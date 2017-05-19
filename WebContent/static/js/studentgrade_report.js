$('document').ready(function(){
	
	$("#student").on('change',function(){
		
		var student_id = $("#student option:selected").val();
		//display grade
		$("#GradeInfoDiv").empty();
		$.ajax({
			url:contextPath+"/student/class/grade/"+student_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("enrollment list retrieved");
				for ( var idx in data){
					var elem = data[idx];
					
					var row = $("<div class=\"row\"></div>");
					row.append("<div class=\"col-md-2\"></div>");
					
					var classNameDiv = 
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.section.sectionClass.course.courseSubject.symbol+" "+elem.section.sectionClass.course.courseUnitNumber.currNum+"</h3>"
					+	"	</div>";
					
					var gradeDiv = 
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.grade+"</h3>"
					+	"	</div>";
					
					var unitDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.unitTaken+"</h3>"
					+	" 	</div>";
					
					var quarterDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.section.sectionClass.quarter.quarterName.name+" "+elem.section.sectionClass.quarter.year+"</h3>"
					+	"	</div>";
					
					row.append(classNameDiv);
					row.append(gradeDiv);
					row.append(unitDiv);
					row.append(quarterDiv);
					
					$("#GradeInfoDiv").append($(row));
				}
			},
			error: function(data,textStatus){
				alert("Fail to retrieve enrollmentList. Please refresh page");
			}
		});
		
		//display GPA
		$("#GPAInfoDiv").empty();
		$.ajax({
			url:contextPath+"/student/quarter/gpa/"+student_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("quarter GPA list retrieved");
				for ( var idx in data){
					var elem = data[idx];
					
					var row = $("<div class=\"row\"></div>");
					row.append("<div class=\"col-md-4\"></div>");
					
					var gpaDiv = 
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.gpa+"</h3>"
					+	"	</div>";
					
					var quarterDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.quarter.quarterName.name+" "+elem.quarter.year+"</h3>"
					+	"	</div>";
					
					row.append(gpaDiv);
					row.append(quarterDiv);
					
					$("#GPAInfoDiv").append($(row));
				}
			},
			error: function(data,textStatus){
				alert("Fail to retrieve quarterGPAList. Please refresh page");
			}
		});
		
		//display cumulative GPA
		$("#CumulativeGPAInfoDiv").empty();
		$.ajax({
			url:contextPath+"/student/cumulativegpa/"+student_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("cumulative GPA retrieved");
				for ( var idx in data){
					var elem = data[idx];
					
					var row = $("<div class=\"row\"></div>");
					row.append("<div class=\"col-md-4\"></div>");
					
					var cumulativeGpaDiv = 
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem+"</h3>"
					+	"	</div>";
					
					row.append(cumulativeGpaDiv);
					$("#CumulativeGPAInfoDiv").append($(row));
				}
			},
			error: function(data,textStatus){
				alert("Fail to retrieve cumulative GPA. Please refresh page");
			}
		});
	});
});