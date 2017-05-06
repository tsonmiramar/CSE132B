var appendSelectItem = function(selector,itemList){
	if (selector.is(":empty")){
		for ( var idx in itemList){
			var option = "<option value=\""+itemList[idx].id+"\">" + itemList[idx].name + "</option";
			selector.append(option);
		};
	}
}

var computeQuarterList = function(quarterFrom,yearFrom,quarterTo,yearTo,quarterJSON,quarterNameJSON){
	var quarter_id = quarterFrom - 1; //index 0 1 2 from quarterFrom val 1 2 3
	var year = yearFrom
	var quarterAttendList = [];
	
	while (year <= yearTo ){
		var quarterItem = {};
		
		var quarterName = quarterNameJSON[(quarter_id+1).toString()];
		if ( quarterJSON[quarterName][year] !== undefined){
			quarterItem["id"] = quarterJSON[quarterName][year];
		}
		else {
			quarterItem["quarterName"] = {
					"id" : quarter_id + 1
			};
			quarterItem["year"] = year;
		}
		
		quarterAttendList.push(quarterItem);
		
		if ( year === yearTo && quarter_id === ( quarterTo -1 )) break;
		
		year = quarter_id === 0 ? year + 1 : year;
		quarter_id = (quarter_id + 1) % 3;
	}
	
	return quarterAttendList;
}

var toQuarterJSONMapping = function(quarterList){
	var quarterJSON = {};
	
	for ( var i in quarterList){
		var name = quarterList[i].quarterName.name;
		var id = quarterList[i].id;
		var year = quarterList[i].year.toString();
		
		if (quarterJSON[name] === undefined)
			quarterJSON[name] = {};
		
		quarterJSON[name][year] = id;
	}
	
	return quarterJSON;
}

var toQuarterNameJSONMapping = function(quarterNameList){
	var quarterNameJSON = {};
	
	for ( var i in quarterNameList){
		var name = quarterNameList[i].name;
		var id = quarterNameList[i].id.toString();
		quarterNameJSON[id] = name;
	}
	
	return quarterNameJSON;
}

$('document').ready(function(){
	
	//Retrieve College List
	var collegeList = [];
	$.ajax({
		url:contextPath+"/college/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("college list retrieved");
			collegeList = data;
		},
		error: function(data, textStatus){
			alert("Failed to retrieve college list. Please refresh page");
		}
	});
	
	//Retrieve Department List
	var departmentList = [];
	$.ajax({
		url:contextPath+"/department/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("department list retrieved");
			departmentList = data;
		},
		error: function(data, textStatus){
			alert("Failed to retrieve department list. Please refresh page");
		}
	});
	
	//Retrieve Faculty List
	var facultyList = [];
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
	
	//Retrive Quarter Name List
	var quarterNameJSON = {};
	$.ajax({
		url:contextPath+"/quarter/quarterName/list",
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success: function(data, textStatus){
			console.log("quarter name list retrieved");
			quarterNameJSON = toQuarterNameJSONMapping(data);
		},
		error: function(data, textStatus){
			alert("Failed to retrieve quarter name list. Please refresh page");
		}
	});
	
	//Retrive Quarter List
	var quarterJSON = {};
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
		},
		error: function(data, textStatus){
			alert("Failed to retrieve quarter list. Please refresh page");
		}
	});
	
	//Load default undergrad entry
	$('#SubStudentEntry').load(contextPath+"/student/undergrad/entry",function(){
		appendSelectItem($("#SubStudentEntry").find("#college"),collegeList);
	});
	
	//Retrieve 
	//Handle Student Type event
	$('#studentType').on('click',"input[type='radio']",function(){
		
		var subEntry = $(this).attr('id');	
		$('#SubStudentEntry').load(contextPath+"/student/"+subEntry+"/entry", function(responseTxt, statusTxt, xhr){
			if ( subEntry === "undergrad" || subEntry === "BSMS" ){
				if ( subEntry === "BSMS"){
					appendSelectItem($("#SubStudentEntry").find("#department"),departmentList);
				}
				appendSelectItem($("#SubStudentEntry").find("#college"),collegeList);
			}
			else {
				appendSelectItem($("#SubStudentEntry").find("#department"),departmentList);
				
				if ( subEntry === "phD"){
					appendSelectItem($("#SubStudentEntry").find("#faculty"),facultyList);
				}
			}
		});
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
				"quarterAttendList": []
		};
		
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
			student["major"] = $("#SubStudentEntry #major").val();
			student["minor"] = $("#SubStudentEntry #minor").val();
			student["college"] = { "id": $("#SubStudentEntry #college option:selected").val() };
			
			if ($("#BSMS").is(":checked")){
				student['department'] = {
						"id": $("#department option:selected").val()
				};
				
				studentTypeJSON['bsmsMaster'] = student;
			}
			else {
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
		console.log(studentTypeJSON);
		
		$.ajax({
			url:$("#student_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(studentTypeJSON),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new student");
			},
			error: function(data, textStatus){
				alert("Failed to insert student. Please retry");
			}
		});
	});
});