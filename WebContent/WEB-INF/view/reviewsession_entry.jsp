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
					<div class="col-md-4">
						<label style="text-transform:capitalize">class</label>
						<select class="form-control" id="class">
							<c:forEach items="${courseClassList}" var="courseClass">
								<option value="${courseClass.id}">${courseClass.course.courseSubject.symbol} ${courseClass.course.courseUnitNumber.currNum}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-4">
						<label style="text-transform:capitalize">section</label>
						<select class="form-control" id="section">
							<c:forEach var="section" items="${courseClassList[0].sectionList}">
								<option value="${section.id}">${section.id}</option>
							</c:forEach>
						</select>
					</div>
					
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
<script type="text/javascript">
	var courseClassJSON = ${courseClassJSON}; 
</script>
</html>