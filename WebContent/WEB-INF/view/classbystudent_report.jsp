<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Class Enrolled By Student "/>
	<jsp:param name="mainJsFile" value="classbystudent_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Classes Currently Taken by Student"/>
    </jsp:include>
	<div class="row">
		<div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<label>Student</label>
				<select class="form-control" id="student">
					<c:forEach var="student" items="${studentList}">
						<option value="${student.id}">${student.firstname} ${student.lastname} ${student.middlename}</option>
					</c:forEach>
				</select>
			</div>
			
		</div>
	</div>
	
	<div class="row">
		<c:forTokens var="item" items="class,title,unit,section,faculty,limit" delims=",">
			<div class="col-md-2">
				<h3 style="text-transform:capitalize">${item}</h3>	
			</div>
		</c:forTokens>
	</div>	
			
	<div id="ClassInfoList">
		<c:forEach var="courseClass" items="${courseClassList}">
			<div class="row">
				<div class="col-md-2">
					<h3>${courseClass.course.courseSubject.symbol} ${courseClass.course.courseUnitNumber.currNum}</h3>	
				</div>
				
				<div class="col-md-2">
					<h3>${courseClass.title}</h3>
				</div>
				<div class="col-md-2">
					<h3>${courseClass.course.courseUnitNumber.unitTo}</h3>
				</div>
				
				<div class="col-md-2">
					<h3>${courseClass.sectionList.toArray()[0].id}</h3>
				</div>
				
				<div class="col-md-2">
					<h3>${courseClass.sectionList.toArray()[0].faculty.name}</h3>
				</div>
				
				<div class="col-md-2">
					<h3>${courseClass.sectionList.toArray()[0].enrollmentLimit}</h3>
				</div>
			</div>
		</c:forEach>	
	</div>
</body>
</html>