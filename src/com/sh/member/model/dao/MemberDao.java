package com.sh.member.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.sh.member.model.entity.FavShop;
import com.sh.member.model.entity.Menu;
import com.sh.member.model.entity.RestList;

public class MemberDao {
Properties prop = new Properties();
	
	public MemberDao() {
		try {
			prop.load(new FileReader("resources/list-query.properties"));
//			System.out.println(prop);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


//	public List<RestList> findByrest(Connection conn, String local, String category) {
//		List<RestList> lists = new ArrayList<>();
//		String sql = prop.getProperty("findByrest");
//		try (PreparedStatement pstmt = conn.prepareStatement(sql);){
//			pstmt.setString(1, local);
//			pstmt.setString(2, category);
//			try(ResultSet rset = pstmt.executeQuery()){
//				while(rset.next()) {
//					RestList list=new RestList();
//					list.setIdNo(rset.getInt(1)); // 첫 번째 열의 인덱스 (id_no)
//	                list.setName(rset.getString(2)); // 두 번째 열의 인덱스 (name)
//	                list.setKindshop(rset.getString(3)); // 여덟 번째 열의 인덱스 (kindshop)
//	                list.setCategory(rset.getString(4)); // 다섯 번째 열의 인덱스 (category)
//	                list.setAddr(rset.getString(5)); // 세 번째 열의 인덱스 (addr)
//	                
//					lists.add(list);
//				}
//			}
//		} catch (SQLException e) {
//			throw new MemberException("식당조회 오류",e);
//		}
//	return lists;
//		
//	}


	public List<RestList> findKindRest(Connection conn) {
		List<RestList> kind= new ArrayList<>();
		String sql = prop.getProperty("findKindRest");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
			try(ResultSet rset = pstmt.executeQuery()){
				while(rset.next()) {
					RestList list=new RestList();
					list.setIdNo(rset.getInt(1)); // 첫 번째 열의 인덱스 (id_no)
	                list.setName(rset.getString(2)); // 두 번째 열의 인덱스 (name)
	                list.setLocalCode(rset.getString(3)); // 여섯 번째 열의 인덱스 (local_code)
	                list.setAddr(rset.getString(4)); // 세 번째 열의 인덱스 (addr)
	                list.setKindshop(rset.getString(5)); // 여덟 번째 열의 인덱스 (kindshop)
	                list.setCategory(rset.getString(6)); // 다섯 번째 열의 인덱스 (category)
	                list.setLocalName(rset.getString(7)); // 일곱 번째 열의 인덱스 (local_name)
					kind.add(list);
				}
			}
		} catch (SQLException e) {
			throw new MemberException("착한식당조회 오류" ,e);
			
		}
		return kind;
	}


//	public List<Menu> findMenu(Connection conn, int inputNo) {
//		 List<Menu> foodMenu = new ArrayList<>();
//	        String sql = prop.getProperty("displayMenu");
//
//	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//	            pstmt.setInt(1, inputNo);
//	            try (ResultSet rset = pstmt.executeQuery()) {
//	            	 while(rset.next()) {
//	                     foodMenu.add(handleFoodMenuResultSet(rset));
//	                 }
//	            }
//	        } catch (SQLException e) {
//	            throw new MemberException("메뉴판 출력 오류!",e);
//	        }
//
//	        return foodMenu;
//	}
	
	public List<RestList> findByrest(Connection conn, String local, String category) {
        List<RestList> lists = new ArrayList<>();
        String sql = prop.getProperty("findByrest");
        try (PreparedStatement pstmt = conn.prepareStatement(sql);){
            pstmt.setString(1, local);
            pstmt.setString(2, category);
            try(ResultSet rset = pstmt.executeQuery()){
                while(rset.next()) {
                    RestList list=new RestList();
                    list.setRownum(rset.getString("rownum"));
                    list.setIdNo(rset.getInt("rest_id_no")); // 첫 번째 열의 인덱스 (id_no)
                    list.setName(rset.getString("rest_name")); // 두 번째 열의 인덱스 (name)
                    list.setKindshop(rset.getString("rest_kindshop")); // 여덟 번째 열의 인덱스 (kindshop)
                    list.setCategory(rset.getString("rest_category")); // 다섯 번째 열의 인덱스 (category)
                    list.setAddr(rset.getString("rest_addr")); // 세 번째 열의 인덱스 (addr)
                    
                    lists.add(list);
                }
            }
        } catch (SQLException e) {
            throw new MemberException("식당조회 오류",e);
        }
    return lists;
        
    }
	
	
	public List<Menu> findMenu(Connection conn, String inputNo, String local, String category) {
        List<Menu> foodMenu = new ArrayList<>();
        String sql = prop.getProperty("displayMenu");

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, local);
            pstmt.setString(2, category);
            pstmt.setString(3, inputNo);
            
            try (ResultSet rset = pstmt.executeQuery()) {
                 while(rset.next()) {
                     foodMenu.add(handleFoodMenuResultSet(rset));
                 }
            }
        } catch (SQLException e) {
            throw new MemberException("메뉴판 출력 오류!",e);
        }

        return foodMenu;
    }
	
	private Menu handleFoodMenuResultSet(ResultSet rset) throws SQLException {
        Menu foodMenu = new Menu();
        foodMenu.setMenuNo(rset.getInt("menu_no"));
        foodMenu.setMenuName(rset.getString("menu_name"));
        foodMenu.setPrice(rset.getInt("price"));

        return foodMenu;
    }


	public List<FavShop> findByFavShop(Connection conn, String id) {
		List<FavShop> favShop=new ArrayList<>();
		String sql=prop.getProperty("findByFavShop");
		try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, id);
            try (ResultSet rset = pstmt.executeQuery()) {
            	
                while(rset.next()) {
                	FavShop favShops =new FavShop();
                
                	
                	favShops.setRestId(rset.getInt("rest_id"));
                	favShops.setRestName(rset.getString("rest_name"));
                	favShops.setCategory(rset.getString("c_category"));
                	favShops.setKindShop(rset.getString("r_kindshop"));
                	favShops.setAddr(rset.getString("r_addr"));
                	favShop.add(favShops);
                	
                }
            
            } 
		}catch (SQLException e) {
        	throw new MemberException("즐겨찾는 식당 조회 오류",e);
        }
		return favShop;
	}


//	public int insertFavshop(Connection conn, String id, int restNo) {
//		
//			String sql = prop.getProperty("insertFavShop");
//			int result=0;
//			try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
//				
//				pstmt.setString(1, id);
//				pstmt.setInt(2, restNo);
//				
//				result=pstmt.executeUpdate();
//			} catch (SQLException e) {
//				throw new MemberException("즐겨찾기 등록 오류",e);
//			}
//			return result;
//		
//	}
	public int insertFavshop(Connection conn, String id, int restNO) {
	    if (!isDuplicateFavshop(conn, id, restNO)) {
	        String sql = prop.getProperty("insertFavShop");
	        int result = 0;
	        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            pstmt.setString(1, id);
	            pstmt.setInt(2, restNO);
	            result = pstmt.executeUpdate();
	        } catch (SQLException e) {
	            throw new MemberException("즐겨찾기 등록 오류", e);
	        }
	        return result;
	    }else
	    	return 0;
	}
	private boolean isDuplicateFavshop(Connection conn, String id, int restNO) {
	    String sql = prop.getProperty("isDuplicateFavshop");
	    int count = 0;

	    try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
	        pstmt.setString(1, id);
	        pstmt.setInt(2, restNO);

	        try (ResultSet resultSet = pstmt.executeQuery()) {
	            if (resultSet.next()) {
	                count = resultSet.getInt(1);
	            }
	        }
	    } catch (SQLException e) {
	        throw new MemberException("중복 체크 오류", e);
	    }

	    return count>0;
	}
	public int deleteFavshop(Connection conn, String id, int delNo) {
		String sql = prop.getProperty("deleteFavShop");
		int result=0;
		try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
			
			pstmt.setString(1, id);
			pstmt.setInt(2, delNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new MemberException("즐겨찾기 해제 오류",e);
		}
		return result;
	}
	
}
