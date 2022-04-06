<%@page import="java.text.DecimalFormat"%>
<%@ page import="com.xprodmvc.model.Users"%>
<%@ page import="com.xprodmvc.model.Cart"%>
<%@ page import="com.xprodmvc.dao.ProductsDao"%>
<%@ page import="com.xprodmvc.connection.DbCon"%>
<%@ page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);	      	   
Users auth = (Users) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;

if (cart_list != null) { 

	ProductsDao pDao = new ProductsDao(DbCon.getConnection());
	cartProduct=pDao.getCartProduct(cart_list);
	double total=pDao.getTotalCartPrice(cart_list);
	request.setAttribute("total", total);
	request.setAttribute("cart_list", cart_list);


}
%>
<!DOCTYPE html>
<html>
<head>
<title>Big Bears</title>
<style type="text/css">
.table body td {
	vertical align: middle:
}

.btn-incre, btn-decre {
	box-shadow: none;
	font-size: 25px;
}

</style>
<%@include file="includes/head.jsp"%>
</head>

<body>
	<%@include file="includes/navbar.jsp"%>
	<h1 class="center">PANIER</h1>
	
	<div class="container">
		<div class="d-flex py-3">
		<h3>Prix total:  ${(total>0)?dcf.format(total):0} €</h3>
			<a class="mx-3 btn btn-primary" href="cart-check-out">Valider la
				Commande</a>

		</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Nom</th>
					<th scope="col">Categorie</th>
					<th scope="col">Prix</th>
					<th scope="col">Quantité</th>
					<th scope="col">Acheter maintenant</th>
					<th scope="col">Annuler</th>
				</tr>
			</thead>
			<tbody>
			
			<%
			if(cart_list != null){
				
				for(Cart c : cartProduct){%>

				<tr>
					<td><%=c.getName() %></td>
					<td><%=c.getCategory() %></td>
					<td><%=dcf.format(c.getPrice()) %>€</td>
					<td>
						<form action="order-now" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%=c.getId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between w-50">
								<a class="btn btn-sm btn-decre" href="quantity-inc-dec?action=dec&id=<%=c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control w-50" value="<%=c.getQUANTITY() %>" readonly>
								<a class="btn btn-sm btn-incre" href="quantity-inc-dec?action=inc&id=<%=c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
						
					</td>
					<td><button type="submit" class="btn btn-primary btn-on">Acheter</button></td>
					</form>
					<td><a href="remove-from-cart?action=delete&id=<%=c.getId()%>" class="btn btn-on btn-danger">Enlever</a></td>
					
				</tr>
			<%}
				} %>
			
			
			
			</tbody>
		
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>
</body>
</html>