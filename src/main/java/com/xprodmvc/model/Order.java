package com.xprodmvc.model;

public class Order extends Product {
	private int orderId;
	private int uId;
	private int quantity;
	private String date;

	public Order(int id, String name, String category, Double price, String image, int orderId, int uId, int quantity,
			String date) {
		super(id, name, category, price, image);
		this.orderId = orderId;
		this.uId = uId;
		this.quantity = quantity;
		this.date = date;
	}

	public Order() {
		super();
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getuId() {
		return uId;
	}

	public void setuId(int uId) {
		this.uId = uId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", uId=" + uId + ", quantity=" + quantity + ", date=" + date + "]";
	}

}
