package com.xprodmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xprodmvc.model.*;

public class ProductsDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public ProductsDao(Connection con) {
		this.con = con;
	}

	/*
	 * public ProductsDao(Connection con, String query, PreparedStatement pst,
	 * ResultSet rs) { this.con = con; this.query = query; this.pst = pst; this.rs =
	 * rs;
	 * 
	 * }
	 */

	public ProductsDao() {
	}

	public List<Product> getAllProducts() {
		List<Product> produits = new ArrayList<Product>();
		try {
			query = "select * from produits";
			pst = this.con.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				Product row = new Product();
				row.setId(rs.getInt("IDPROD"));
				row.setName(rs.getString("NOM"));
				row.setCategory(rs.getString("CATEGORIE"));
				row.setPrice(rs.getDouble("PRIX"));
				row.setImage(rs.getString("IMAGE"));

				produits.add(row);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return produits;
	}

	public List<Cart> getCartProduct(ArrayList<Cart> cartlist) {
		List<Cart> produits = new ArrayList<>();
		try {
			if (cartlist.size() > 0) {

				for (Cart item : cartlist) {

					query = "select * from produits where IDPROD=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {

						Cart row = new Cart();
						row.setId(rs.getInt("IDPROD"));
						row.setName(rs.getString("NOM"));
						row.setCategory(rs.getString("CATEGORIE"));
						row.setImage(rs.getString("IMAGE"));
						row.setPrice(rs.getDouble("PRIX") * item.getQUANTITY());
						row.setQUANTITY(item.getQUANTITY());

						produits.add(row);
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return produits;
	}

	public double getTotalCartPrice(ArrayList<Cart> cartList) {

		double sum = 0;
		try {
			if (cartList.size() > 0) {

				for (Cart item : cartList) {
					query = "select PRIX from produits where IDPROD=?";
					pst = this.con.prepareStatement(query);
					pst.setInt(1, item.getId());
					rs = pst.executeQuery();
					while (rs.next()) {
						sum += rs.getDouble("PRIX") * item.getQUANTITY();
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sum;
	}

	public Product getSingleProduct(int pId) {
		Product row = null;
		try {
			query = "select * from produits where IDPROD=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, pId);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				row = new Product();
				row.setId(rs.getInt("IDPROD"));
				row.setName(rs.getString("NOM"));
				row.setCategory(rs.getString("CATEGORIE"));
				row.setPrice(rs.getDouble("PRIX"));
				row.setImage(rs.getString("IMAGE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

		return row;
	}
}
