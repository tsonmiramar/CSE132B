$("document").ready(function(){
	
	$("#section").on('change',function(){
		var section_id = $("#section option:selected").val();
		var dayFrom_id = $("#dayFrom option:selected").val();
		var dayTo_id = $("#dayTo option:selected").val();
		
		displayReviewSessionReport(section_id,dayFrom_id,dayTo_id);
	});
	
	$("#dayFrom").on('change',function(){
		var section_id = $("#section option:selected").val();
		var dayFrom_id = $("#dayFrom option:selected").val();
		var dayTo_id = $("#dayTo option:selected").val();
		
		displayReviewSessionReport(section_id,dayFrom_id,dayTo_id);
	});
	
	$("#dayTo").on('change',function(){
		var section_id = $("#section option:selected").val();
		var dayFrom_id = $("#dayFrom option:selected").val();
		var dayTo_id = $("#dayTo option:selected").val();
		
		displayReviewSessionReport(section_id,dayFrom_id,dayTo_id);
	});
});

var displayReviewSessionReport = function(section_id,dayFrom_id,dayTo_id){
	$("#reviewSessionDiv").empty();
	
	$.ajax({
		url:contextPath+"/section/meeting/availablereviewsession/"+section_id+"/"+dayFrom_id+"/"+dayTo_id,
		type: "GET",
		headers: {
			'Content-Type':'application/json'
		},
		success:function(data){
			console.log("Review Session Retrieved!");
			for ( var idx in data) {
				
				$("#reviewSessionDiv").append(
						"<div class=\"row\">"
						+	"<div class=\"col-md-4\">"
						+	"	<h4>"+data[idx].weekday+"</h4>"
						+	"</div>"
							
						+	"<div class=\"col-md-4\">"
						+	"	<h4>"+data[idx].startTime+"</h4>"
						+	"</div>"
							
						+	"<div class=\"col-md-4\">"
						+	"<h4>"+data[idx].endTime+"</h4>"
						+	"</div>"
					+	"</div>"		
				);
			}
		},
		error: function(data){
			alert('Failed retrieving review session data. Please refresh page');
		}
	});
}