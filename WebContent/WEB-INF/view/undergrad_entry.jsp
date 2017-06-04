<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="form-group row">
	<c:forTokens var="type" items="major,minor,college" delims=",">
		<div class="col-md-4">
			<label style="text-transform:capitalize">${type}</label>
			<select class="form-control" id="${type }"></select>
		</div>
	</c:forTokens>
</div>