var appendStudentItem = function(selector, itemList){
	if (selector.is(':empty')){
		for ( var idx in itemList ){
			var text = itemList[idx].firstname + " " + itemList[idx].lastname + " " + itemList[idx].middlename;
			var value = itemList[idx].id;
			var option = new Option(text,value);
			
			selector.append($(option));
		}
	}
}

var appendFacultyItem = function(selector, itemList){
	selector.empty();
	for ( var idx in itemList ){
		var text = itemList[idx].name;
		var value = itemList[idx].id;
		var option = new Option(text,value);
		
		selector.append($(option));
	}
}

var appendDepartmentSelectItem =function(selector,itemList){
	appendFacultyItem(selector,itemList);
}

var toStudentJSON = function(itemList){
	var studentJSON = {};
	for ( var idx in itemList) {
		var id = itemList[idx].id.toString();
		studentJSON[id] = itemList[idx];
	}
	
	return studentJSON;
}

$('document').ready(function(){
	var gradStudentJSON = {};
	var phdCandidateJSON = {};
	var departmentList = [];
	
	//Retrive Grad student list
	$.ajax({
		url:contextPath+"/student/grad/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("grad student list retrieved");
			appendStudentItem($("#student"),data);
			gradStudentJSON = toStudentJSON(data);
			
			//Retrieve PHD Candidate list
			$.ajax({
				url:contextPath+"/student/phd/list",
				type: "GET",
				headers: {
					'Content-Type':'application/json'
				},
				success: function(data, textStatus){
					console.log("phd candidate list retrieved");
					phdCandidateJSON = toStudentJSON(data);
					
					if ( phdCandidateJSON[$("#student option:selected").val()] !== undefined ){
						$("#committeePhD").show();
					}
					else {
						$("#committeePhD").hide();
					}
					
					//Retrieve Department List
					$.ajax({
						url:contextPath+"/department/list",
						type: "GET",
						headers: {
							'Content-Type':'application/json'
						},
						success: function(data, textStatus){
							console.log("department list retrieved");
							departmentList = data;
							appendDepartmentSelectItem($("#committeePhD").find("#department"),departmentList);
							
							var dept_id = $("#committeePhD").find("#department option:selected").val();
							//Initialize faculty by department
							$.ajax({
								url:contextPath+"/faculty/list/department/"+dept_id,
								type: "GET",
								headers: {
									'Content-Type':'application/json'
								},
								success: function(data, textStatus){
									console.log("faculty list by department retrieved");
									appendFacultyItem($("#committeePhD").find("#faculty"),data);
								},
								error: function(data, textStatus){
									alert("Failed to retrieve faculty list by department. Please refresh page");
								}
							});
						},
						error: function(data, textStatus){
							alert("Failed to retrieve phd candidate list. Please refresh page");
						}
					});
				},
				error: function(data, textStatus){
					alert("Failed to retrieve phd candidate list. Please refresh page");
				}
			});
				
		},
		error: function(data, textStatus){
			alert("Failed to retrieve grad student list. Please refresh page");
		}
	});
	
	
	//Adding Faculty event handler
	$("#AddFacultyBtn").on('click',function(){
		if ($("#faculty").is(":empty")) return;
		
		var facultyId = $("#faculty option:selected").val();
		var facultyName = $("#faculty option:selected").text();
		var facultyBtn = "<button class=\"btn btn-default\" value=\""+facultyId+"\" type=\"button\">"+ facultyName+
		" <span class=\"glyphicon glyphicon-remove\"></span></button>"
		
		$("#GradCommitteeList").append(facultyBtn);
	});
	
	//Handling deleting faculty in grad committee
	$("#GradCommitteeList").on('click','button',function(){
		$(this).remove();
	});
	
	//Thesis Grad Committee event handler
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		if (phdCandidateJSON[student_id] !== undefined){
			$("#committeePhD").show();
		}
		else{
			$("#committeePhD").hide();
		}
		
		var dept_id = gradStudentJSON[student_id]['department']['id'];
		$.ajax({
			url:contextPath+"/faculty/list/department/"+dept_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("faculty list by department retrieved");
				appendFacultyItem($("#faculty"),data);
			},
			error: function(data, textStatus){
				alert("Failed to retrieve faculty list by department. Please refresh page");
			}
		});
	});
	
	//Thesis PhD committee event handler
	$("#committeePhD").on('change','#department',function(){
		var dept_id = $(this).find("option:selected").val();
		$.ajax({
			url:contextPath+"/faculty/list/department/"+dept_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("faculty list by department retrieved");
				appendFacultyItem($("#committeePhD").find("#faculty"),data);
			},
			error: function(data, textStatus){
				alert("Failed to retrieve faculty list by department. Please refresh page");
			}
		});
	});
	
	//Thesis Phd Committee add faculty event handler
	$("#committeePhD").on('click','#AddFacultyBtn', function(){
		var faculty = $("#committeePhD").find("#faculty");
		if (faculty.is(":empty")) return;
		
		var facultyId = faculty.find("option:selected").val();
		var facultyName = faculty.find("option:selected").text();
		var facultyBtn = "<button class=\"btn btn-default\" value=\""+facultyId+"\" type=\"button\">"+ facultyName+
		" <span class=\"glyphicon glyphicon-remove\"></span></button>"
		
		$("#PhDCommitteeList").append(facultyBtn);
	});
	
	$("#committeePhD").find("#PhDCommitteeList").on('click','button', function(){
		$(this).remove();
	});
	
	//Form submission
	$("#thesiscommittee_entryForm").submit(function(e){
		e.preventDefault();
		
		var committee_type = {};
		
		var committeeGrad = {};
		committeeGrad['facultyList'] = [];
		committeeGrad['gradList'] = [{
			'id': $("#student option:selected").val()
		}];
		
		$("#GradCommitteeList").find('button').each(function(){
			committeeGrad['facultyList'].push({
				'id': $(this).val()
			})
		});
		
		committee_type['gradCommittee'] = committeeGrad;
		
		//if PhD Candidate is chosen
		if ( phdCandidateJSON[$("#student option:selected").val()] !== undefined){
			var committeePhD = {
					'facultyList': [],
					'phdList': [{
						'id': $("#student option:selected").val()
					}]
			}
			
			$("#committeePhD").find("#PhDCommitteeList").find('button').each(function(){
				committeePhD['facultyList'].push({
					'id': $(this).val()
				})
			});
			
			committee_type['phDCommittee'] = committeePhD;
		}
		
		//Submit form
		$.ajax({
			url:$("#thesiscommittee_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(committee_type),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new committee");
			},
			error: function(data, textStatus){
				alert("Failed to insert committee. Please retry");
			}
		});
		
		//clean up
		$("#GradCommitteeList").empty();
		$("#PhDCommitteeList").empty();
		$("#committeePhD").hide();
	});
});
