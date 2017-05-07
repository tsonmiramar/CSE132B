<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Probation Entry"/>
	<jsp:param name="mainJsFile" value="probation_entry.js"/>	
	<jsp:param name="commonfunction" value="commonfunction.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Probation Entry Submission"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/student/probation/add" id="probation_entryForm">
				<div class="form-group row">
					<div class="col-md-6">
						<label>Student</label>
						<select class="form-control" id="student">
							<c:forEach var="student" items="${studentList}">
								<option value="${student.id}"> ${student.firstname} ${student.middlename} ${student.lastname}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group row">
					<label>Probation</label>
					<c:forTokens var="dest" items="From,To" delims=",">
						<div class="col-md-3">
							<label>${dest}</label>
							<select class="form-control" id="quarter${dest}">
								<c:forEach var="quarterName" items="${quarterNameList}">
									<option value="${quarterName.id}">${quarterName.name}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="col-md-3"> 
							<label>Year</label>
							<input class="form-control" type="text" id="year${dest}"/>
						</div>
					</c:forTokens>
				</div>	
				
				<div class="form-group row">
					<label>Reason:</label>
					<div class="col-md-12">
						<textarea rows="5" class="form-control" id="reason"></textarea>
					</div>
				</div>
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>	