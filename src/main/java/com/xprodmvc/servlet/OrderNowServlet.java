package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xprodmvc.connection.DbCon;
import com.xprodmvc.dao.*;
import com.xprodmvc.model.Cart;
import com.xprodmvc.model.Order;
import com.xprodmvc.model.Users;

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			Users auth = (Users) request.getSession().getAttribute("auth");
			if (auth != null) {
				String IDPROD = request.getParameter("id");
				int QUANTITE_PRODUIT = Integer.parseInt(request.getParameter("quantity"));
				// System.out.println(IDPROD);

				if (QUANTITE_PRODUIT <= 0) {

					QUANTITE_PRODUIT = 1;
				}
				Order orderModel = new Order();

				orderModel.setId(Integer.parseInt(IDPROD));
				// System.out.println(orderModel.getIDPROD());
				orderModel.setuId(auth.getID_USERS());
				// System.out.println(orderModel.getID_USER());
				orderModel.setQuantity(QUANTITE_PRODUIT);
				// System.out.println(orderModel.getQUANTITY());
				orderModel.setDate(formatter.format(date));
				// System.out.println(orderModel.getDATE());

				OrderDao orderDao = new OrderDao(DbCon.getConnection());
				boolean result = orderDao.insertOrder(orderModel);
				// System.out.println(result);

				if (result) {
					ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
					if (cart_list != null) {
						for (Cart c : cart_list) {
							if (c.getId() == Integer.parseInt(IDPROD)) {

								cart_list.remove(cart_list.indexOf(c));
								break;
							}
						}
					}
					response.sendRedirect("order.jsp");
				} else {
					out.println("Orders failed");

				}
			} else {
				response.sendRedirect("login.jsp");
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}