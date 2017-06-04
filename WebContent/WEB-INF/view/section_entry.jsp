<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>
	New Section
	<button class="pull-right" type="button" class="btn btn-primary" id="removeSectionBtn">
		<span class="glyphicon glyphicon-remove"></span>
	</button>
</h3>

<div class="form-group row">
	<div class="col-md-4">
		<label>Enrollment Limit</label>
		<input class = "form-control" type="number" id="enroll_limit" placeholder="Enter enrollment number limit"/>
	</div>
	
	<div class="col-md-4">
		<label> Choose Instructor</label>
		<select class="form-control" id="faculty_id">
			<c:forEach var="faculty" items="${facultyList}">
				<option value="${faculty.id}">${faculty.name}, ${faculty.title}</option>
			</c:forEach>
		</select>
	</div>
</div>

<div id="MeetingList">
</div>

<div class="form-group">
	<button type="button" class="btn btn-primary" id="AddMeetingBtn">Add Section Meeting</button>
</div>