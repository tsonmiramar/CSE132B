<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!-- JSTL CoreTag -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<jsp:include page="common_headers.jsp"></jsp:include>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/index.js"></script>
<title>Home</title>
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
				<h3><a href="${pageContext.request.contextPath}/course">Add New Course</a></h3>
				<h3><a href="${pageContext.request.contextPath}/class">Add New Class</a></h3>
		</div>	
	</div>
</div>
</body>
</html>