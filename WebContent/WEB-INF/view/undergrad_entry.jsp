<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group row">
	<c:forTokens var="type" items="major,minor,college" delims=",">
		<div class="col-md-4">
			<label style="text-transform:capitalize">${type}</label>
			<c:choose>
				<c:when test="${type == 'college' }">
					<select class="form-control" id="${type }"></select>
				</c:when>
				<c:otherwise>
					<input type="text" class="form-control" id="${type }" placeholder="Enter ${type }"/>
				</c:otherwise>
			</c:choose>
		</div>
	</c:forTokens>
</div>