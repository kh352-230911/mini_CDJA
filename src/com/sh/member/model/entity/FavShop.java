package com.sh.member.model.entity;

public class FavShop {
	private int favNo;
	private String favId;
	private int favShopNo;
	private String restName;
	private int restId;
	private String category;
	private String kindShop;
	private String addr;
	public FavShop() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FavShop(int favNo, String favId, int favShopNo, String restName, int restId, String category,
			String kindShop, String addr) {
		super();
		this.favNo = favNo;
		this.favId = favId;
		this.favShopNo = favShopNo;
		this.restName = restName;
		this.restId = restId;
		this.category = category;
		this.kindShop = kindShop;
		this.addr = addr;
	}
	public int getFavNo() {
		return favNo;
	}
	public void setFavNo(int favNo) {
		this.favNo = favNo;
	}
	public String getFavId() {
		return favId;
	}
	public void setFavId(String favId) {
		this.favId = favId;
	}
	public int getFavShopNo() {
		return favShopNo;
	}
	public void setFavShopNo(int favShopNo) {
		this.favShopNo = favShopNo;
	}
	public String getRestName() {
		return restName;
	}
	public void setRestName(String restName) {
		this.restName = restName;
	}
	public int getRestId() {
		return restId;
	}
	public void setRestId(int restId) {
		this.restId = restId;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getKindShop() {
		return kindShop;
	}
	public void setKindShop(String kindShop) {
		this.kindShop = kindShop;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	@Override
	public String toString() {
		return "FavShop [favNo=" + favNo + ", favId=" + favId + ", favShopNo=" + favShopNo + ", restName=" + restName
				+ ", restId=" + restId + ", category=" + category + ", kindShop=" + kindShop + ", addr=" + addr + "]";
	}
	
	
	
	
}
