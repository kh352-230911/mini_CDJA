package com.sh.menu.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.lang.reflect.Member;
import java.sql.Connection;
import java.util.List;

import com.sh.menu.model.dao.MenuDao;
import com.sh.menu.model.entity.MemberMr;
import com.sh.menu.model.entity.OwnerMr;
import com.sh.menu.model.entity.RestListMr;

public class MenuService {
	private MenuDao menuDao = new MenuDao();

	public int insertMember(MemberMr member) {
		int result = 0;
		//  1. Connection 생성 (url, user, password) - autoCommit false설정
		Connection conn = getConnection();
		try {
			// 2. Dao요청
			result = menuDao.insertMember(conn, member);
			// 3. 트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4. 자원반납(conn)
			close(conn);
		}
		return result;
	}
	
	public int insertMember2(OwnerMr member) {
		int result = 0;
		//  1. Connection 생성 (url, user, password) - autoCommit false설정
		Connection conn = getConnection();
		try {
			// 2. Dao요청
			result = menuDao.insertMember2(conn, member);
			// 3. 트랜잭션처리
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			// 4. 자원반납(conn)
			close(conn);
		}
		return result;
	}
	
//	public List<RestListMr> findByName(String name) {
//	    Connection conn = getConnection();
//	    List<RestListMr> restList = null;
//	    try {
//	        restList = menuDao.findRestByName(conn, name);
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    } finally {
//	        close(conn);
//	    }
//	    return restList;
//	}
	
	public List<RestListMr> findByName(String name) {
        Connection conn = getConnection();
        List<RestListMr> restList = menuDao.findRestByName(conn, name);
        close(conn);
        return restList;
    }

	public boolean checkRestOwnerByIdNo(int idNo) {
		boolean result = true;
		Connection conn = getConnection();
		result = menuDao.checkRestOwnerByIdNo(conn, idNo);
		close(conn);
		
		return result;
	}

	
}
