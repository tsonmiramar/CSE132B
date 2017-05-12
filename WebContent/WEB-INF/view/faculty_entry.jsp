<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Faculty Entry"/>
	<jsp:param name="mainJsFile" value="faculty_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Faculty Entry Form"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/faculty/add" id="faculty_entryForm">
				<div class="form-group row">
					<c:forTokens var="identity" items="name,title" delims=",">
						<div class="col-md=4">
							<label style="text-transform:capitalize">${identity}</label>
							<c:choose>
								<c:when test="${identity == 'title'}">
									<select class="form-control" id="${identity}">
										<c:forTokens items="Lecturer,Assistant Professor,Associate Professor,Professor" delims="," var="title" varStatus="loop">
											<option value="${loop.index}">${title}</option>
										</c:forTokens>
									</select>
								</c:when>
								<c:otherwise>
									<input class="form-control" type="text" id="${identity}"/>	
								</c:otherwise>
							</c:choose>
						</div>
					</c:forTokens>
				</div>
				
				<div class="form-group row">
					<label>Department</label>
					<select class="form-control" id="department">
						<c:forEach var="dept" items="${departmentList}">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
					</select>
				</div>
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>