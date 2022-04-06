<%@page import="java.text.DecimalFormat"%>
<%@ page import="com.xprodmvc.model.*" %>
<%@ page import="java.util.*"%>
<%@ page import="com.xprodmvc.connection.DbCon"%>
<%@ page import="com.xprodmvc.dao.*"%>
<%@ page import="java.util.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<% 
	
	DecimalFormat dcf = new DecimalFormat("#.##");
	request.setAttribute("dcf", dcf);
	
	Users auth =(Users)request.getSession().getAttribute("auth");
	List<Order> orders = null;
	if(auth!=null){
	request.setAttribute("auth",auth);
	OrderDao orderDao = new OrderDao(DbCon.getConnection());
	//orders = orderDao.userOrders(auth.getID_USERS());
	
	}
	ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;

if (cart_list != null) { 
	request.setAttribute("cart_list", cart_list);
}%>
<!DOCTYPE html>
<html>
<head>
<title>Commandes</title>
<%@include file="includes/head.jsp"%>
</head>

<body>
	<%@include file="includes/navbar.jsp"%>
	<h1>COMMANDES</h1>
	<%@include file="includes/footer.jsp"%>
</body>
</html>