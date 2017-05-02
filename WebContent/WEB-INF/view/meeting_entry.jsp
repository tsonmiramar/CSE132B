<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h4>New Meeting
	<button class="pull-right" type="button" class="btn btn-primary" id="removeMeetingBtn">
		<span class="glyphicon glyphicon-remove"></span>
	</button>
</h4>
<label>Meeting Type</label>
<c:forTokens var="meetingType" items="Lecture,Lab,Discussion" delims=",">
	<div class="radio-inline">
		<c:choose>
			<c:when test="${meetingType == 'Lecture'}">
				<input type="radio" name="meetingType${meetingCounter}" id="${meetingType }_meeting" checked/> ${meetingType}
			</c:when>
			<c:otherwise>
				<input type="radio" name="meetingType${meetingCounter}" id="${meetingType }_meeting" /> ${meetingType}
			</c:otherwise>						
		</c:choose>
	</div>
</c:forTokens>

<div class="form-group">
	<label class="checkbox-inline" id="checkboxDiv" style="display:none">
			<input type="checkbox" id="discussionRequired" value="false"/> 
			Mandatory Attendance ?
	</label>
</div>
<div class="form-group row">
	<div class="col-md-4">
		<label> Weekday </label>
		<select class="form-control" id="weekday">
			<c:forTokens var="day" items="Monday,Tuesday,Wednesday,Thursday,Friday" delims=",">
				<option value="${day}">${day} </option>
			</c:forTokens>
		</select>
	</div>
	
	<div class="col-md-4">
		<label>Start Time</label>
		<input class="form-control" type="time" id="startTime" />
	</div>
	
	<div class="col-md-4">
		<label>End Time</label>
		<input class="form-control" type="time" id="endTime" />
	</div>
</div>

<div class="form-group row">
	<div class="col-md-4">
		<label>Building</label>
		<input class="form-control" type="text" id="building" placeholder="Enter Building"/>
	</div>
	
	<div class="col-md-4">
		<label>Room</label>
		<input class="form-control" type="text" id="room" placeholder="Enter Room"/>
	</div>
</div>