<jsp:include page="/WEB-INF/jsp/header.jsp" />

<h2>Votre panier</h2>
<div class="main-content">Liste des articles</div>
<form action="${pageContext.request.contextPath}" method="POST">
	<input type="submit" value="Home" />
</form>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />