package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.xprodmvc.model.Cart;

@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");

		try (PrintWriter out = response.getWriter()) {

			ArrayList<Cart> cartlist = new ArrayList<Cart>();
			int id = Integer.parseInt(request.getParameter("id"));

			Cart cm = new Cart();
			cm.setId(id);
			cm.setQUANTITY(1);
			HttpSession session = request.getSession();
			ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");

			if (cart_list == null) {
				cartlist.add(cm);
				session.setAttribute("cart-list", cartlist);
				// out.println("Session créee et ajouté à la liste");
				// response.sendRedirect("cart.jsp");
				out.println(
						"<h3 style='color:crimon ; text-align:center'><a href='cart.jsp'>Premier produit ajouté!</a></h3>");

			} else {
				cartlist = cart_list;
				boolean exist = false;

				for (Cart c : cart_list) {
					if (!exist && c.getId() == id)
						if (!exist && c.getId() != id) {
							cartlist.add(cm);
							session.setAttribute("cart-list", cartlist);
							out.println(
									"<h3 style='color:crimon ; text-align:center'><a href='cart.jsp'>Ajout d'un nouveau produit</a></h3>");
						}
					{
						int quantity = c.getQUANTITY();
						quantity++;
						c.setQUANTITY(quantity);
						response.sendRedirect("cart.jsp");

					}

				}
				// for (Cart ca : cart_list) {
				// out.print(ca.getIDPROD());

				// }
			}
		}
	}
}