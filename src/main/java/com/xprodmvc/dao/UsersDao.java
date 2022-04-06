package com.xprodmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.xprodmvc.model.Users;

public class UsersDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public UsersDao(Connection con) {
		this.con = con;
	}

	public Users userlogin(String EMAIL, String MOT_DE_PASSE) {
		Users user = null;

		try {
			query = "select *from users where EMAIL=?and MOT_DE_PASSE=?";
			pst = this.con.prepareStatement(query);
			pst.setString(1, EMAIL);
			pst.setString(2, MOT_DE_PASSE);
			rs = pst.executeQuery();

			if (rs.next()) {
				user = new Users();
				user.setID_USERS(rs.getInt("ID_USERS"));
				user.setNOM(rs.getString("NOM"));
				user.setEMAIL(EMAIL);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
}
