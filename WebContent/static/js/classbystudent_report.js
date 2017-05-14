$('document').ready(function(){
	
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		
		$.ajax({
			url:contextPath+"/class/list/"+student_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				console.log("course class list retrieved");
				$("#ClassInfoList").empty();
				for ( var idx in data){
					var elem = data[idx];
					var classNameDiv = 
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.course.courseSubject.symbol+" "+elem.course.courseUnitNumber.currNum+"</h3>"
					+	"	</div>";
					
					var unitDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.course.courseUnitNumber.unitTo+"</h3>"
					+	"	</div>";
					
					var sectionDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.sectionList[0].id+"</h3>"
					+	"	</div>";
					
					var facultyDiv =
						"	<div class=\"col-md-2\">"
					+	"		<h3>"+elem.sectionList[0].faculty.name+"</h3>"
					+	"	</div>";
					
					var enrollDiv =
						"	<div class=\"col-md-2\">"
						+	"		<h3>"+elem.sectionList[0].enrollmentLimit+"</h3>"
						+	"	</div>";
					
					var row = $("<div class=\"row\"></div>");
					row.append(classNameDiv);
					row.append(unitDiv);
					row.append(sectionDiv);
					row.append(facultyDiv);
					row.append(enrollDiv);
					
					console.log(row);
					$("#ClassInfoList").append($(row));
				}
			},
			error: function(data, textStatus){
				alert("Failed to retrieve course class list. Please refresh page");
			}
		});
	});
});
