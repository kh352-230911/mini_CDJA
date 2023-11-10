package com.sh.menu.model.entity;

public class MemberMr {
	private String id;
	private String password;
	private String name;
	private String nickname; 
	
	public MemberMr() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public MemberMr(String id, String password, String name, int id_no, String nickname) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "member_mr [id=" + id + ", password=" + password + ", name=" + name + ", nickname="
				+ nickname + "]";
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
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
	
}
