<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Remaining Requirement for Master Degree "/>
	<jsp:param name="mainJsFile" value="master_remaining_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Master Degree's Remaining Requirement"/>
    </jsp:include>
    
    <div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<div class="col-md-4">
					<label>Student</label>
					<select class="form-control" id="student">
						<c:forEach var="studentMS" items="${studentMSList}">
							<option value="${studentMS.id}">${studentMS.firstname} ${studentMS.lastname}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-md-4">
					<label>Master Degree</label>
					<select class="form-control" id="degree">
						<c:forEach var="degreeMS" items="${degreeMSList}">
							<option value="${degreeMS.id}">${degreeMS.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
			
			<div class="row">
				<div class="col-offset-2 col-md-8">
					<h3><strong>Completed Concentration:</strong>
						<span id="ConcentrationDiv">
							<c:forEach var="concentration" items="${concentrationCompletedList}">
								${concentration.name} 
							</c:forEach>
						</span>
					</h3>
				</div>
			</div>
			
			<div class="row" id="ConcentrationDiv">
				
			</div>
			
			<div class="row">
				<div class="col-offset-2 col-md-10">
					<h3><strong>Class not yet taken per concentration:</strong></h3>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-4">
					<h3><strong>Concentration</strong></h3>
				</div>
				
				<div class="col-md-3">
					<h3><strong>Class</strong></h3>
				</div>
				
				<div class="col-md-4">
					<h3><strong>Next Quarter Offered</strong></h3>
				</div>
			</div>
			
			<div id="ClassNotYetTakenDiv">
				<c:forEach var="concentrationCourseClass" items="${concentrationCourseClassNotYetTakenList}">
					<div class="row">
						<div class="col-md-4">
							<h4>${concentrationCourseClass.concentration.name}</h4>
						</div>
						
						<div class="col-md-8">
							<c:forEach var="courseClass" items="${concentrationCourseClass.courseClassList}">
								<div class="row">
									<div class="col-md-4">
										<h4>${courseClass.course.courseSubject.symbol} ${courseClass.course.courseUnitNumber.currNum}</h4>
									</div>
									
									<div class="col-md-6">
										<h4>${courseClass.quarter.quarterName.name} ${courseClass.quarter.year}</h4>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>	
				</c:forEach>
			</div>
	</div>
</body>
</html>