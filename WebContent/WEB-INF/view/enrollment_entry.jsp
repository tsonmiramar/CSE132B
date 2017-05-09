<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Enrollment Entry"/>
	<jsp:param name="mainJsFile" value="enrollment_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Enrollment Entry"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/class/enrollment/add" id="enrollment_entryForm">
				<div class="form-group row">
					<c:forTokens var="identity" items="student" delims=",">
						<div class="col-md-6">
							<label style="text-transform:capitalize">${identity }</label>
							<select class="form-control" id="${identity}"></select>
						</div>
					</c:forTokens>
					
					<div class="col-md-6">
						<label>Quarter:</label>
						<h4>Spring 2017</h4>
					</div>
				</div>
				
				<div class="form-group row">
					<c:forTokens var="identity" items="class,section" delims=",">		
						<div class="col-md-4">
							<label style="text-transform:capitalize">${identity}</label>
							<select class="form-control" id="${identity}"></select>
						</div>
					</c:forTokens>
					
					<div class="col-md-4">
						<div id="unitDiv">
							<label>Unit</label>
							<input type="text" class="form-control" id="unit" placeholder="Enter unit taken"/>
						</div>
					</div>
				</div>
				
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>