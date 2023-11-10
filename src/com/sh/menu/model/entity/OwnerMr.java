package com.sh.menu.model.entity;

public class OwnerMr {
	private String id;
	private String password;
	private String name;
	private int id_no;
	private String rest_name;
	
	public OwnerMr() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "owner_mr [id=" + id + ", password=" + password + ", name=" + name + ", id_no=" + id_no + ", rest_name="
				+ rest_name + "]";
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
	public String getRest_name() {
		return rest_name;
	}
	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
	
}

