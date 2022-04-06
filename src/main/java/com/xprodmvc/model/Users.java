package com.xprodmvc.model;

public class Users {

	private int ID_USERS;
	private String NOM;
	private String EMAIL;
	private String MOT_DE_PASSE;

	public Users() {
		super();
	}

	public Users(int iD_USERS, String nOM, String eMAIL, String mOT_DE_PASSE) {
		ID_USERS = iD_USERS;
		NOM = nOM;
		EMAIL = eMAIL;
		MOT_DE_PASSE = mOT_DE_PASSE;
	}

	public int getID_USERS() {
		return ID_USERS;
	}

	public void setID_USERS(int iD_USERS) {
		ID_USERS = iD_USERS;
	}

	public String getNOM() {
		return NOM;
	}

	public void setNOM(String nOM) {
		NOM = nOM;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String eMAIL) {
		EMAIL = eMAIL;
	}

	public String getMOT_DE_PASSE() {
		return MOT_DE_PASSE;
	}

	public void setMOT_DE_PASSE(String mOT_DE_PASSE) {
		MOT_DE_PASSE = mOT_DE_PASSE;
	}
}
