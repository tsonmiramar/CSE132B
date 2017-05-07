<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/jquery-3.2.1.min.js"></script>
	<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/bootstrap.min.css">
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/bootstrap.min.js"></script>
	<c:if test="${param.mainJsFile != null }">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/${param.mainJsFile}"></script>
	</c:if>
	<c:if test="${param.commonfunction != null }">
		<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/${param.commonfunction}"></script>
	</c:if>
	<c:if test="${param.mainCSSFile != null }">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/${param.mainCSSFile}">
	</c:if>
	<script type="text/javascript">
		//Reference variable to the root path 
		var contextPath = "${pageContext.request.contextPath}"
	</script>
	<title><c:out value="${param.title}"/></title>
</head>