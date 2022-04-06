package com.xprodmvc.model;

public class Cart extends Product {

	private int QUANTITY;

	public Cart() {
		super();
	}

	public Cart(int iDPROD, String nOM, String cATEGORIE, Double pRIX, String iMAGE, int qUANTITY) {
		super(iDPROD, nOM, cATEGORIE, pRIX, iMAGE);
		QUANTITY = qUANTITY;
	}

	public int getQUANTITY() {
		return QUANTITY;
	}

	public void setQUANTITY(int qUANTITY) {
		QUANTITY = qUANTITY;
	}

}
