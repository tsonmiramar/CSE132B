$('document').ready(function(){
	
	//Submit form event
	$('#faculty_entryForm').submit(function(e){
		e.preventDefault();
		
		var faculty = {
				'name' : $("#name").val(),
				'title': $("#title").val(),
				'department': {
					'id': $("#department option:selected").val()
				}
		};
		
		$.ajax({
			url:$('#faculty_entryForm').attr('action'),
			type: "POST",
			data:JSON.stringify(faculty),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new faculty");
			},
			error: function(data, textStatus){
				alert("Failed to insert faculty. Please retry");
			}
		});	
	});
});