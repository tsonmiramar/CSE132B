$('document').ready(function(){
	//Register Class select event handler
	$("#class").on('change',function(){
		var class_id = $("#class option:selected").val();
		var itemList = courseClassJSON[class_id].sectionList;
		
		retrieveSectionList($("#section"),itemList);
		
		displayUnitInput(courseClassJSON[class_id]);
		
		displayGradeOption(courseClassJSON[class_id]);
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
				},
				'letter_option': false,
				'su_option': false
		}

		var class_id = $("#class option:selected").val();
		var courseUnitNumber = courseClassJSON[class_id].course.courseUnitNumber;
		if ( courseUnitNumber.unitFrom !== courseUnitNumber.unitTo ){
			enrollment['unitTaken'] = $("#unitDiv").find("#unit").val();
		}
		else{
			enrollment['unitTaken'] = courseUnitNumber.unitTo;
		}
		
		var courseOption = courseClassJSON[class_id].course.courseOption;
		if ( $("#GradeOptionDiv").is(":empty") ){
			enrollment['letter_option'] = courseOption.letter_option;
			enrollment['su_option'] = courseOption.su_option;
		}
		else{
			if ( $("#GradeOptionDiv").find("#gradeOption option:selected").text() === 'letter only' ){
				enrollment['letter_option'] = true;
			}
			else {
				enrollment['su_option'] = true;
			}
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

var displayGradeOption = function(courseClassObj){
	var letter_option = courseClassObj.course.courseOption.letter_option;
	var su_option = courseClassObj.course.courseOption.su_option;
	
	$("#GradeOptionDiv").empty();
	if ( letter_option && su_option ){
		var gradeOptionHTML = "<label>Grade Option</label> "
							 +"<select class=\"form-control\" id=\"gradeOption\">"
							 +"		<option>letter only</option>"
							 +"		<option>SU only</option>"
							 +"		<option>letter or SU</option>"
							 +"</select>";
		
		$("#GradeOptionDiv").append(gradeOptionHTML);
	}
}
