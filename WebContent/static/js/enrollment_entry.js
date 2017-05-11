$('document').ready(function(){
	//Register Class select event handler
	$("#class").on('change',function(){
		var class_id = $("#class option:selected").val();
		var itemList = courseClassJSON[class_id].sectionList;
		
		retrieveSectionList($("#section"),itemList);
		
		displayUnitInput(courseClassJSON[class_id]);
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
		var courseUnitNumber = courseClassJSON[class_id].course.courseUnitNumber;
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

var retrieveSectionList = function(selector,sectionList){
	selector.empty();
	for ( var idx in sectionList){
		var value = sectionList[idx].id;
		var option = new Option(value,value);
		selector.append($(option));
	}
	
}

var displayUnitInput = function(courseClassObj){
	var unitFrom = courseClassObj.course.courseUnitNumber.unitFrom;
	var unitTo = courseClassObj.course.courseUnitNumber.unitTo;
	if ( unitFrom != unitTo ){
		$("#unitDiv").show();
		$("#unitDiv").find("label").text("Unit: " + unitFrom+"-"+unitTo);
	}
	else{
		$("#unitDiv").hide();
	}
}
