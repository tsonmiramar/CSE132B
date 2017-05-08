<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Review Session Submission"/>
	<jsp:param name="mainJsFile" value="reviewsession_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Review Session Submission"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/section/meeting/reviewsession/add" id="reviewsession_entryForm">
				<div class="form-group row">
					<c:forTokens var="identity" items="class,section" delims=",">
						<div class="col-md-3">
							<label style="text-transform:capitalize">${identity}</label>
							<select class="form-control" id="${identity}"></select>
						</div>
					</c:forTokens>
					
					<div class="col-md-4">
						<label>Review Date</label>
						<input class="form-control" type="date" min="2017-04-03" max="2017-06-09" id="reviewDate"/>
					</div>		
				</div>
				
				<jsp:include page="basemeeting_entry.jsp"></jsp:include>
				
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>