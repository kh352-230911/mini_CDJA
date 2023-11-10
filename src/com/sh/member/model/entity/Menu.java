package com.sh.member.model.entity;

public class Menu {
	private int fn;
	private int idNo;
	private String categoryCode;
	private int menuNo;
	private String menuName;
	private int price;
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Menu(int fn, int idNo, String categoryCode, int menuNo, String menuName, int price) {
		super();
		this.fn = fn;
		this.idNo = idNo;
		this.categoryCode = categoryCode;
		this.menuNo = menuNo;
		this.menuName = menuName;
		this.price = price;
	}
	public int getFn() {
		return fn;
	}
	public void setFn(int fn) {
		this.fn = fn;
	}
	public int getIdNo() {
		return idNo;
	}
	public void setIdNo(int idNo) {
		this.idNo = idNo;
	}
	public String getCategoryCode() {
		return categoryCode;
	}
	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	@Override
	public String toString() {
		return "Menu [fn=" + fn + ", idNo=" + idNo + ", categoryCode=" + categoryCode + ", menuNo=" + menuNo
				+ ", menuName=" + menuName + ", price=" + price + "]";
	}
	
	
	
}
