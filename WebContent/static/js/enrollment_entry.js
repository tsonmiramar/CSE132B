$('document').ready(function(){
	
	//Retrive all student
	$.ajax({
		url:contextPath+"/student/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("student list retrieved");
			
			//Append student selection
			for ( var idx in data){
				var text = data[idx].firstname + " " + data[idx].lastname + " " + data[idx].middlename;
				var value = data[idx].id;
				var option = new Option(text,value);
				
				$("#student").append($(option));
			}
		},
		error: function(data, textStatus){
			alert("Failed to retrieve student list. Please refresh page");
		}
	});
	
	//Retrieve all class in current quarter
	var classJSON = {};
	$.ajax({
		url:contextPath+"/class/Spring/2017/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("class list retrieved");
			courseClassList = data;
			//Initialize select option for class and sectionList
			for ( var idx in data){
				var text = data[idx].course.courseSubject.symbol + " " + data[idx].course.courseUnitNumber.currNum;
				var value = data[idx].id;
				var option = new Option(text,value);
				$("#class").append($(option));
				
				classJSON = toJSON(data);
			}
	
			if ( data.length > 0 ){
				var itemList = data[0].sectionList
				retrieveSectionList($("#section"),itemList);
				
				var class_id = $("#class option:selected").val();
				displayUnitInput(classJSON,class_id);
			};
		},
		error: function(data, textStatus){
			alert("Failed to retrieve class list. Please refresh page");
		}
	});
	
	//Register Class select event handler
	$("#class").on('change',function(){
		var class_id = $("#class option:selected").val();
		var itemList = classJSON[class_id].sectionList;
		
		retrieveSectionList($("#section"),itemList);
		
		displayUnitInput(classJSON,class_id);
	});
	
	//Form submission
	$("#enrollment_entryForm").submit(function(e){
		e.preventDefault();
		
		var enrollment = {
				'student': {
					'id' : $("#student option:selected").val()
				},
				'section': {
					'id' : $("#section option:selected").val()
				}
		}

		var class_id = $("#class option:selected").val();
		var courseUnitNumber = classJSON[class_id].course.courseUnitNumber;
		if ( courseUnitNumber.unitFrom !== courseUnitNumber.unitTo ){
			enrollment['unitTaken'] = $("#unitDiv").find("#unit").val();
		}
		else{
			enrollment['unitTaken'] = courseUnitNumber.unitTo;
		}
		
		$.ajax({
			url:$("#enrollment_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(enrollment),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new enrollment");
			},
			error: function(data, textStatus){
				alert("Failed to insert enrollment. Please retry");
			}
		});
	});
});

var toJSON = function(itemList){
	var itemJSON = {};
	for ( var idx in itemList ){
		var id = itemList[idx].id.toString();
		itemJSON[id] = itemList[idx];
	}
	return itemJSON;
}

var retrieveSectionList = function(selector,sectionList){
	selector.empty();
	for ( var idx in sectionList){
		var value = sectionList[idx].id;
		var option = new Option(value,value);
		selector.append($(option));
	}
	
}

var displayUnitInput = function(classJSON, class_id){
	var unitFrom = classJSON[class_id].course.courseUnitNumber.unitFrom;
	var unitTo = classJSON[class_id].course.courseUnitNumber.unitTo;
	if ( unitFrom != unitTo ){
		$("#unitDiv").show();
		$("#unitDiv").find("label").text("Unit: " + unitFrom+"-"+unitTo);
	}
	else{
		$("#unitDiv").hide();
	}
}
