package com.xprodmvc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.xprodmvc.connection.DbCon;
import com.xprodmvc.model.Order;
import com.xprodmvc.model.Product;

public class OrderDao {
	private Connection con;
	private String query;
	private PreparedStatement pst;
	private ResultSet rs;

	public OrderDao(Connection con) {
		super();
		this.con = con;
	}

	public boolean insertOrder(Order model) {

		boolean result = false;
		try {
			query = "insert into orders (IDPROD,IDUSER,o_quantity,COMMANDEDATE)" + "value(?,?,?,?)";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, model.getId());
			pst.setInt(2, model.getuId());
			pst.setInt(3, model.getQuantity());
			pst.setString(4, model.getDate());
			pst.executeUpdate();
			result = true;
			System.out.println("Order inserted !");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return result;
	}

	public List<Order> userOrders(int id) {
		List<Order> list = new ArrayList<>();
		try {
			query = "select * from orders where IDUSER=? order by orders.IDORDER desc";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			rs = pst.executeQuery();

			while (rs.next()) {

				Order order = new Order();
				ProductsDao pDao = new ProductsDao(DbCon.getConnection());
				int pId = rs.getInt("IDPROD");
				Product product = pDao.getSingleProduct(pId);
				order.setOrderId(rs.getInt("IDORDER"));
				order.setId(pId);
				order.setName(product.getName());
				order.setCategory(product.getCategory());
				order.setPrice(product.getPrice());
				order.setQuantity(rs.getInt("o_quantity"));
				order.setDate(rs.getString("COMMANDEDATE"));

				list.add(order);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return list;

	}

	public void cancelOrder(int id) {
		try {
			query = " delete from orders where IDORDER=?";
			pst = this.con.prepareStatement(query);
			pst.setInt(1, id);
			pst.execute();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}

	}
}