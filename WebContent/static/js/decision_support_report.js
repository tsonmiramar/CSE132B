$("document").ready(function(){
	
	$("#course").on('change',function(){
		var faculty_id = $("#faculty option:selected").val();
		var course_id = $("#course option:selected").val();
		var quarter_id = $("#quarter option:selected").val();
		displayGradeCountByQuarter(faculty_id,course_id,quarter_id);
		displayGradeCountOverYear(faculty_id,course_id);
		displayGradeCountbyCourseOverYear(course_id);
		displayGradeGPAOverYear(faculty_id,course_id);
	});
	
	$("#faculty").on('change',function(){
		var faculty_id = $("#faculty option:selected").val();
		var course_id = $("#course option:selected").val();
		var quarter_id = $("#quarter option:selected").val();
		displayGradeCountByQuarter(faculty_id,course_id,quarter_id);
		displayGradeCountOverYear(faculty_id,course_id);
		displayGradeGPAOverYear(faculty_id,course_id);
	});
	
	$("#quarter").on('change',function(){
		var faculty_id = $("#faculty option:selected").val();
		var course_id = $("#course option:selected").val();
		var quarter_id = $("#quarter option:selected").val();
		displayGradeCountByQuarter(faculty_id,course_id,quarter_id);
	});
});

var displayGradeCountByQuarter = function(faculty_id,course_id,quarter_id){
	$("#GradeCountByQuarterDiv").empty();
	$.ajax({
		url:contextPath+"/gradereport/gradecount/"+faculty_id+"/"+course_id+"/"+quarter_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("grade count by quarter retrieved");
			for ( var idx in data ){
				$("#GradeCountByQuarterDiv").append(
						"<div class=\"row\">"
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].grade+"</h4>"
							+"</div>"
						
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].count+"</h4>"
							+"</div>"
						+"</div>"
				);		
			}
		},
		error:function(data){
			alert("Failed to retrieve grade count. please refresh page");
		}
	});
}

var displayGradeCountOverYear = function(faculty_id,course_id){
	$("#GradeCountOverYearDiv").empty()
	$.ajax({
		url:contextPath+"/gradereport/gradecount/"+faculty_id+"/"+course_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("grade count by quarter retrieved");
			for ( var idx in data ){
				$("#GradeCountOverYearDiv").append(
						"<div class=\"row\">"
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].grade+"</h4>"
							+"</div>"
						
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].count+"</h4>"
							+"</div>"
						+"</div>"
				);		
			}
		},
		error:function(data){
			alert("Failed to retrieve grade count. please refresh page");
		}
	});
}

var displayGradeCountbyCourseOverYear = function(course_id){
	$("#GradeCountByCourseOverYearDiv").empty()
	$.ajax({
		url:contextPath+"/gradereport/gradecount/"+course_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("grade count by course retrieved");
			for ( var idx in data ){
				$("#GradeCountByCourseOverYearDiv").append(
						"<div class=\"row\">"
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].grade+"</h4>"
							+"</div>"
						
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].count+"</h4>"
							+"</div>"
						+"</div>"
				);		
			}
		},
		error:function(data){
			alert("Failed to retrieve grade count by course. please refresh page");
		}
	});
}

var displayGradeGPAOverYear = function(faculty_id,course_id){
	$("#GradeGPAOverYearDiv").empty()
	$.ajax({
		url:contextPath+"/gradereport/gradegpa/"+faculty_id+"/"+course_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("grade count by quarter retrieved");
			for ( var idx in data ){
				$("#GradeGPAOverYearDiv").append(
						"<div class=\"row\">"
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].grade+"</h4>"
							+"</div>"
						
							+"<div class=\"col-md-4\">"
								+"<h4>"+data[idx].gpa+"</h4>"
							+"</div>"
						+"</div>"
				);		
			}
		},
		error:function(data){
			alert("Failed to retrieve grade count. please refresh page");
		}
	});
}