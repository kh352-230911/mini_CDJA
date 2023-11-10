package com.sh.owner.model.entity;

public class Owner {
	private String id;
	private String password;
	private String name;
	private int id_no;
	private boolean kindshop;
	
	public Owner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Owner(String id, String password, String name, int id_no, boolean kindshop) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.id_no = id_no;
		this.kindshop = kindshop;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId_no() {
		return id_no;
	}

	public void setId_no(int id_no) {
		this.id_no = id_no;
	}

	public boolean isKindshop() {
		return kindshop;
	}

	public void setKindshop(boolean kindshop) {
		this.kindshop = kindshop;
	}

	@Override
	public String toString() {
		return "Owner [id=" + id + ", password=" + password + ", name=" + name + ", id_no=" + id_no + ", kindshop="
				+ kindshop + "]";
	}
	
	

}
