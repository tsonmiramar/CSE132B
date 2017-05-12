<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Past Enrollment Entry"/>
	<jsp:param name="mainJsFile" value="pastenrollment_entry.js"/>	
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Past Enrollment Entry"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<form method="POST" action="${pageContext.request.contextPath}/class/enrollment/add" id="pastenrollment_entryForm">
				<div class="form-group row">
					<div class="col-md-6">
						<label style="text-transform:capitalize">student</label>
						<select class="form-control" id="student">
							<c:forEach var="student" items="${studentList}">
								<option value="${student.id}">${student.firstname} ${student.lastname} ${student.middlename}</option> 
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-6">
						<label style="text-transform:capitalize">quarter</label>
						<select class="form-control" id="quarter">
							<c:forEach var="quarter" items="${quarterList}">
								<option value="${quarter.id}">${quarter.quarterName.name} ${quarter.year}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				
				<div class="form-group row">			
					<div class="col-md-3">
						<label style="text-transform:capitalize">class</label>
						<select class="form-control" id="class">
							<c:forEach items="${courseClassList}" var="courseClass">
								<option value="${courseClass.id}">${courseClass.course.courseSubject.symbol} ${courseClass.course.courseUnitNumber.currNum}</option>
							</c:forEach>
						</select>
					</div>
					
					<div class="col-md-3">
						<label style="text-transform:capitalize">section</label>
						<select class="form-control" id="section">
							<c:forEach var="section" items="${courseClassList[0].sectionList}">
								<option value="${section.id}">${section.id}</option>
							</c:forEach>
						</select>
					</div>
					<c:forTokens var="identity" items="unit,grade" delims=",">
						<div class="col-md-3" id="${identity}Div">
							<label style="text-transform:capitalize">${identity}</label>
							<div id="gradeInputType">
								<c:choose>
									<c:when test="${identity == 'unit'}">
										<input type="text" class="form-control" id="${identity}" placeholder="Enter ${identity}"/>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${courseClassList[0].course.courseOption.letter_option == true }">
												<input type="text" class="form-control" id="${identity}" placeholder="Enter ${identity}" />
											</c:when>
											<c:otherwise>
												<select class="form-control" id="${identity}">
													<c:forTokens var="grade" items="S,U" delims=",">
														<option>${grade}</option>
													</c:forTokens>
												</select>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</c:forTokens>
				</div>
				
				<jsp:include page="submitBtn.jsp"></jsp:include>
			</form>
		</div>
	</div>
</body>
 <script type="text/javascript">
	var courseClassJSON = ${courseClassJSON}; 
	var key = Object.keys(courseClassJSON)[0];
	displayUnitInput(courseClassJSON[key]);
</script>  
</html>