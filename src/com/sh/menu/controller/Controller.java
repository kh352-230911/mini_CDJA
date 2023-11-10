package com.sh.menu.controller;


import java.util.List;

import com.sh.menu.model.entity.MemberMr;
import com.sh.menu.model.entity.OwnerMr;
import com.sh.menu.model.entity.RestListMr;
import com.sh.menu.model.service.MenuService;

public class Controller {
	private MenuService menuService = new MenuService();
	
	public int insertMember(MemberMr member) {
		int result = 0;
		try {
			result = menuService.insertMember(member); 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
		}
		return result;
	}
	
	public int insertMember(OwnerMr member) {
		int result = 0;
		try {
			result = menuService.insertMember2(member); 
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
		}
		return result;
	}
	
//	public List<RestListMr> findByName(String name) {
//		
//		return menuService.findByName(name);
//	}
	
    //리스트 수정하기
    public List<RestListMr> findByName(String name) {
        List<RestListMr> restList = null;
        try {
            restList = menuService.findByName(name);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("> 불편을 드려 죄송합니다. : " + e.getMessage());
        }
        return restList;
    }

	public boolean checkRestOwnerByIdNo(int idNo) {
		boolean result = true;
		try {
			result = menuService.checkRestOwnerByIdNo(idNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("불편을 드려 죄송합니다." + e.getMessage());
		}
		return result;
	}
}

