package com.sh.owner.controller;

import java.util.List;

import com.sh.owner.model.entity.FoodMenu;
import com.sh.owner.model.service.OwnerService;

public class OwnerController {
	private OwnerService ownerService = new OwnerService();

	public int doKindshop(int idNo) {
		int result = 0;
		try {
			result = ownerService.doKindshop(idNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("불편을 드려 죄송합니다." + e.getMessage());
		}
		
		return result;
	}

	public int endKindshop(int idNo) {
		int result = 0;
		try {
			result = ownerService.endKindshop(idNo);
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("불편을 드려 죄송합니다." + e.getMessage());
		}
		
		return result;
	}

	public List<FoodMenu> findByidNo(int idNo) {
		List<FoodMenu> foodMenus = null;
			try {
				foodMenus = ownerService.findByidNo(idNo);
			} catch (Exception e) {
				e.printStackTrace();
				System.err.println("불편을 드려 죄송합니다." + e.getMessage());
				
			}
			
		return foodMenus;
	}

	public int insertMenu(FoodMenu foodMenu) {
		int result = 0;
		try {
			result = ownerService.insertMenu(foodMenu);
		} catch (Exception e) {
			System.err.println("불편을 드려 죄송합니다." + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public int deleteMenu(String deleteMenuSelect) {
		int result = 0;
		
			try {
				result = ownerService.deleteMenu(deleteMenuSelect);
			} catch (Exception e) {
				System.err.println("불편을 드려 죄송합니다." + e.getMessage());
				e.printStackTrace();
			}
		return result;
	}
}
