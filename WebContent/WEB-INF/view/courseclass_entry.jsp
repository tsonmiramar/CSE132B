<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
    <jsp:param name="title" value="Class Entry"/>
    <jsp:param name="mainJsFile" value="courseclass_entry.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Class Entry Form"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form id="courseclasss_entryForm" method="POST" action="${pageContext.request.contextPath}/class/add">
				<div class="form-group row">
					<div class="col-md-4">
						<label>Course</label>
						<select class="form-control" id="course_id">
							<c:forEach var="course" items="${courseList}">
								<option value="${course.id}">${course.courseSubject.symbol} ${course.courseUnitNumber.currNum}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-4">
						<label>Class Title</label>
						<input class="form-control" id="title" placeholder="Add a class title"/>
					</div>
					
					<div class="col-md-4">
						<label>Class Quarter</label>
						<select class="form-control" id="quarter_id">
							<c:forEach var="quarter" items="${quarterList}">
								<option value="${quarter.id}">${quarter.quarterName.name} ${quarter.year}</option>
							</c:forEach>
						</select>
					</div>	
				</div>
				
				<div id="SectionList">
				</div>
			
				<div class="form-group">
					<button type="button" class="btn btn-primary pull-left" id="AddSectionBtn">Add Class Section</button>
					<button type="submit" class="btn btn-submit pull-right" id="SubmitBtn">Submit</button>
				</div>
				
			</form>
		</div>
	</div>
</body>
</html>