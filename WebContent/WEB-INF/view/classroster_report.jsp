<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Rosters of Class "/>
	<jsp:param name="mainJsFile" value="classroster_report.js"/>
</jsp:include>
	<body class="container-fluid">
		<jsp:include page="entry_header.jsp">
	       <jsp:param name="entry_name" value="Roster of Class"/>
	   </jsp:include>
	   <div class="row">
			<div class="col-md-offset-3 col-md-6">
				<div class="form-group row">
					<label>Class</label>
					<select class="form-control" id="class">
						<c:forEach var="courseClass" items="${courseClassList}">
							<option value="${courseClass.id}">${courseClass.course.courseSubject.symbol} ${courseClass.course.courseUnitNumber.currNum},${courseClass.quarter.quarterName.name} ${courseClass.quarter.year}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-2"></div>	
			<c:forEach var="item" items="Pid,First,Last,SSN,Resident,Unit,Grade,Quarter">
				<div class="col-md-1">
					<h3><strong>${item}</strong></h3>
				</div>
			</c:forEach>
		</div>
		
		<div id="StudentInfoDiv">
			<c:forEach var="enrollment" items="${enrollmentList}">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-1">
						<h3>${enrollment.student.pid}</h3>
					</div>
					
					<div class="col-md-1">
						<h3>${enrollment.student.firstname}</h3>
					</div>
					
					<div class="col-md-1">
						<h3>${enrollment.student.lastname}</h3>
					</div>
					
					<div class="col-md-1">
						<h3>${enrollment.student.ssn}</h3>
					</div>
					
					<div class="col-md-1">
						<h3>${enrollment.student.residentStatus.type}</h3>
					</div>
					
					<div class="col-md-1">
						<h3>${enrollment.unitTaken}</h3>
					</div>
					<div class="col-md-1">
						<c:choose>
							<c:when test="${enrollment.letter_option}">
								<h3>Letter</h3>
							</c:when>
							<c:otherwise>
								<h3>SU</h3>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="col-md-1">
						<h3>${enrollment.section.sectionClass.quarter.quarterName.name} ${enrollment.section.sectionClass.quarter.year}</h3>
					</div>
				</div>
			</c:forEach>
		</div>		
	</body>
</html>
