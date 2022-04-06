<%@page import="java.text.DecimalFormat"%>
<%@ page import="com.xprodmvc.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.xprodmvc.connection.DbCon"%>
<%@ page import="com.xprodmvc.dao.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);

Users auth = (Users) request.getSession().getAttribute("auth");
List<Order> orders = null;
if (auth != null) {
	request.setAttribute("auth", auth);
	OrderDao orderDao = new OrderDao(DbCon.getConnection());
	orders = orderDao.userOrders(auth.getID_USERS());

} else {
	  response.sendRedirect("login.jsp");
}	
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
// List<Cart> cartProduct = null;

if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
<!DOCTYPE html>
<html>
<head>
<title>Commandes</title>
<%@include file="includes/head.jsp"%>
</head>

<body>
	<%@include file="includes/navbar.jsp"%>
	<h1>COMMANDES</h1>
	<div class="container">
		<div class="cart-header my-3">Toutes les Commandes</div>
		<table class="table table-light">
			<thead>
				<tr>
					<th scope="col">Date</th>
					<th scope="col">Nom</th>
					<th scope="col">Categorie</th>
					<th scope="col">Quantité</th>
					<th scope="col">Prix</th>
					<th scope="col">Annuler</th>
				</tr>
			</thead>
			<tbody>
				<%
				if (orders != null) {
					for (Order o : orders) {
				%>
				<tr>
					<td><%=o.getDate()%></td>
					<td><%=o.getName()%></td>
					<td><%=o.getCategory()%></td>
					<td><%=o.getQuantity()%></td>
					<td><%=dcf.format(o.getPrice())%></td>
					<td><a class="btn btn-sm btn-danger"
						href="cancel-order?id=<%=o.getOrderId()%>">Annuler La Commande</a></td>
				</tr>
				<%
				

				}
				}
				%>
			</tbody>
		</table>

	</div>
	<%@include file="includes/footer.jsp"%>
</body>
</html>