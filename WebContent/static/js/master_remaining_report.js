$("document").ready(function(){
	$("#student").on('change',function(){
		var student_id = $("#student option:selected").val();
		var degree_id = $("#degree option:selected").val();
		displayConcentrationReport(student_id,degree_id);
	});
	
	$("#degree").on('change',function(){
		var student_id = $("#student option:selected").val();
		var degree_id = $("#degree option:selected").val();
		displayConcentrationReport(student_id,degree_id);
	});
});

var displayConcentrationReport = function(student_id,degree_id){
	$("#ConcentrationDiv").empty();
	$("#ClassNotYetTakenDiv").empty();
	
	$.ajax({
		url:contextPath+"/degree/masterremaining/classnotyettakenperconcentration/"+student_id+"/"+degree_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("class not yet taken per concentration retrieved");
			for ( var idx in data){
				var row = $(
						 "<div class=\"row\">"
						+"	<div class=\"col-md-4\">"
						+		"<h4>"+data[idx].concentration.name+"</h4>"
						+"	</div>"
						+"</div>"
						);
				
				var courseClassCol = $("<div class=\"col-md-8\"></div>");
				
				for ( var jdx in data[idx].courseClassList ){
					courseClassCol.append(
						 "<div class=\"row\">" 
						+"	<div class=\"col-md-4\">"
						+"		<h4>"+data[idx].courseClassList[jdx].course.courseSubject.symbol
									 +" "
									 +data[idx].courseClassList[jdx].course.courseUnitNumber.currNum
						+"		</h4>"
						+" 	</div>"
						+"  <div class=\"col-md-6\">"
						+" 		<h4>"+data[idx].courseClassList[jdx].quarter.quarterName.name
									 +" "
									 +data[idx].courseClassList[jdx].quarter.year
									 +"</h4>"
						+"  </div>"
						+"</div>"
					);
				}
				
				row.append($(courseClassCol));
				$("#ClassNotYetTakenDiv").append($(row));
			}
		},
		error:function(data){
			alert("Failed retrieving class per concentration! Please refresh page")
		}
	});
	
	$.ajax({
		url:contextPath+"/degree/masterremaining/completedconcentration/"+student_id+"/"+degree_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("Completed concentration retrieved");
			for ( var idx in data){
				$("#ConcentrationDiv").append(data[idx].name+" ");
			}
		},
		error:function(data){
			alert("Failed retrieved completed concentration");
		}
	});
}