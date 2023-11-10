package com.sh.owner.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.owner.model.dao.OwnerDao;
import com.sh.owner.model.entity.FoodMenu;

public class OwnerService {
	private OwnerDao ownerDao = new OwnerDao();

	public int doKindshop(int idNo) {
		int result = 0;
		Connection conn = getConnection();

		try {
			result = ownerDao.doKindshop(conn, idNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public int endKindshop(int idNo) {
		int result = 0;
		Connection conn = getConnection();

		try {
			result = ownerDao.endKindshop(conn, idNo);
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}

		return result;
	}

	public List<FoodMenu> findByidNo(int idNo) {
		Connection conn = getConnection();
		List<FoodMenu> foodMenus = ownerDao.findByidNo(conn, idNo);
		close(conn);
		return foodMenus;
	}

	public int insertMenu(FoodMenu foodMenu) {
		int result = 0;
		
		Connection conn = getConnection();
		
		try {
			result = ownerDao.insertMenu(conn, foodMenu);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		return result;
	}

	public int deleteMenu(String deleteMenuSelect) {
		int result = 0;
		Connection conn = getConnection();
		
		try {
			result = ownerDao.deleteMenu(conn, deleteMenuSelect);
			
			commit(conn);
		} catch (Exception e) {
			rollback(conn);
			throw e;
		} finally {
			close(conn);
		}
		
		return result;
	}

}
