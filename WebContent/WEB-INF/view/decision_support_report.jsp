<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Decision Support Report"/>
	<jsp:param name="mainJsFile" value="decision_support_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Decision Support Report"/>
    </jsp:include>
    <div class="row">
	   <div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<div class="col-md-4">
					<label>Course</label>
					<select class="form-control" id="course">
						<c:forEach var="course" items="${courseList}">
							<option value="${course.id}">${course.courseSubject.symbol} ${course.courseUnitNumber.currNum}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-md-4">
					<label>Faculty</label>
					<select class="form-control" id="faculty">
						<c:forEach var="faculty" items="${facultyList}">
							<option value="${faculty.id}">${faculty.name}, ${faculty.title}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-md-4">
				
					<label>Quarter</label>
					<select class = "form-control" id="quarter">
						<c:forEach var="quarter" items="${quarterList}">
							<option value="${quarter.id}">${quarter.quarterName.name} ${quarter.year}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		
			<div class="row">
				<div class="col-md-8">
					<h3>Grade Count by Quarter:</h3>
				</div>
			</div>
			
			<div class="row">
				<c:forTokens items="Grade,Count" delims="," var="title">
					<div class="col-md-4">
						<h3>${title}</h3>
					</div>
				</c:forTokens>
			</div>
			
			<div id="GradeCountByQuarterDiv">
				<c:forEach var="gradeCount" items="${gradeCountFacultyCourseQuarterList}">
					<div class="row">
						<div class="col-md-4">
							<h4>${gradeCount.grade}</h4>
						</div>
						
						<div class="col-md-4">
							<h4>${gradeCount.count}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<h3>Grade Count Over Years:</h3>
				</div>
			</div>
			
			<div id="GradeCountOverYearDiv">
				<c:forEach var="gradeCount" items="${gradeCountFacultyCourseList}">
					<div class="row">
						<div class="col-md-4">
							<h4>${gradeCount.grade}</h4>
						</div>
						
						<div class="col-md-4">
							<h4>${gradeCount.count}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<h3>Grade Count by Course Over Years:</h3>
				</div>
			</div>
			
			<div id="GradeCountByCourseOverYearDiv">
				<c:forEach var="gradeCount" items="${gradeCountByCourseList}">
					<div class="row">
						<div class="col-md-4">
							<h4>${gradeCount.grade}</h4>
						</div>
						
						<div class="col-md-4">
							<h4>${gradeCount.count}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="col-md-8">
					<h3>GPA of Course X by Professor Y Over Years:</h3>
				</div>
			</div>
			
			<div id="GradeGPAOverYearDiv">
				<c:forEach var="gpa" items="${gradeGPAFacultyCourseList}">
					<div class="row">
						<div class="col-md-4">
							<h4>${gpa}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>	