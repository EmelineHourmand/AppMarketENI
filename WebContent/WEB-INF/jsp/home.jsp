<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h1 align="center">PAGE HOME</h1>


<div class="container-fluid">
	<form action="${pageContext.request.contextPath}/addListing" method="GET">
		<input class="btn btn-info center" type="submit"
			value="Ajouter une liste" />
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />