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
		displayGradeOption(courseClassJSON[class_id]);
		displayGradeInputType(courseClassJSON[class_id]);
	});
	
	//GradeOption selection event handler
	$("#gradeOptionDiv").on('change','#gradeOption',function(){
		var text = $(this).find("option:selected").text();
		
		$("#gradeDiv").empty();
		$("#gradeDiv").append("<label>Grade</label>");
		if ( text === 'letter only'){
			$("#gradeDiv").append(
					"<input type=\"text\" placeHolder=\"Enter grade\" class=\"form-control\" id=\"grade\" />"
			);
		}else{
			$("#gradeDiv").append(
					"<select class=\"form-control\" id=\"grade\">"
				+	"	<option>S</option>"
				+	"	<option>U</option>"
				+	"<\select>"
			);
		}
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
				'grade': $("#grade").val(),
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
		
		if ($("#gradeOptionDiv").is(":empty")){
			enrollment['letter_option'] = courseClassJSON[class_id].course.courseOption.letter_option;
			enrollment['su_option'] = courseClassJSON[class_id].course.courseOption.letter_option;
		}
		else {
			var option = $("#gradeOptionDiv").find("#gradeOption").find("option:selected").text();
			if ( option === "letter only"){
				enrollment['letter_option'] = true;
			}
			else{
				enrollment['su_option'] = true;
			}
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
	
	//Display enrolled class event handler
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		var quarter_id = $("#quarter option:selected").val();
		displayClassEnrolledByStudentandQuarter(student_id,quarter_id);
	});
	
	$("#quarter").on('change',function(){
		var student_id = $("#student option:selected").val();
		var quarter_id = $("#quarter option:selected").val();
		displayClassEnrolledByStudentandQuarter(student_id,quarter_id);
	});
	
	//Update grade data event handler
	$("#DisplayEnrollmentDiv").on('click',"#btnUpdate", function(){
		var form_group = $(this).closest(".form-group");	
		var enrollment = {
				'id': form_group.find("#enrollmentId").val(),
				'grade': form_group.find("#gradeUpdate").val()
		}
		
		$.ajax({
			url:contextPath+"/class/enrolled/update",
			type: "PUT",
			data:JSON.stringify(enrollment),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully update student's grade");
			},
			error: function(data, textStatus){
				alert("Failed to insert enrollment. Please retry");
			}
		});
	});
/**************** FINISH EVENT HANDLER *******************/
});

/**************** HELPER FUNCTION ***********************/
var displayClassEnrolledByStudentandQuarter = function(student_id,quarter_id){
	$("#DisplayEnrollmentDiv").empty();
	$.ajax({
		url:contextPath+"/class/enrolled/"+student_id+"/"+quarter_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data){
			for ( var idx in data ){
				
				$("#DisplayEnrollmentDiv").append(
					"<div class=\"row\">" +
						"	<div class=\"col-md-4\">" +
						"		<h5>"+data[idx].section.sectionClass.course.courseSubject.symbol+" "+data[idx].section.sectionClass.course.courseUnitNumber.currNum+"</h5>" +
						"</div>" +
				
						"<div class=\"form-group\">" +
						"	<div class=\"col-md-4\">" +
						"		<input type=\"hidden\" id=\"enrollmentId\" value=\""+data[idx].id+"\"/>" +
						"		<input type=\"text\" class=\"form-control\" id=\"gradeUpdate\" value=\""+data[idx].grade+"\"/>" +
						"	</div>" +
						"	<div class=\"col-md-4\">" +
						"		<button type=\"button\" class=\"btn btn-primary\">Update</button>" +
						"	</div>" +
						"</div>" +
					"</div>"
				);
			}
		},
		error: function(data){
			alert("Failed retrieving enrollment data. Please refresh page");
		}
	});
}

var retrieveClassandSection = function(quarter_id){
	$.ajax({
		url:contextPath+"/class/quarter/"+quarter_id+"/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("class JSON retrieved");
			//Initialize select option for class and sectionList
			$("#class").empty();
			Object.entries(data).forEach(function([key,value]){
				var text = value.course.courseSubject.symbol + " " + value.course.courseUnitNumber.currNum;
				var option = new Option(text,key);
				$("#class").append($(option));
			})
			courseClassJSON = data;
			
			$("#section").empty();
			var class_id = $("#class option:selected").val();
			
			if ( data[class_id] !== undefined ){
				var itemList = data[class_id].sectionList;
				appendSectionSelectionItem($("#section"),itemList);
				displayUnitInput(data[class_id]);
				displayGradeOption(data[class_id]);
				displayGradeInputType(data[class_id]);
			}
			else {
				$("#unitDiv").hide();
				$("#gradeDiv").hide();
				$("#gradeOptionDiv").hide();
			}
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

var displayGradeInputType = function(classObj){
	$("#gradeDiv").empty();
	
	var letter_option = classObj.course.courseOption.letter_option;
	
	$("#gradeDiv").append("<label>Grade</label>");
	
	if ( letter_option ){
		$("#gradeDiv").append("<input type=\"text\" class=\"form-control\" id=\"grade\" placeholder=\"Enter grade\">");
	}else{
		$("#gradeDiv").append(
				"<select class=\"form-control\" id=\"grade\">"
			+	"	<option>S<\option>"
			+	"	<option>U<\option>"
			+	"<\select>"
		)
	}
}

var displayGradeOption = function(courseClassObj){
	var letter_option = courseClassObj.course.courseOption.letter_option;
	var su_option = courseClassObj.course.courseOption.su_option;
	
	$("#gradeOptionDiv").empty();
	if ( letter_option && su_option ){
		var gradeOptionHTML = "<label>Grade Option</label> "
							 +"<select class=\"form-control\" id=\"gradeOption\">"
							 +"		<option>letter only</option>"
							 +"		<option>SU only</option>"
							 +"</select>";
		
		$("#gradeOptionDiv").append(gradeOptionHTML);
	}
}
/****************** FINISH HELPER FUNCTION ***************/