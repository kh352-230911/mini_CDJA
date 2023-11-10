package com.sh.menu.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.sh.menu.model.entity.LoginMember;
import com.sh.menu.model.entity.LoginOwner;
import com.sh.menu.model.exception.MenuException;

public class LoginDao {
	Properties prop = new Properties();
	public LoginDao() {
		
			try {
				prop.load(new FileReader("resources/menu-query.properties"));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		
	}

	public LoginMember login(Connection conn, String id, String password) {
		LoginMember loginMember = null;
		String sql = prop.getProperty("memberLogin");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			try (ResultSet rset = pstmt.executeQuery();) {
				if(rset.next()) {
					loginMember = handleLoginMemberResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new MenuException("회원 로그인 오류!",e);
		}
		return loginMember;
	}

	private LoginMember handleLoginMemberResultSet(ResultSet rset) throws SQLException {
		LoginMember loginMember = new LoginMember();
		
		loginMember.setId(rset.getString("id"));
		loginMember.setPassword(rset.getString("password"));
		loginMember.setName(rset.getString("name"));
		loginMember.setNickname(rset.getString("nickname"));
		
		return loginMember;
	}

	public LoginOwner login2(Connection conn, String id, String password) {
		LoginOwner loginOwner = null;
		String sql = prop.getProperty("ownerLogin");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, password);
			try (ResultSet rset = pstmt.executeQuery();) {
				while(rset.next()) {
					loginOwner = handleLoginOwnerResultSet(rset);
				}
			}
		} catch (SQLException e) {
			throw new MenuException("회원 로그인 오류!",e);
		}
		return loginOwner;
	}
	
	private LoginOwner handleLoginOwnerResultSet(ResultSet rset) throws SQLException {
		LoginOwner loginOwner = new LoginOwner();
		
		loginOwner.setId(rset.getString("id"));
		loginOwner.setPassword(rset.getString("password"));
		loginOwner.setName(rset.getString("name"));
		loginOwner.setId_no(rset.getInt("id_no"));
		return loginOwner;
	}
}
