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
			<c:forTokens var="identity" items="course,class,student,faculty,committee,degree" delims=",">
					<h3><a href="${pageContext.request.contextPath}/${identity}/entry">Add New ${identity}</a></h3>
			</c:forTokens>
			
			<h3><a href="${pageContext.request.contextPath}/class/enrollment/entry">Add New Course Enrollment</a></h3>
			<h3><a href="${pageContext.request.contextPath}/class/pastenrollment/entry">Add Course Taken in the past</a></h3>
			<h3><a href="${pageContext.request.contextPath}/student/probation/entry">Add New Probation</a></h3>
			<h3><a href="${pageContext.request.contextPath}/section/meeting/reviewsession/entry">Add New Review Session</a></h3>
							
		</div>	
	</div>
</div>
</body>
</html>