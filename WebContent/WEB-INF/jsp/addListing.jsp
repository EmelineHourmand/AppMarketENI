<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2 align="center">Nouvelle liste</h2>

<div class="container-fluid">
	<form action="${pageContext.request.contextPath}/addListing"
		method="GET" class="form-inline col-sm-4">
		<div class="form-group mb-2">
			<label for="listName" class="">Name : </label> <input type="text"
				class="form-control" id="listName" placeholder="Enter list name" required="required" name="listName">
		</div>
		<button type="submit" class="btn btn-info mb-2">Add list</button>
		
		<div class="form-group mb-2">
			<label for="articleName" class="">Name : </label> <input type="text"
				class="form-control" id="listName" placeholder="Enter list name" required="required" name="articleName">
		</div>
		<button type="submit" class="btn btn-info mb-2">Add article</button>
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />