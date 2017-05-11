<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Degree Requirement Submission"/>
	<jsp:param name="mainJsFile" value="degree_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Degree Requirement Submission"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/degree/add" id="degree_entryForm">
				<div class="form-group row">
					<div class="col-md-4">
						<label>Department</label>
						<select class="form-control" id="department">
							<c:forEach var="dept" items="${departmentList}">
								<option value="${dept.id}">${dept.name}</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-md-4">
						<label>Institution</label>
						<input type="text" class="form-control" id="institution" placeholder="Enter an institution"/>
					</div>
					
					<div class="col-md-4">
						<label>Degree Type</label>
						<select class="form-control" id="degreeType">
							<c:forEach var="type" items="${degreeTypeList}">
								<option value="${type.id}">${type.name}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group row" id="courseConcentration">
					<div class="col-md-4">
						<label>Course Concentration</label>
						<select class="form-control" id="course">
							<c:forEach var="course" items="${courseList}">
								<option value="${course.id}">${course.courseSubject.symbol} ${course.courseUnitNumber.currNum}</option>
							</c:forEach>
						</select>
					</div>
					<button type="button" class="btn btn-primary" id="AddCourseBtn" >Add Course</button>
					<div id="concentrationList">
						<!-- will be populate with course concentration -->
					</div>
				</div>
				
				<div class="form-group row">
					<div class="col-md-4">
						<label>Total Unit</label>
						<input type="number" class="form-control" id="totalUnitNumber"/>
					</div>
				</div>
				
				<label>Unit Category Requirement</label>
				<div id="unitCategoryRequirement">
					<div id="categoryRequirement">
						<div class="form-group row">
							<c:forTokens var="identity" items="type,GPA,minimumUnit" delims=",">
								<div class="col-md-4">
									<label style="text-transform:capitalize">${identity}</label>
									<c:choose>
										<c:when test="${identity == 'GPA'}">
											<input type="number" step=0.1 min=0.0 class="form-control" id="${identity}" />
										</c:when>
										<c:when test="${identity == 'minimumUnit'}">
											<input type="number" min=0 class="form-control" id="${identity}" />
										</c:when>
										<c:otherwise>
											<input type="text" class="form-control" id="${identity}" />
										</c:otherwise>
									</c:choose> 
								</div>
							</c:forTokens>
						</div>
					</div>
				</div>
				<button type="button" class="btn btn-primary" id="AddUnitCategoryBtn">Add Unit Category Requirement</button>
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
</html>