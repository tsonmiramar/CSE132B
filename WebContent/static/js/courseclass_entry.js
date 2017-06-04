$('document').ready(function(){
	
	//Add section btn event handler
	$('#AddSectionBtn').on('click',function(){
		var newSectionDiv = $('<div id=\"section\"></div>');
		newSectionDiv.load(contextPath+"/section/entry");
		$('#SectionList').append(newSectionDiv);
	});
	
	//Add meeting btn event handler
	$('#SectionList').on('click','#AddMeetingBtn',function(){
		var newMeetingDiv = $('<div id=\"meeting\"></div>');
		newMeetingDiv.load(contextPath+"/section/meeting/entry");
		$(this).closest('#section').children('#MeetingList').append(newMeetingDiv);
	});
	
	//Remove section btn event handler
	$('#SectionList').on('click','#removeSectionBtn',function(){
		$(this).closest("#section").remove();
	});
	
	//Remove meeting btn event handler
	$('#SectionList').on('click','#removeMeetingBtn', function(){
		$(this).closest("#meeting").remove();
	});
	
	//Checkbox event handler
	$('#SectionList').on('click', "#section #meeting input[type='checkbox']", function(){
		if ($(this).is(":checked")){
			$(this).val("true");
		}
		else{
			$(this).val("false");
		}
	});
	
	//Radio button event handler
	$('#SectionList').on('click', "#section #meeting input[type='radio']",function(){
		
		if ($(this).attr('id') === 'Discussion_meeting'){
			var checkboxDiv = $(this).closest('#meeting').find('#checkboxDiv');
			checkboxDiv.show();
		}
		else{
			var checkboxDiv = $(this).closest('#meeting').find('#checkboxDiv');
			checkboxDiv.hide();
		}
	});
	
	//Class form submission handler
	$('#courseclasss_entryForm').submit(function(e){
		e.preventDefault();
		
		var courseClass = {
				'course':{
					'id': $('#course_id option:selected').val(),
				},
				'title': $('#title').val(),
				'quarter':{
					'id': $('#quarter_id option:selected').val()
				},
				'sectionList': [],
		};
		
		if (!$('#SectionList').is(':empty')){
			$('#SectionList').children('#section').each(function(){
				var section = {
					'faculty':{
						'id': $(this).find('#faculty_id option:selected').val()
					},
					'enrollmentLimit': $(this).find('#enroll_limit').val(),
					'reviewSessionList': [],
					'discussionList': [],
					'nondiscussionList': []
				};
				
				if (!$(this).children('#MeetingList').is(':empty')){
					$(this).find('#MeetingList #meeting').each(function(){
						
						var meeting = {
								'startTime': $(this).find('#startTime').val(),
								'endTime': $(this).find('#endTime').val(),
								'weekday': $(this).find('#weekday option:selected').val(),
								'room': $(this).find('#room').val(),
								'building': $(this).find('#building').val()
						};
						
						
						if ( $(this).find('#Lecture_meeting').is(':checked')){
							meeting['type'] = 'LE';
							section['nondiscussionList'].push(meeting);
						}
						else if ( $(this).find('#Lab_meeting').is(':checked')){
							meeting['type'] = 'LA';
							section['nondiscussionList'].push(meeting);
						}
						else {
							meeting['required'] = $('#discussionRequired').val();
							section['discussionList'].push(meeting);
						}
					});
				}
				
				courseClass['sectionList'].push(section);
			});
		}
		
		var formURL = $('#courseclasss_entryForm').attr("action");
		$.ajax({
			url:formURL,
			type: "POST",
			data:JSON.stringify(courseClass),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new class");
			},
			error: function(data, textStatus){
				alert("Failed to insert class. Please retry");
			}
		});
	});
});