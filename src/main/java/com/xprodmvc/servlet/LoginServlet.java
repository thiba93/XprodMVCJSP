package com.xprodmvc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xprodmvc.connection.DbCon;
import com.xprodmvc.dao.UsersDao;
import com.xprodmvc.model.Users;

@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			out.print("This is login servlet ");

			String EMAIL = request.getParameter("login-mail");
			String MOT_DE_PASSE = request.getParameter("login-password");
			out.print("This are the infos servlet:" + EMAIL + "" + MOT_DE_PASSE);
			UsersDao udao = new UsersDao(DbCon.getConnection());
			Users user = udao.userlogin(EMAIL, MOT_DE_PASSE);
			if (user != null) {
				request.getSession().setAttribute("auth", user);
				System.out.print("User logged in," + " Nom de l'utilisateur: " + user.getNOM());
			} else {
				System.out.print("mauvais combinaison mot de passe+ identifiant");
			}

		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();

		}
	}
}
