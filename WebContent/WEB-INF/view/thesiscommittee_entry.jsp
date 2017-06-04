<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Thesis Committee Submission"/>
	<jsp:param name="mainJsFile" value="thesiscommittee_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Thesis Committee Submission"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/committee/add" id="thesiscommittee_entryForm">	
				<div class="form-group row">
					<label>Student</label>
					<select class="form-control" id="student">
						<c:forEach var="student" items="${gradList}">
							<option value="${student.id}">${student.firstname} ${student.lastname} ${student.middlename}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="form-group row" id="committeeGrad">
						<label>Thesis Committee</label>
						<select id="faculty" class="form-control">
							<c:forEach var="faculty" items="${facultyList}">
								<option value="${faculty.id}">${faculty.name},${faculty.title}</option>
							</c:forEach>
						</select>
						<button type="button" class="btn btn-primary" id="AddFacultyBtn">Add Faculty</button>
						<div id="GradCommitteeList"><!-- populated with faculty --></div>
				</div>
				
				<div id="committeePhD" style="display:none">
					<div class="form-group row">
						<label>Thesis PhD Committee</label>
					</div>
					<div class="form-group row">		
						<label>Department</label>
						<select id="department" class="form-control">
							<c:forEach var="department" items="${departmentList}">
								<option value="${department.id}">${department.name}</option>
							</c:forEach>
						</select>
					</div>	
					<div class="form-group row">
						<label>Faculty</label>
						<select id="faculty" class="form-control">
							<c:forEach var="faculty" items="${facultyList2}">
								<option value="${faculty.id}">${faculty.name},${faculty.title}</option>
							</c:forEach>
						</select>
						<button type="button" class="btn btn-primary" id="AddFacultyBtn">Add Faculty</button>							
						<div id="PhDCommitteeList"><!-- populated with faculty --></div>
					</div>
				</div>
				
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
<script type="text/javascript">
	var phdCandidateJSON = ${phdJSON};
	var gradStudentJSON = ${gradJSON};
	console.log( gradStudentJSON );
</script>
</html>