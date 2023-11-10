package com.sh.menu.model.entity;

public class LoginOwner {
	private String id;
	private String password;
	private String name;
	private int id_no;
	
	public LoginOwner() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginOwner(String id, String password, String name, int id_no) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.id_no = id_no;
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

	@Override
	public String toString() {
		return "LoginOwner [id=" + id + ", password=" + password + ", name=" + name + ", id_no=" + id_no + "]";
	}
}
