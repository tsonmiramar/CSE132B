<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
    <jsp:param name="title" value="Course Entry"/>
    <jsp:param name="mainJsFile" value="course_entry.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
    	<jsp:param name="entry_name" value="Course Entry Form"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form id="course_entryForm" method="POST" action="${pageContext.request.contextPath}/course/add">
				<div class="form-group">
					<label>Course Subject</label>
					<select class="form-control" id="subject_id">
						<c:forEach var="subject" items="${subjectList}">
							<option value="${subject.subject_id}">${subject.symbol}</option>
						</c:forEach>
					</select>
					
					<label>Course Number:</label>
					<input class="form-control" type="text" id="currNum" placeholder="Enter a course number"/>
				</div>
				
				<div class="form-group">
					<label>Course Units</label>
					<c:forTokens var="optionName" items="Single,Range" delims=",">
						<label class="radio-inline">
							<c:choose>
								<c:when test="${optionName == 'Single'}">
									<input type="radio" name="UnitOption" id="${optionName}_unitOpt" checked/>${optionName} Units Option
								</c:when>
								<c:otherwise>
									<input type="radio" name="UnitOption" id="${optionName}_unitOpt"/>${optionName} Units Option
								</c:otherwise>						
							</c:choose>
						</label>
					</c:forTokens>		
					
					<input class="form-control" id="minUnitInput" type="number" name="unitFrom" placeholder="Enter minimum units" value="-1"/>
					<input class="form-control" id="maxUnitInput" type="number" name="unitTo" placeholder="Enter maximum units"/>		
				</div>
				
				<div class="form-group">
					<label>Grade Options</label>
						<c:forTokens var="optionName" items="letter,SU" delims=",">
							<label class="checkbox-inline">
								<input type="checkbox" id="${optionName}Opt" value="false"/> ${optionName} Option
							</label>
						</c:forTokens>
				</div>
				
				<div class="form-group">
					<label class="checkbox-inline">
						<input type="checkbox" id="labwork" value="false"/> Lab Work Required ?
					</label>
				</div>
				
				<div class="form-group">
					<label>Course Prerequisites:</label>
					<div class="checkbox">
						<label><input type="checkbox" id="instConsent" value="false"/>Required Consent of Instructor?</label>
					</div>
					
					<div class="input-group">
						<select id="prereg_id" class="form-control">
							<c:forEach var="course" items="${courseList}">
								<option value="${course.id}">${course.courseSubject.symbol} ${course.courseUnitNumber.currNum}</option>
							</c:forEach>
						</select>
						<span class="input-group-btn">
							<button type="button" class="btn btn-primary" id="AddPrereqBtn">Add Prerequisite</button>
						</span>
					</div>
					
					<div class="form-group">
						<label>Prerequisite List:</label>
						<div id="PrereqList" >
							<!-- list of hidden input per course will be populated here -->
						</div>
					</div>
					
					<div class="form-group">
						<button type="submit" class="btn btn-submit" id="SubmitBtn">Submit</button>
					</div>
				</div>	
			</form>
		</div>
	</div>
</body>
</html>