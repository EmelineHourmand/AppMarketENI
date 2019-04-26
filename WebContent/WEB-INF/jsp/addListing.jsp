<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2 align="center">Nouvelle liste</h2>

<div class="container-fluid">
	<form action="${pageContext.request.contextPath}/addListing"
		method="POST" class="form-group col-sm-4">
		<div class="form-group mb-2">
			<label for="listName">Name : </label> <input type="text"
				class="form-control" id="listName" placeholder="Enter list name"
				required="required" name="listName">
		</div>

		<label for="articleName">Article : </label> <input type="text"
			class="form-control mb-3" id="listName" placeholder="Enter list name"
			required="required" name="articleName">
		<button type="submit" class="btn btn-info mb-2">Add list</button>
	</form>

</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />