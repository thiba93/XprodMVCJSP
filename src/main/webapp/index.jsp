<%@page import="java.text.DecimalFormat"%>
<%@ page import="com.xprodmvc.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xprodmvc.connection.DbCon"%>
<%@ page import="com.xprodmvc.dao.*"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
Users auth = (Users) request.getSession().getAttribute("auth");

if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductsDao pd = new ProductsDao(DbCon.getConnection());
List<Product> produits = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;

if (cart_list != null) { 
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html>
<head>
<title>BigBears</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<h1>Bienvenue sur Big Bears</h1>
	<%@include file="includes/navbar.jsp"%>
	<%-- 	<%out.print(DbCon.getConnection());%> --%>
	<div class="container">
		<div class="card-header my-3">Liste des produits</div>
		<div class=row>
			<%
			if (!produits.isEmpty()) {
				for (Product p : produits) {%>

			<div class="col-md-3" style="width: 18rem;">
				<div class="card">
					<img src="product-image/<%=p.getImage() %>" class="card-img-top">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="Prix">
							Prix:<%=p.getPrice() %>€.
						</h6>
						<h6 class="Categorie"><%=p.getCategory() %>.
						</h6>
						<div class="mt-3 d-flex justify content-between"></div>
						<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Ajouter
							au panier</a> <a href="order-now?quantity=1&id=<%=p.getId()%>"
							class="btn btn-primary">Acheter maintenant</a>
					</div>
				</div>
			</div>
			<%}
			}
			%>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>