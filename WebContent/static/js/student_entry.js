var appendSelectItem = function(selector,itemList){
	if (selector.is(":empty")){
		for ( var idx in itemList){
			var option = "<option value=\""+itemList[idx].id+"\">" + itemList[idx].name + "</option";
			selector.append(option);
		};
	}
}

$('document').ready(function(){
	
	//Retrieve College List
	var collegeList = [];
	//Retrieve Department List
	var departmentList = [];
	//Retrive Quarter Name List
	var quarterNameJSON = {};
	//Retrieve Faculty List
	var facultyList = [];
	//Retrive Quarter List
	var quarterJSON = {};
	
	$.ajax({
		url:contextPath+"/college/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("college list retrieved");
			collegeList = data;
			
			$.ajax({
				url:contextPath+"/department/list",
				type: "GET",
				headers: {
					'Content-Type':'application/json'
				},
				success: function(data, textStatus){
					console.log("department list retrieved");
					departmentList = data;
					
					//Load default undergrad entry
					$('#SubStudentEntry').load(contextPath+"/student/undergrad/entry",function(){
						appendSelectItem($("#SubStudentEntry").find("#college"),collegeList);
						appendSelectItem($("#SubStudentEntry").find("#major"),departmentList);
						appendSelectItem($("#SubStudentEntry").find("#minor"),departmentList);
					});
					
					$.ajax({
						url:contextPath+"/quarter/quarterName/list",
						type: "GET",
						headers: {
							'Content-Type':'application/json'
						},
						success: function(data, textStatus){
							console.log("quarter name list retrieved");
							quarterNameJSON = toQuarterNameJSONMapping(data);
							
							$.ajax({
								url:contextPath+"/quarter/list",
								type: "GET",
								headers: {
									'Content-Type':'application/json'
								},
								success: function(data, textStatus){
									console.log("quarter list retrieved");
									//Convert QuarterList to JSON Mapping
									quarterJSON = toQuarterJSONMapping(data);
									
									$.ajax({
										url:contextPath+"/faculty/list",
										type: "GET",
										headers: {
											'Content-Type':'application/json'
										},
										success: function(data, textStatus){
											console.log("faculty list retrieved");
											facultyList = data;
										},
										error: function(data, textStatus){
											alert("Failed to retrieve faculty list. Please refresh page");
										}
									});
								},
								error: function(data, textStatus){
									alert("Failed to retrieve quarter list. Please refresh page");
								}
							});
						},
						error: function(data, textStatus){
							alert("Failed to retrieve quarter name list. Please refresh page");
						}
					});
				},
				error: function(data, textStatus){
					alert("Failed to retrieve department list. Please refresh page");
				}
			});
		},
		error: function(data, textStatus){
			alert("Failed to retrieve college list. Please refresh page");
		}
	});
	
	/*** EVENT HANDLER ***/
	//Handle Student Type event
	$('#studentType').on('click',"input[type='radio']",function(){
		
		var subEntry = $(this).attr('id');	
		$('#SubStudentEntry').load(contextPath+"/student/"+subEntry+"/entry", function(responseTxt, statusTxt, xhr){
			if ( subEntry === "undergrad" || subEntry === "BSMS" ){
				if ( subEntry === "BSMS"){
					appendSelectItem($("#SubStudentEntry").find("#department"),departmentList);
				}
				appendSelectItem($("#SubStudentEntry").find("#college"),collegeList);
				appendSelectItem($("#SubStudentEntry").find("#major"),departmentList);
				appendSelectItem($("#SubStudentEntry").find("#minor"),departmentList);
			}
			else {
				appendSelectItem($("#SubStudentEntry").find("#department"),departmentList);
				
				if ( subEntry === "phD"){
					appendSelectItem($("#SubStudentEntry").find("#faculty"),facultyList);
				}
			}
		});
	});
	
	// Handle Has Degree checkbox event
	$("#degreeCheckbox").on('change',function(){
		if ($(this).is(":checked")){
			$("#degreeDiv").show();
		}
		else{
			$("#degreeDiv").hide();
		}
	});
	
	//Handle Student Form submission
	$("#student_entryForm").submit(function(e){
		e.preventDefault();
		var studentTypeJSON = {};
		
		var student = {
				"firstname":$("#firstName").val(),
				"middlename":$("#middleName").val(),
				"lastname":$("#lastName").val(),
				"pid":$("#pid").val(),
				"ssn":$("#ssn").val(),
				"residentStatus":{
					"id": $("#residency option:selected").val()
				},
				"degreeList": [],
				"quarterAttendList": []
		};
		
		if ($("#degreeCheckbox").is(":checked")){
			student['degreeList'].push(
				{
					'degree': {
						'id':$("#degree option:selected").val()
					}
				}
				);
		}
		
		//Compute attendance quarter
		var quarterFrom = parseInt($("#quarterFrom").val());
		var quarterTo = parseInt($("#quarterTo").val());
		var yearFrom = parseInt($("#yearFrom").val());
		var yearTo = parseInt($("#yearTo").val());
		var quarterAttendList = computeQuarterList(quarterFrom, yearFrom, 
												   quarterTo, yearTo, 
												   quarterJSON, quarterNameJSON);
		
		student["quarterAttendList"] = quarterAttendList;
		
		if ( $("#undergrad").is(":checked") || $("#BSMS").is(":checked")){
			
			if ($("#BSMS").is(":checked")){
				student['department'] = {
						"id": $("#department option:selected").val()
				};
				student["collegeBSMS"] = { "id": $("#SubStudentEntry #college option:selected").val() };
				student["majorBSMS"] = {
						'id':$("#SubStudentEntry #major option:selected").val()
				};
				student["minorBSMS"] = {
						'id': $("#SubStudentEntry #minor option:selected").val()
				};
				studentTypeJSON['bsmsMaster'] = student;
			}
			else {
				student["college"] = { "id": $("#SubStudentEntry #college option:selected").val() };
				student["major"] = {
						'id':$("#SubStudentEntry #major option:selected").val()
				};
				student["minor"] = {
						'id': $("#SubStudentEntry #minor option:selected").val()
				};
				studentTypeJSON["underGrad"] = student;
			}
 		}
		else {
			student['department'] = {
					"id": $("#department option:selected").val()
			};
			
			if ($("#phD").is(":checked")){
				student['advisor'] = {
						"id": $("#faculty option:selected").val()
				}
				
				studentTypeJSON['phdCandidate'] = student;
			}
			else if ($("#master").is(":checked")){
				studentTypeJSON['master'] = student;
			}
			else {
				studentTypeJSON['phdPreCandidate'] = student;
			}
		}
		
		//Submit form
		$.ajax({
			url:$("#student_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(studentTypeJSON),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new student");
				
				//Retrieve updated quarterList
				$.ajax({
					url:contextPath+"/quarter/list",
					type: "GET",
					headers: {
						'Content-Type':'application/json'
					},
					success: function(data, textStatus){
						//Convert QuarterList to JSON Mapping
						quarterJSON = toQuarterJSONMapping(data);
					},
					error: function(data, textStatus){
						alert("Failed to retrieve quarter list. Please refresh page");
					}
				});
			},
			error: function(data, textStatus){
				alert("Failed to insert student. Please retry");
			}
		});
	});
});