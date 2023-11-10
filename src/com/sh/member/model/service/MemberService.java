package com.sh.member.model.service;

import static com.sh.common.JdbcTemplate.close;
import static com.sh.common.JdbcTemplate.commit;
import static com.sh.common.JdbcTemplate.getConnection;
import static com.sh.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.sh.member.model.dao.MemberDao;
import com.sh.member.model.entity.FavShop;
import com.sh.member.model.entity.Menu;
import com.sh.member.model.entity.RestList;

public class MemberService {
	private MemberDao memberDao = new MemberDao();

	public List<RestList> findByrest(String local, String category) {
		Connection conn=getConnection();
		List<RestList> lists=memberDao.findByrest(conn,local,category);
		close(conn);
		return lists;
	}

	public List<RestList> findKindRest() {
		Connection conn =getConnection();
		List<RestList> kind=memberDao.findKindRest(conn);
		close(conn);
		return kind;
	}

//	public List<Menu> findMenu(int inputNo) {
//		Connection conn = getConnection();
//		List<Menu> foodMenu = memberDao.findMenu(conn, inputNo);
//        close(conn);
//        return foodMenu;
//	}
	public List<Menu> findMenu(String inputNo, String local, String category) {
        Connection conn = getConnection();
        List<Menu> foodMenu = memberDao.findMenu(conn, inputNo,local,category);
        close(conn);
        return foodMenu;
    }

	public List<FavShop> findByFavShop(String id) {
		Connection conn=getConnection();
		List<FavShop> favShop=memberDao.findByFavShop(conn,id);
		close(conn);
		return favShop;
	}

	public int insertFavShop(String id, int restNo) {
		int result=0;
		Connection conn = getConnection();
		try {
			// 2. Dao요청
			result = memberDao.insertFavshop(conn, id,restNo);
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

	public int deleteFavShop(String id, int delNo) {
		int result=0;
		Connection conn = getConnection();
		try {
			// 2. Dao요청
			result = memberDao.deleteFavshop(conn, id,delNo);
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
}
