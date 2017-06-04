<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Student's Grade Report"/>
	<jsp:param name="mainJsFile" value="studentgrade_report.js"/>
</jsp:include>
	<body class="container-fluid">
		<jsp:include page="entry_header.jsp">
	       <jsp:param name="entry_name" value="Student's Grade Report"/>
	   </jsp:include>
	   <div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="form-group row">
					<label>Student</label>
					<select class="form-control" id="student">
						<c:forEach var="student" items="${studentList}">
							<option value="${student.id}">${student.firstname} ${student.lastname} ${student.middlename}, ${student.ssn}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2"></div>	
			<c:forEach var="item" items="Class,Grade,Unit,Quarter">
				<div class="col-md-2">
					<h3><strong>${item}</strong></h3>
				</div>
			</c:forEach>
		</div>
		
		
		<div id="GradeInfoDiv">
			<c:forEach var="enrollment" items="${enrollmentList}">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-2">
						<h3>${enrollment.section.sectionClass.course.courseSubject.symbol} ${enrollment.section.sectionClass.course.courseUnitNumber.currNum}</h3>
					</div>
					
					<div class="col-md-2">
						<h3>${enrollment.grade}</h3>
					</div>
					
					<div class="col-md-2">
						<h3>${enrollment.unitTaken}</h3>
					</div>
					
					<div class="col-md-2">
						<h3>${enrollment.section.sectionClass.quarter.quarterName.name} ${enrollment.section.sectionClass.quarter.year}</h3>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="col-md-4"></div>	
			<c:forEach var="item" items="GPA,Quarter">
				<div class="col-md-2">
					<h3><strong>${item}</strong></h3>
				</div>
			</c:forEach>
		</div>
		<div id="GPAInfoDiv">
			<c:forEach var="quarterGPA" items="${quarterGPAList}">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-2">
						<h3>${quarterGPA.gpa}</h3>
					</div>
					
					<div class="col-md-2">
						<h3>${quarterGPA.quarter.quarterName.name} ${quarterGPA.quarter.year}</h3>
					</div>
				</div>
			</c:forEach>
		</div>
		
		<div class="row">
			<div class="col-md-4"></div>	
			<c:forTokens items="Cumulative GPA" delims="," var="item">
				<div class="col-md-4">
					<h3><strong>${item}</strong></h3>
				</div>
			</c:forTokens>
		</div>
		
		<div id="CumulativeGPAInfoDiv">
			<c:forEach var="cumulativeGPA" items="${cumulativeGPAList}">
				<div class="row">
					<div class="col-md-4"></div>
					<div class="col-md-4">
						<h3>${cumulativeGPA}</h3>
					</div>
				</div>
			</c:forEach>
		</div>
	</body>
</html>