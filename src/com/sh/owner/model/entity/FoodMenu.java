package com.sh.owner.model.entity;

public class FoodMenu {
	private int pn;
	private int idNo;
	private String categoryCode;
	private int menuNo;
	private int price;
	private String menuName;

	public FoodMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FoodMenu(int pn, int idNo, String categoryCode, int menuNo, int price, String menuName) {
		super();
		this.pn = pn;
		this.idNo = idNo;
		this.categoryCode = categoryCode;
		this.menuNo = menuNo;
		this.price = price;
		this.menuName = menuName;
	}

	public int getPn() {
		return pn;
	}

	public void setPn(int pn) {
		this.pn = pn;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	@Override
	public String toString() {
		return "FoodMenu [pn=" + pn + ", idNo=" + idNo + ", categoryCode=" + categoryCode + ", menuNo=" + menuNo
				+ ", price=" + price + ", menuName=" + menuName + "]";
	}

}
