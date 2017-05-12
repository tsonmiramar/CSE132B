$('document').ready(function(){	
/**************** EVENT HANDLER ***************************/
	//Quarter selection event Handler
	$("#quarter").on("change",function(){
		
		//Load new class from current quarter
		var quarter_id = $("#quarter option:selected").val();
		retrieveClassandSection(quarter_id);
	});
	
	//Class selection event Handler
	$("#class").on("change",function(){
		var class_id = $("#class option:selected").val();
		var sectionList = courseClassJSON[class_id].sectionList;
		
		appendSectionSelectionItem($("#section"),sectionList);
		displayUnitInput(courseClassJSON[class_id]);
		displayGradeInputType(class_id);
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
		var courseUnitNumber = courseClassJSON[class_id].course.courseUnitNumber;
		if ( courseUnitNumber.unitFrom !== courseUnitNumber.unitTo ){
			enrollment['unitTaken'] = $("#unitDiv").find("#unit").val();
		}
		else{
			enrollment['unitTaken'] = courseUnitNumber.unitTo;
		}
		
		$.ajax({
			url:$("#pastenrollment_entryForm").attr('action'),
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
var retrieveClassandSection = function(quarter_id){
	$.ajax({
		url:contextPath+"/class/quarter/"+quarter_id+"/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("class list retrieved");
			//Initialize select option for class and sectionList
			$("#class").empty();
			Object.entries(data).forEach(function([key,value]){
				var text = value.course.courseSubject.symbol + " " + value.course.courseUnitNumber.currNum;
				var option = new Option(text,key);
				$("#class").append($(option));
			})
			courseClassJSON = data;
			
			$("#section").empty();
			if ( Object.keys(data).length > 0 ){
				var class_id = $("#class option:selected").val()
				var itemList = data[class_id].sectionList;
				appendSectionSelectionItem($("#section"),itemList);
				displayUnitInput(data[class_id]);
			};
			
			displayGradeInputType(class_id);
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

var displayUnitInput = function(classObj){
	var unitFrom = classObj.course.courseUnitNumber.unitFrom;
	var unitTo = classObj.course.courseUnitNumber.unitTo;
	if ( unitFrom != unitTo ){
		$("#unitDiv").show();
		$("#unitDiv").find("label").text("Unit: " + unitFrom+"-"+unitTo);
	}
	else{
		$("#unitDiv").hide();
	}
}

var displayGradeInputType = function(class_id){
	if ( courseClassJSON[class_id].course.courseOption.letter_option == true ){
		$("#gradeInputType").html("<input type=\"text\" class=\"form-control\" id=\"grade\" placeholder=\"Enter grade\">");
	}else{
		$("#gradeInputType").html(
				"<select class=\"form-control\" id=\"grade\">"
			+	"	<option>S<\option> "
			+	"	<option>U<\option> "
			+	"<\select>"
		)
	}
}
/****************** FINISH HELPER FUNCTION ***************/