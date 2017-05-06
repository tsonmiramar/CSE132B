<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- JSTL CoreTag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
    <jsp:param name="title" value="Home"/>
    <jsp:param name="mainJsFile" value="index.js"/>
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
		<h3>Choose an action below:</h3>
	</div>
</div>
<div class="row">
	<div class="col-md-offset-4 col-md-4">
		<div class="row">
				<h3><a href="${pageContext.request.contextPath}/course/entry">Add New Course</a></h3>
				<h3><a href="${pageContext.request.contextPath}/class/entry">Add New Class</a></h3>
				<h3><a href="${pageContext.request.contextPath}/student/entry">Add New Student</a></h3>
		</div>	
	</div>
</div>
</body>
</html>