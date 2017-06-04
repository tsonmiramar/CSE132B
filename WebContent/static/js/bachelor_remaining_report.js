$("document").ready(function(){
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		var degree_id = $("#degree option:selected").val();
		displayRemainingUnitRequire(student_id,degree_id);
	});
	
	$("#degree").on('change',function(){
		var student_id = $("#student option:selected").val();
		var degree_id = $("#degree option:selected").val();
		displayRemainingUnitRequire(student_id,degree_id);
	});
});

var displayRemainingUnitRequire = function(student_id,degree_id){
	$.ajax({
		url:contextPath+"/degree/bachelorremaining/"+student_id+"/"+degree_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("Degree Remaining List retrieved");
			$("#degreeRemainingDiv").empty();
			$("#totalRemainingUnit").empty();
			var totalRemainingUnit = 0;
			for (var idx in data){
				var row = $("<div class=\"row\"></div>");
				row.append(  "<div class=\"col-md-2\"></div>"
							+"<div class=\"col-md-4\">"
							+"	<h4>"+data[idx].name+"</h4>"
							+"</div>"
							+"<div class=\"col-md-4\">"
							+"	<h4>"+data[idx].remainingUnit+"</h4>"
							+"</div>"
						  );
				totalRemainingUnit+=data[idx].remainingUnit;
				$("#degreeRemainingDiv").append($(row));
				$("#totalRemainingUnit").text(totalRemainingUnit);
			}
		},
		error:function(data){
			console.log("Failed retrieving degree remaining unit. Please retry");
		}
	});
}