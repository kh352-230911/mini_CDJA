package com.sh.menu.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.menu.model.entity.MemberMr;
import com.sh.menu.model.entity.OwnerMr;
import com.sh.menu.model.entity.RestListMr;
import com.sh.menu.model.exception.MenuException;

public class MenuDao {
	Properties prop = new Properties();
	
	public MenuDao() {

        try {
            prop.load(new FileReader("resources/member-query.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	//일반회원
	public int insertMember(Connection conn, MemberMr member) {
		String sql = prop.getProperty("insertMember");
		int result = 0;
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPassword());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getNickname());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MenuException("일반 회원 가입 오류", e);
		}
		return result;
	}
		
		//사장회원
		public int insertMember2(Connection conn, OwnerMr member) {
			
			if(!checkRestOwner (conn, member)) {
				String sql = prop.getProperty("insertMember2");
				int result = 0;
				
				PreparedStatement pstmt;
				try {
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, member.getId());
					pstmt.setString(2, member.getPassword());
					pstmt.setString(3, member.getName());
					pstmt.setInt(4, member.getId_no());
//					pstmt.setString(5, member.getRest_name());
					
					result = pstmt.executeUpdate();
				} catch (SQLException e) {
					throw new MenuException("사장 회원 가입 오류", e);
				}
				return result;
			}else 
				return 0;
	}

		public List<RestListMr> findRestByName(Connection conn, String name) {
		    List<RestListMr> restList = new ArrayList<>();
		    String sql = prop.getProperty("findRestByName");
		    
		    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
		        pstmt.setString(1, "%" + name + "%");
		        
		        try (ResultSet rset = pstmt.executeQuery()) {
		            while (rset.next()) {
		                restList.add(handleRestResultSet(rset));
		            }
		        }
		    } catch (SQLException e) {
		        throw new MenuException("가게 이름 검색 오류", e);
		    }

		    return restList;
		}
		private RestListMr handleRestResultSet(ResultSet rset) throws SQLException {
		    RestListMr restListMr = new RestListMr();
		    restListMr.setId_no(rset.getInt("id_no"));
		    restListMr.setName(rset.getString("name"));
		    restListMr.setAddr(rset.getString("addr"));
		    restListMr.setCategoryName(rset.getString("category"));
		    restListMr.setKindshop(rset.getString("kindshop"));
		    
		    return restListMr;
		}

		public boolean checkRestOwner (Connection conn, OwnerMr member) {
			int count = 0;
			String sql = prop.getProperty("checkRestOwner");
			try (PreparedStatement pstmt = conn.prepareCall(sql)) {
				pstmt.setInt(1, member.getId_no());
				
				try (ResultSet rset = pstmt.executeQuery()) {
					if(rset.next()) {
						count = rset.getInt(1);
					}
				}
			} catch (SQLException e) {
				throw new MenuException("회원가입 가게 사장 중복 확인 오류!", e);
			}
			return count > 0;
		}

		public boolean checkRestOwnerByIdNo (Connection conn, int idNo) {
			int count = 0;
			String sql = prop.getProperty("checkRestOwner");
			try (PreparedStatement pstmt = conn.prepareCall(sql)) {
				pstmt.setInt(1, idNo);
				
				try (ResultSet rset = pstmt.executeQuery()) {
					if(rset.next()) {
						count = rset.getInt(1);
					}
				}
			} catch (SQLException e) {
				throw new MenuException("회원가입 가게 사장 중복 확인 오류!", e);
			}
			return count > 0;
		}
		
		
		

}
