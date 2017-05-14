<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- JSTL CoreTag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
    <jsp:param name="title" value="Report Home"/>
    <jsp:param name="mainJsFile" value="report_index.js"/>
</jsp:include>
</head>
<body class="container-fluid">
<header class="row">
	<div class="col-md-offset-3 col-md-6">
		<h1>Welcome to TritonLink Management System</h1>
	</div>
</header>
<div class="row">
	<div class="col-md-offset-4 col-md-4">
		<h3>Choose a report below:</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-4 col-md-4">
		<div class="row">
			<h3><a href="${pageContext.request.contextPath}/report/classbystudent">Class took by Students</a></h3>				
		</div>	
	</div>
</div>
</body>
</html>