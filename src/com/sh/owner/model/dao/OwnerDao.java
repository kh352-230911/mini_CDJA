package com.sh.owner.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.owner.model.entity.FoodMenu;
import com.sh.owner.model.exception.OwnerException;

public class OwnerDao {
	
	Properties prop = new Properties();
	
	public OwnerDao() {
		
		try {
			prop.load(new FileReader("resources/owner-query.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int doKindshop(Connection conn, int idNo) {
		String sql = prop.getProperty("doKindshop");
		int result = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OwnerException("착한식당 설정 오류!",e);
		}
		return result;
	}

	public int endKindshop(Connection conn, int idNo) {
		String sql = prop.getProperty("endKindshop");
		int result = 0;
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idNo);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			throw new OwnerException("착한식당 설정 오류!",e);
		}
		return result;
	}

	public List<FoodMenu> findByidNo(Connection conn, int idNo) {
		List<FoodMenu> foodMenus = new ArrayList<>();
		String sql = prop.getProperty("displayMenu");
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, idNo);
			try (ResultSet rset = pstmt.executeQuery()) {
				while(rset.next()) {
					foodMenus.add(handleFoodMenuResultSet(rset));
				}
			}
		} catch (SQLException e) {
			throw new OwnerException("메뉴판 출력 오류!",e);
		}
		
		return foodMenus;
	}

	private FoodMenu handleFoodMenuResultSet(ResultSet rset) throws SQLException {
		FoodMenu foodMenu = new FoodMenu();
//		foodMenu.setMenuNo(rset.getInt("menu_no"));
		foodMenu.setMenuName(rset.getString("menu_name"));
		foodMenu.setPrice(rset.getInt("price"));
		
		return foodMenu;
	}

	public int insertMenu(Connection conn, FoodMenu foodMenu) {
		if(!checkDupMenu(conn, foodMenu)) {
			int result = 0;
			String sql = prop.getProperty("insertMenu");
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, foodMenu.getIdNo());
				pstmt.setString(2, foodMenu.getMenuName());
				pstmt.setInt(3, foodMenu.getPrice());
				
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new OwnerException("메뉴 추가 오류!",e);
			}
			return result;
			
		} else
			return 0;
	}

	public int deleteMenu(Connection conn, String deleteMenuSelect) {
		
			int result = 0;
			String sql = prop.getProperty("deleteMenu");
			
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1,deleteMenuSelect);
				result = pstmt.executeUpdate();
			} catch (SQLException e) {
				throw new OwnerException("메뉴 삭제 오류!", e);
			}
			return result;
		
	}
	
	public boolean checkDupMenu(Connection conn, FoodMenu foodMenu ) {
		String sql = prop.getProperty("checkDupMenu");
		int count = 0;
		
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1,foodMenu.getIdNo());
			pstmt.setString(2,foodMenu.getMenuName() );
			
			try (ResultSet rset = pstmt.executeQuery()) {
				if(rset.next()) {
					count = rset.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new OwnerException("중복 메뉴 체크 오류!", e);
		}
		return count > 0;
	}

}
