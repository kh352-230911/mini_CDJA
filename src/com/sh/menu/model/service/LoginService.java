package com.sh.menu.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.getConnection;

import java.sql.Connection;

import com.sh.menu.model.dao.LoginDao;
import com.sh.menu.model.entity.LoginMember;
import com.sh.menu.model.entity.LoginOwner;

public class LoginService {
	private LoginDao loginDao = new LoginDao();

	public LoginMember login(String id, String password) {
		Connection conn = getConnection();
		LoginMember loginMember = loginDao.login(conn, id,password);
		close(conn);
		return loginMember;
	}

	public LoginOwner login2(String id, String password) {
		Connection conn = getConnection();
		LoginOwner loginOwner = loginDao.login2(conn, id,password);
		close(conn);
		return loginOwner;
	}
}
