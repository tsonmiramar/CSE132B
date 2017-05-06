<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Student Entry"/>
	<jsp:param name="mainJsFile" value="student_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Student Entry Form"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/student/add" id="student_entryForm">
				<div class="form-group row">
					<c:forTokens var="suffix" items="first,last,middle" delims=",">
						<div class="col-md-4">
							<label style="text-transform: capitalize">${suffix} Name</label>
							<input type="text" class="form-control" id="${suffix}Name" placeholder="Enter ${suffix } Name"/>
						</div>
					</c:forTokens>
				</div>
				
				<div class="form-group row">
					<c:forTokens var="identity" items="pid,ssn,residency" delims=",">
						<div class="col-md-4">
							<label style="text-transform:uppercase">${identity}</label>
							<c:choose>
								<c:when test="${identity == 'residency' }">
									<select class="form-control" id="${identity}">
										<c:forEach var="resident" items="${residentStatusList}" >
											<option value="${resident.id}">${resident.type}</option>
										</c:forEach>
									</select>
								</c:when>
								<c:otherwise>
									<input type="text" class="form-control" id="${identity}" placeholder="Enter ${identity }"/>
								</c:otherwise>
							</c:choose>
						</div>
					</c:forTokens>
				</div>
				
				<label>Attendance</label>
				<div class="form-group row">
					<c:forTokens var="dest" items="From,To" delims=",">
						<div class="col-md-3">
							<label>${dest}</label>
							<select class="form-control" id="quarter${dest}">
								<c:forEach var="quarterName" items="${quarterNameList}">
									<option value="${quarterName.id}">${quarterName.name}</option>
								</c:forEach>
							</select>
						</div>
						
						<div class="col-md-3"> 
							<label>Year</label>
							<input class="form-control" type="text" id="year${dest}"/>
						</div>
					</c:forTokens>
				</div>
				<label>Student Type:</label>
				<div class="form-group row" id="studentType">
					<c:forTokens var="type" items="undergrad,master,BSMS,phD,pre-PhD" delims=",">
						<div class="col-md-2">
						<label class="radio-inline" style="text-transform:capitalize">
							<c:choose>
								<c:when test="${type == 'undergrad'}">
									<input type="radio" name="studentType" id="${type}" checked/>
									${type}
								</c:when>
								<c:otherwise>
									<input type="radio" name="studentType" id="${type}"/>
									${type}
								</c:otherwise>
							</c:choose>
						</label>
						</div>
					</c:forTokens>
				</div>
				
				<div id="SubStudentEntry">	
					<!-- This section will load the appropriate jsp view 
						 depends on student type chose above -->
				</div>
				
				<div class="form-group row">
					<button type="submit" class="btn btn-submit pull-right" id="submitBtn">Submit</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>