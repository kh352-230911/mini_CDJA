package com.sh.menu.model.entity;

public class RestListMr {
	private int id_no;
	private String name;
	private String addr;
	private String category_code;
	private String kindshop;
	private String categoryName;
	
	public RestListMr() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RestListMr(int id_no, String name, String addr, String category_code, String kindshop, String categoryName) {
		super();
		this.id_no = id_no;
		this.name = name;
		this.addr = addr;
		this.category_code = category_code;
		this.kindshop = kindshop;
		this.categoryName = categoryName;
	}

	public int getId_no() {
		return id_no;
	}

	public void setId_no(int id_no) {
		this.id_no = id_no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getCategory_code() {
		return category_code;
	}

	public void setCategory_code(String category_code) {
		this.category_code = category_code;
	}

	public String getKindshop() {
		return kindshop;
	}

	public void setKindshop(String kindshop) {
		this.kindshop = kindshop;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "RestListMr [id_no=" + id_no + ", name=" + name + ", addr=" + addr + ", category_code=" + category_code
				+ ", kindshop=" + kindshop + ", categoryName=" + categoryName + "]";
	}

	
	
	
	
	
}
