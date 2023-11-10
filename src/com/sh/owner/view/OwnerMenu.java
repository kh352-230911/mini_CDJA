package com.sh.owner.view;

import java.util.List;
import java.util.Scanner;


import com.sh.owner.controller.OwnerController;
import com.sh.owner.model.entity.FoodMenu;
import com.sh.owner.model.entity.Owner;

public class OwnerMenu {
	private Scanner sc = new Scanner(System.in);
	private OwnerController ownerController = new OwnerController();
	
	List<Owner> owners = null;
	Owner owner = null;
	int result = 0;
	
	
	
	
	public void mainMenu(int idNo) {
		String menu = """
				===============================
				           무럭무럭(사장)
				===============================
				1. 메뉴변경
				2. 착한식당(등록/해제)
				0. 로그아웃
				===============================

				""";
		while (true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();

			

			switch (choice) {
			case "1":
				changeFood(idNo);
				break;
			case "2":
				kindRest(idNo);
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

			}

		}
	}

	public void kindRest(int idNo) {
		String menu = """
				===============================
				        착한식당 등록/해제
				===============================
				1. 착한식당 등록신청
				2. 착한식당 등록해제
				0. 이전 메뉴
				===============================

				""";

		while (true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();

			switch (choice) {
			case "1":
				ownerController.doKindshop(idNo);
				if(result > 0) 
				System.out.println("착한 식당으로 등록되었습니다.");
				break;
			case "2":
				ownerController.endKindshop(idNo);
				System.out.println("착한 식당 등록이 해제되었습니다.");
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

			}

		}

	}

	public void changeFood(int idNo) {
		
		int result = 0;
		
		List<FoodMenu> foodMenus = null;
		FoodMenu foodMenu = null;

		String menu1 = """
				===============================
				             메뉴변경
				===============================
				""";
		String menu2 = """
				-------------------------------
				1. 메뉴 추가
				2. 메뉴 삭제
				0. 이전 메뉴
				===============================

				""";

		while (true) {

			System.out.print(menu1);
			foodMenus = ownerController.findByidNo(idNo);
			displayFoodMenus(foodMenus);
			System.out.println();
			System.out.print(menu2);
			String choice = sc.next();

			switch (choice) {
			case "1":
				foodMenu = inputMenu(idNo);
				result = ownerController.insertMenu(foodMenu);
				displayResult("메뉴 추가 ", result);
				break;
			case "2":
				result = checkMenuListExist(idNo);
				displayResult("메뉴 삭제 ", result);
				break;
			case "0":
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;

			}

		}

	}

	private void displayFoodMenus(List<FoodMenu> foodMenus) {
		System.out.printf("%-10s%-10s%-10s\n", "번호", "메뉴명", "가격");
		for(int i = 0; i < foodMenus.size(); i++) {
			System.out.printf("%-10d%-10s%-10d\n",
					(i+1), foodMenus.get(i).getMenuName(), 
					foodMenus.get(i).getPrice());
		}
		
	}

	private FoodMenu inputMenu(int idNo) {
		FoodMenu foodMenu = new FoodMenu();
		System.out.println("새로운 메뉴 정보를입력해주세요");
		sc.nextLine();
		System.out.print("메뉴명 : ");
		String foodName = sc.nextLine();
		foodMenu.setMenuName(foodName);
		System.out.print("가격 : ");
		foodMenu.setPrice(sc.nextInt());
		foodMenu.setIdNo(idNo);

		return foodMenu;
	}
	
	private void displayResult(String type, int result) {
		if(result > 0) {
			System.out.println(type+ " 성공!");
		}else {
			System.out.println(type+ " 실패!");
		}
		
	}
	
	
	private int checkMenuListExist(int idNo) {
		result = 0;
		List<FoodMenu> foodMenus = ownerController.findByidNo(idNo);
	
			if(foodMenus.isEmpty()) {
				System.out.println("메뉴가 존재하지 않습니다.");
				result = 0;
			} else
			result = ownerController.deleteMenu(deleteMenuSelect(idNo));
			return result;
		
	}
	private String deleteMenuSelect(int idNo) {
		
		List<FoodMenu> foodMenus = ownerController.findByidNo(idNo);
		System.out.println("삭제할 메뉴 번호를 입력해주세요.");
		int choice = sc.nextInt();
		int index = choice - 1;
		String deleteMenuName = foodMenus.get(index).getMenuName();
		return deleteMenuName;
	}

}
