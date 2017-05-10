<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Past Enrollment Entry"/>
	<jsp:param name="mainJsFile" value="pastenrollment_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Past Enrollment Entry"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/class/enrollment/add" id="pastenrollment_entryForm">
				<div class="form-group row">
					<c:forTokens var="identity" items="student,quarter" delims=",">
						<div class="col-md-6">
							<label style="text-transform:capitalize">${identity }</label>
							<select class="form-control" id="${identity}"></select>
						</div>
					</c:forTokens>
				</div>
				
				<div class="form-group row">
					<c:forTokens var="identity" items="class,section" delims=",">		
						<div class="col-md-3">
							<label style="text-transform:capitalize">${identity}</label>
							<select class="form-control" id="${identity}"></select>
						</div>
					</c:forTokens>
					
					<c:forTokens var="identity" items="unit,grade" delims=",">
						<div class="col-md-3" id="${identity}Div">
							<label style="text-transform:capitalize">${identity}</label>
							<input type="text" class="form-control" id="${identity}" placeholder="Enter ${identity}"/>
						</div>
					</c:forTokens>
				</div>
				
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>