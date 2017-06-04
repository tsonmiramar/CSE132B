<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Class Conflict Current Schedule "/>
	<jsp:param name="mainJsFile" value="class_conflict_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Class Conflict Current Quarter Schedule"/>
    </jsp:include>
    <div class="row">
	   <div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<div class="col-md-4">
					<label>Student</label>
					<select class="form-control" id="student">
						<c:forEach var="student" items="${studentList}">
							<option value="${student.id}">${student.firstname} ${student.lastname}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="row">
				<div class="col-md-3">
					<h3><strong>Class Cannot Take:</strong></h3>
				</div>
				
				<div class="col-md-3">
					<h3><strong>Title</strong></h3>
				</div>
				
				<div class="col-md-3">
					<h3><strong>Conflict With Class:</strong></h3>
				</div>
				
				<div class="col-md-3">
					<h3><strong>Title</strong></h3>
				</div>
			</div>
		
			<div id="ClassConflictDiv">
				<c:forEach var="classConflict" items="${courseClassConflictList}">
					<div class="row">
						<div class="col-md-3">
							<h4>${classConflict.classCannotTake.course.courseSubject.symbol} ${classConflict.classCannotTake.course.courseUnitNumber.currNum}</h4>
						</div>
						
						<div class="col-md-3">
							<h4>${classConflict.classCannotTake.title}</h4>
						</div>
						
						<div class="col-md-3">
							<h4>${classConflict.classConflictWith.course.courseSubject.symbol} ${classConflict.classConflictWith.course.courseUnitNumber.currNum}</h4>
						</div>
						
						<div class="col-md-3">
							<h4>${classConflict.classConflictWith.title}</h4>
						</div>
					</div>
				</c:forEach>
			</div>		
			
		</div>	
	</div>
	</body>
</html>