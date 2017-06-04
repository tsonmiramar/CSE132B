<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group row">
	<div class="col-md-4">
		<label> Weekday </label>
		<select class="form-control" id="weekday">
			<c:forTokens var="day" items="Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday" delims=",">
				<option value="${day}">${day} </option>
			</c:forTokens>
		</select>
	</div>
	
	<c:forTokens var="identity" items="start,end" delims=",">
		<div class="col-md-4">
			<label style="text-transform:capitalize">${identity} Time</label>
			<input class="form-control" type="time" id="${identity}Time" />
		</div>
	</c:forTokens>
</div>

<div class="form-group row">
	<c:forTokens var="identity" items="building,room" delims=",">
		<div class="col-md-4">
			<label style="text-transform:capitalize">${identity}</label>
			<input class="form-control" type="text" id="${identity}" placeholder="Enter ${identity}"/>
		</div>
	</c:forTokens>
</div>