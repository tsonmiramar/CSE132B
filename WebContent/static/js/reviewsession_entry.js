var appendSectionItem = function(selector,itemList){
	if (selector.is(":empty")){
		for ( var idx in itemList ){
			var text = itemList[idx].id;
			var option = new Option(text,text);
			selector.append(option);
		}
		
	}
}

var appendDaySelectionItem = function(selector, month){
	selector.empty();
	for ( var i = 0; i < daysInMonth(month,2017); i++){
		var option = new Option(i+1,i+1);
		selector.append($(option));
	}
}

//Month is 1 based
function daysInMonth(month,year) {
    return new Date(year, month, 0).getDate();
}

//parse a date in yyyy-mm-dd format
function parseDate(input) {
  var parts = input.match(/(\d+)/g);
  return new Date(parts[0], parts[1]-1, parts[2]); // months are 0-based
}

$('document').ready(function(){
	
	$("#weekday").parent().remove();
	
	var NUM_MONTH = 12;
	var dayArray = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];
	var monthArray = ["Jan","Feb","Mar","Apr", "May", "Jun", "Jul", "Aug","Sep", "Oct", "Nov", "Dec"];
	
	//Load corresponding section id based to selected class
	$("#class").on('change',function(){
		var id = $("#class option:selected").val().toString();
		
		$("#section").empty();
		appendSectionItem($("#section"),courseClassJSON[id].sectionList)
	});
	
	//Form Submission
	$("#reviewsession_entryForm").submit(function(e){
		e.preventDefault();
		
		var reviewSession = {
			"section" : {
				"id" : $("#section option:selected").val()
			},
			"startTime" : $("#startTime").val(),
			"endTime" : $("#endTime").val(),
			"building" : $("#building").val(),
			"room" : $("#room").val()
		};
		
		//Compute month and day
		var date = parseDate($("#reviewDate").val());
		
		reviewSession['month'] = monthArray[date.getMonth()];
		reviewSession['day'] = date.getDate();
		reviewSession['weekday'] = dayArray[date.getDay()];
		
		console.log(reviewSession);
		
		$.ajax({
			url:$("#reviewsession_entryForm").attr('action'),
			type: "POST",
			data:JSON.stringify(reviewSession),
			headers: {
				'Content-Type':'application/json'
			},
			success: function(data, textStatus){
				alert("Successfully insert a new review session");
			},
			error: function(data, textStatus){
				alert("Failed to insert review session. Please retry");
			}
		});
	});
});