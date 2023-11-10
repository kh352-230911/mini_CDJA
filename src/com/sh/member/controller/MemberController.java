package com.sh.member.controller;

import java.util.List;

import com.sh.member.model.entity.FavShop;
import com.sh.member.model.entity.Menu;
import com.sh.member.model.entity.RestList;
import com.sh.member.model.service.MemberService;

public class MemberController {
	private MemberService memberService = new MemberService();

	public List<RestList> findByrest(String local, String category) {
		List<RestList> lists=null;
		try {
			lists=memberService.findByrest(local,category);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
		}
		return lists;
	}

	public List<RestList> findKindRest() {
		List<RestList> kind=null;
		try {
			kind=memberService.findKindRest();
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
		}
		return kind;
	}

//	public List<Menu> findMenu(int inputNo) {
//		 List<Menu> foodMenu = null;
//         try {
//             foodMenu =memberService.findMenu(inputNo);
//         } catch (Exception e) {
//             e.printStackTrace();
//             System.err.println("불편을 드려 죄송합니다." + e.getMessage());
//
//         }
//		return foodMenu;
//	}
	
	public List<Menu> findMenu(String inputNo, String local, String category) {
        List<Menu> foodMenu = null;
        try {
            foodMenu =memberService.findMenu(inputNo,local,category);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("불편을 드려 죄송합니다." + e.getMessage());

        }
        return foodMenu;
    }

	public List<FavShop> findByFavShop(String id) {
		List<FavShop> favShop = null;
		try {
			favShop = memberService.findByFavShop(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return favShop;
	}
	public int insertFavShop(String id, int restNo){
		int result=0;
		try {
			result=memberService.insertFavShop(id,restNo);
		}catch (Exception e) {
			System.err.println("불편을 드려 죄송합니다"+e.getMessage());
		}
		return result;
	}

	public int deleteFavShop(String id, int delNo) {
		int result=0;
		try {
			result=memberService.deleteFavShop(id,delNo);
		}catch (Exception e) {
			System.err.println("불편을 드려 죄송합니다"+e.getMessage());
		}
		return result;
	}
}
