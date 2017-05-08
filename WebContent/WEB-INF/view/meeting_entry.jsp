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

<jsp:include page="basemeeting_entry.jsp"></jsp:include>