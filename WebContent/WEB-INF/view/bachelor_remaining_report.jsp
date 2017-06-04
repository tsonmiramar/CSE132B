<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Remaining Requirement for Bachelor Degree "/>
	<jsp:param name="mainJsFile" value="bachelor_remaining_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Bachelor Degree's Remaining Requirement"/>
    </jsp:include>
    
    <div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<div class="col-md-4">
					<label>Student</label>
					<select class="form-control" id="student">
						<c:forEach var="student" items="${studentList}">
							<option value="${student.id}">${student.firstname} ${student.lastname}</option>
						</c:forEach>
					</select>
				</div>
				
				<div class="col-md-4">
					<label>Bachelor Degree</label>
					<select class="form-control" id="degree">
						<c:forEach var="degree" items="${degreeList}">
							<option value="${degree.id}">${degree.name}</option>
						</c:forEach>
					</select>
				</div>
			</div>	
	</div>
	<div class="row">
		<div class="col-md-offset-2 col-md-4">
			<h3>Total Remaining Unit: <span id="totalRemainingUnit">${totalRemainingUnit}</span></h3>
		</div>
	</div>
	<div class="row">
		<div class="col-md-offset-2 col-md-8">
			<h3>Remaining Unit for Each Category</h3>
		</div>
	</div>	
	<div class="row">
		<div class="col-md-2"></div>
		<c:forTokens items="Category, Remaining Unit" delims="," var="title">
			<div class="col-md-4">
				<h3>${title}</h3>
			</div>
		</c:forTokens>
	</div>
	<div id="degreeRemainingDiv">
		<c:forEach var="degreeRemaining" items="${degreeRemainingList}">
			<div class="col-md-2"></div>
			<div class="row">
				<div class="col-md-4">
					<h4>${degreeRemaining.name}</h4>
				</div>
				
				<div class="col-md-4">
					<h4>${degreeRemaining.remainingUnit}</h4>
				</div>
			</div>
		</c:forEach>
	</div>
</body>
</html>
