$('document').ready(function(){
	
/********* PAGE INITIALIZATION **************/
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
	
	
	//Retrieve all quarter
	var quarterJSON = {};
	$.ajax({
		url:contextPath+"/quarter/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("quarter list retrieved");
			
			//Append quarter selection
			for ( var idx in data){
				var text = data[idx].quarterName.name + " " + data[idx].year;
				var value = data[idx].id;
				var option = new Option(text,value);
				
				$("#quarter").append($(option));
			}
			quarterJSON = toJSON(data);
			
			//Retrieve all class in the selected quarter
			var quarterName = data[0].quarterName.name;
			var year = data[0].year;
			
			retrieveClassandSection(quarterName,year);
		},
		error: function(data, textStatus){
			alert("Failed to retrieve quarter list. Please refresh page");
		}			
	});
/**************** FINISH PAGE INITIALIZATION **************/
	
/**************** EVENT HANDLER ***************************/
	//Quarter selection event Handler
	$("#quarter").on("change",function(){
		
		//Load new class from current quarter
		var quarter_id = $("#quarter option:selected").val();
		var quarterName = quarterJSON[quarter_id].quarterName.name;
		var year = quarterJSON[quarter_id].year;
		
		retrieveClassandSection(quarterName,year);
	});
	
	//Class selection event Handler
	$("#class").on("change",function(){
		var class_id = $("#class option:selected").val();
		var sectionList = classJSON[class_id].sectionList;
		
		appendSectionSelectionItem($("#section"),sectionList);
		
		displayUnitInput(classJSON, class_id);
	});
	
	//Form submission
	$("#pastenrollment_entryForm").submit(function(e){
		e.preventDefault();
		
		var enrollment = {
				'student': {
					'id' : $("#student option:selected").val()
				},
				'section': {
					'id' : $("#section option:selected").val()
				},
				'grade': $("#grade").val()
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
/**************** FINISH EVENT HANDLER *******************/
});

/**************** HELPER FUNCTION ***********************/
var toJSON = function(itemList){
	var itemJSON = {};
	for ( var idx in itemList) {
		var id = itemList[idx].id.toString();
		itemJSON[id] = itemList[idx];
	}
	return itemJSON;
}

var retrieveClassandSection = function(quarterName,year){
	$.ajax({
		url:contextPath+"/class/"+quarterName+"/"+year+"/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("class list retrieved");
			//Initialize select option for class and sectionList
			$("#class").empty();
			for ( var idx in data){
				var text = data[idx].course.courseSubject.symbol + " " + data[idx].course.courseUnitNumber.currNum;
				var value = data[idx].id;
				var option = new Option(text,value);
				$("#class").append($(option));
			}
			
			classJSON = toJSON(data);
			
			$("#section").empty();
			if ( data.length > 0 ){
				var itemList = data[0].sectionList
				appendSectionSelectionItem($("#section"),itemList);
				
				var class_id = $("#class option:selected").val();
				displayUnitInput(classJSON,class_id);
			};
		},
		error: function(data, textStatus){
			alert("Failed to retrieve class list. Please refresh page");
		}
	});
}

var appendSectionSelectionItem = function(selector,sectionList){
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
/****************** FINISH HELPER FUNCTION ***************/