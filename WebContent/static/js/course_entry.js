$('document').ready(function(){
	
	//Grade Option Event handler
	$("#minUnitInput").hide();
	
	$("#Single_unitOpt").click(function(){
		if (this.checked){
			$("#minUnitInput").val("-1");
			$("#minUnitInput").hide();
		}
	});
	
	$("#Range_unitOpt").click(function(){
		if (this.checked){
			$("#minUnitInput").show();
		}
	});
	
	//Checkbox value event handler
	$("input[type='checkbox']").on('change',function(){
		if ($(this).is(":checked")){
			$(this).val("true");
		}
		else{
			$(this).val("false");
		}
	});
	
	//Prerequisite Add Event Handler
	$('#AddPrereqBtn').click(function(){
		var courseName = $("#prereg_id option:selected").text();	
		var courseId = $("#prereg_id option:selected").val();
		
		if (courseId === undefined){
			return;
		}
		var courseBtn = "<button class=\"btn btn-default\" value=\""+courseName+"\" type=\"button\">"+ courseName+
						" <span class=\"glyphicon glyphicon-remove\"></span></button>"
		
		var courseInputHidden = "<input id=\""+courseName+"_hidden\" type=\"hidden\" name=\"PrereqListId\" value=\""+courseId+"\">"
		$("#PrereqList").append(courseBtn);
		$("#PrereqList").append(courseInputHidden);
	});
	
	$("#PrereqList").on('click',"button",function(){
		var inputHidden = $(this).val() + "_hidden";

		$("#"+inputHidden).remove();
		$(this).remove();
	});
	
	//Perform Form submission
	$("#course_entryForm").submit(function(e){
		e.preventDefault();
		
		var course = {};
		
		course['id'] = 0;
		
		course['courseSubject'] = {'subject_id': $("#subject_id option:selected").val(),
								   'symbol': $("#subject_id option:selected").text()
		};
		
		course['courseOption'] = {
				'letter_option' : $("#letterOpt").val(),
				'SU_option': $("#SUOpt").val(),
				'labwork': $("#labwork").val(),
				'instructor_consent': $('#instConsent').val()
		};
		
		course['courseUnitNumber'] = {
				'currNum': $("#currNum").val(),
				'prevNum': undefined,
				'unitFrom': $("#minUnitInput").val() === "-1" ? $("#maxUnitInput").val() : $("#minUnitInput").val(), 
				'unitTo': $("#maxUnitInput").val()
		};
		
		course['prereq'] = [];
		if ( !$('#PrereqList').is(':empty')){
			$("#PrereqList").children("input[type='hidden']").each(function(){
				var prereq = {'id': $(this).val()};
				course['prereq'].push(prereq);
			});
		}
		
		var formURL = $(this).attr("action");
		
		$.ajax({
			url:formURL,
			type: "POST",
			data:JSON.stringify(course),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new course");
			},
			error: function(data, textStatus){
				alert("Failed to insert course. Please retry");
			}
		});	
		
		//Clear Form after submit
		$(':input').not(':button, :submit, :reset, :hidden, :checkbox, :radio, :select').val("");
		$(':checkbox, :radio').prop('checked',false);
	});
});