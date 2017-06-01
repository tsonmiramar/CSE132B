<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<jsp:include page="common_headers.jsp">
	<jsp:param name="title" value="Review Session Available Hours "/>
	<jsp:param name="mainJsFile" value="review_session_report.js"/>
</jsp:include>
<body class="container-fluid">
	<jsp:include page="entry_header.jsp">
        <jsp:param name="entry_name" value="Review Session Available Hours"/>
    </jsp:include>
    <div class="row">
	   <div class="col-md-offset-3 col-md-6">
			<div class="form-group row">
				<div class="col-md-4">
					<label>Current Section</label>
					<select class="form-control" id="section">
						<c:forEach var="section" items="${sectionList}">
							<option value="${section.id}">${section.id}, ${section.sectionClass.course.courseSubject.symbol} ${section.sectionClass.course.courseUnitNumber.currNum}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			
			<div class="form-group row">
				<div class="col-md-4">
					<label>Available Hours</label>
				</div>
			</div>
			<div class="form-group row">
				<c:forTokens items="From,To" delims="," var="title">
					<div class="col-md-4">
						<label>${title}</label>
						<select class="form-control" id="day${title}">
							<c:forEach var="day" items="${weekdayList}">
								<c:choose>
									<c:when test="${title == 'To' && day.id == 5}">
										<option value="${day.id}" selected>${day.name}</option>
									</c:when>
									<c:when test="${title == 'To' && day.id == 1}">
										<!-- ignore Monday option for dayTo -->
									</c:when>
									<c:otherwise>
										<option value="${day.id}">${day.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
					</div>
				</c:forTokens>
			</div>
		
			<div class="row">
				<c:forTokens items="Day,Start Time,End Time" delims="," var="title">
					<div class="col-md-4">
						<h3><strong>${title}</strong></h3>
					</div>
				</c:forTokens>
			</div>
			
			<div id="reviewSessionDiv">
				<c:forEach items="${reviewSessionList}" var="reviewSession">
					<div class="row">
						<div class="col-md-4">
							<h4>${reviewSession.weekday}</h4>
						</div>
						
						<div class="col-md-4">
							<h4>${reviewSession.startTime}</h4>
						</div>
						
						<div class="col-md-4">
							<h4>${reviewSession.endTime}</h4>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</body>
</html>
