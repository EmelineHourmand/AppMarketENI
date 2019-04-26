<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2 align="center">Listes prédéfinies</h2>

<div class="container-fluid">
	<form action="${pageContext.request.contextPath}/addListing"
		method="GET">
		<input class="btn btn-info center" type="submit"
			value="Ajouter une liste" />
	</form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />