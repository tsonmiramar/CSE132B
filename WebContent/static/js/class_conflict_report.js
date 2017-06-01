$("document").ready(function(){
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		$("#ClassConflictDiv").empty();
		$.ajax({
			url:contextPath+"/class/classcannottake/"+student_id,
			type: "GET",
			headers: {
				'Content-Type':'application/json'
			},
			success:function(data){
				for ( var idx in data){
					$("#ClassConflictDiv").append(
						"<div class=\"row\">"
							+"<div class=\"col-md-3\">"
								+"<h4>"+data[idx].classCannotTake.course.courseSubject.symbol+" "+data[idx].classCannotTake.course.courseUnitNumber.currNum+"</h4>"
							+"</div>"
							
							+"<div class=\"col-md-3\">"
								+"<h4>"+data[idx].classCannotTake.title+"</h4>"
							+"</div>"
							
							+"<div class=\"col-md-3\">"
								+"<h4>"+data[idx].classConflictWith.course.courseSubject.symbol+" "+data[idx].classConflictWith.course.courseUnitNumber.currNum+"</h4>"
							+"</div>"
							
							+"<div class=\"col-md-3\">"
								+"<h4>"+data[idx].classConflictWith.title+"</h4>"
							+"</div>"
						+"</div>"
					);
				}
			},
			error:function(data){
				alert("Failed Retrieve Class Conflict, please refresh page");
			}
		});
	});
});