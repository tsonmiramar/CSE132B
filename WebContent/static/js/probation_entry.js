$('document').ready(function(){
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
	
	$("#probation_entryForm").submit(function(e){
		e.preventDefault();
		
		var studentProbation = [];
		
		//Compute attendance quarter
		var quarterFrom = parseInt($("#quarterFrom").val());
		var quarterTo = parseInt($("#quarterTo").val());
		var yearFrom = parseInt($("#yearFrom").val());
		var yearTo = parseInt($("#yearTo").val());
		var quarterProbationList = computeQuarterList(quarterFrom, yearFrom, 
												   quarterTo, yearTo, 
												   quarterJSON, quarterNameJSON);
		
		
		for ( var idx in quarterProbationList){
			studentProbation.push({
				'studentProbation' : {
					'id': $("#student option:selected").val()
				},
				'quarterProbation': quarterProbationList[idx],
				'reason': $('#reason').val()
			})
		}
		
		//Submit form
		$.ajax({
			url:$("#probation_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(studentProbation),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new probation");
				
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
				alert("Failed to insert probation. Please retry");
			}
		});
	});
});