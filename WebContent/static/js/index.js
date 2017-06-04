$('document').ready(function(){
	
	//Display subject Id based on department Id
	$('#course_department').change(function(){
		var departmentId = $('#course_department option:selected').val();
		
		$.ajax({
			url: 'CourseSubjectController',
			type: 'GET',
			data: { command: 'GET_SUBJECT_BY_DEPARTMENT',
					department_id: departmentId 
			},
			success: function(data){
				var subjectObj = JSON.parse(data);
				
				$('#subject_id').empty();
				$.each(subjectObj,function(key,val){
					var opt = '<option value="'+ key +'">' + val + "</option>";
					$('#subject_id').append(opt);
				});
			},
			error: function(){
				alert('Failed loading subjects for the selected department');
			}
		});
	});
});
