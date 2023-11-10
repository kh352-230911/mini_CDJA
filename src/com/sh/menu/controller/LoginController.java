package com.sh.menu.controller;

import com.sh.menu.model.entity.LoginMember;
import com.sh.menu.model.entity.LoginOwner;
import com.sh.menu.model.service.LoginService;

public class LoginController {
private LoginService loginService = new LoginService();
	
	
	public LoginMember login(String id, String password) {
		LoginMember loginMember = null;
			try {
				loginMember = loginService.login(id,password);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("불편을 드려 죄송합니다.." + e.getMessage());
			}
		return loginMember;
	}


	public LoginOwner login2(String id, String password) {
		LoginOwner loginOwner = null;
		try {
			loginOwner = loginService.login2(id,password);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("불편을 드려 죄송합니다.." + e.getMessage());
		}
		return loginOwner;
	}
}
