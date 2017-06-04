$("document").ready(function(){
	
	$("#class").on('change',function(){
		
		var class_id = $("#class option:selected").val();
		
		$.ajax({
			url:contextPath+"/student/class/"+class_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success:function(data, textStatus){
				$("#StudentInfoDiv").empty();
				
				for ( var idx in data){
					var row = $("<div class=\"row\"></div>");
					var col_md_2 = $("<div class=\"col-md-2\"></div>");
					row.append($(col_md_2));
					
					var pidDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].student.pid+"</h3>"
						);
					
					var firstnameDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].student.firstname+"</h3>"
						);
					
					var lastnameDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].student.lastname+"</h3>"
						);
					
					var ssnDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].student.ssn+"</h3>"
						);
					
					var residentDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].student.residentStatus.type+"</h3>"
						);
					
					var unitDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].unitTaken+"</h3>"
						);
					
					var gradeOptionText = data[idx].letter_option ? "Letter" : "SU";
					
					var gradeOptionDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+gradeOptionText+"</h3>"
					);
					
					var quarterDiv = $('<div class=\"col-md-1\"></div>').append(
							"<h3>"+data[idx].section.sectionClass.quarter.quarterName.name+" "+data[idx].section.sectionClass.quarter.year+"</h3>"
						);
					
					row.append($(pidDiv));
					row.append($(firstnameDiv));
					row.append($(lastnameDiv));
					row.append($(ssnDiv));
					row.append($(residentDiv));
					row.append($(unitDiv));
					row.append($(gradeOptionDiv));
					row.append($(quarterDiv));
					$("#StudentInfoDiv").append($(row))
				}
			},
			error: function(data, textStatus){
				alert("Failed to retrieve rosters list. Please refresh page");
			}
		});
	});
});