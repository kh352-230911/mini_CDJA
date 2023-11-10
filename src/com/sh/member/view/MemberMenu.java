package com.sh.member.view;

import java.util.List;
import java.util.Scanner;

import com.sh.member.controller.MemberController;
import com.sh.member.model.entity.FavShop;
import com.sh.member.model.entity.Menu;
import com.sh.member.model.entity.RestList;

public class MemberMenu {
	private MemberController memberController = new MemberController();
	Scanner sc= new Scanner(System.in);
	List<RestList> lists=null;
	List<RestList> kind=null;
	List<Menu> foodMenu=null;
	List<FavShop> favShop=null;
	int result=0;
	public void mainMenu(String id) {
		String menu = """
				===== 무럭무럭 =====
				1. 식당검색
				2. 착한식당조회
				3. 즐겨찾기메뉴
				0. 로그아웃
				==========================	
				선택 : """;
		String localMenu="""
				1.강남구 2.강서구 3.은평구 4.강동구 5.노원구
				""";
		while(true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			
			switch (choice) {
			case "1":
				System.out.println(localMenu);
				System.out.println("검색할 지역을 입력하세요: ");
				String local=sc.next();
				displayrest(local);
				break;
			case "2": 
				kind=memberController.findKindRest();
				displaykind(kind);
				break;
			case "3": 
				favshopmenu(id);
				
				break;
			case "0": 
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				continue;
			}
		
	}

}
	private void favshopmenu(String id) {
		String menu = """
				===== 무럭무럭 =====
				1. 즐겨찾기한 식당 조회
				2. 즐겨찾기 등록
				3. 즐겨찾기 해제
				0. 이전 메뉴
				==========================	
				선택 : """;
		while(true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			switch (choice) {
			case "1":
				favShop=memberController.findByFavShop(id);
				displayFavShop(favShop);
				break;
			case "2":
				System.out.println("등록하고 싶은 가게의 번호를 입력하세요: ");
				int restNo=sc.nextInt();
				result=memberController.insertFavShop(id,restNo);
				displayinsertFav(result);
				break;
			case "3":
				favShop=memberController.findByFavShop(id);
				displayFavShop(favShop);
				if(!(favShop.isEmpty())) {
				System.out.println("해제하고 싶은 가게의 번호를 입력하세요: ");
				int delNo=sc.nextInt();
				result=memberController.deleteFavShop(id,delNo);
				displaydeleteFav(result);
				}
				break;
			case "0": return;
			default : 
				System.out.println("잘못 입력하셨습니다.");
				continue;
				
			}
			
		}
		
	}
	
	private void displaydeleteFav(int result) {
		if(result > 0)
			System.out.println("즐겨찾기 해제 성공!");
		else
			System.out.println("등록되지않은 식당입니다");
		
	}
	private void displayinsertFav(int result) {
		if(result > 0)
			System.out.println("등록 성공!");
		else
			System.out.println("이미 등록돼있는 식당이거나 없는 식당입니다!");
	}
	private void displayFavShop(List<FavShop> favShop2) {
		System.out.println("==========================================================================");
		System.out.printf("%-10s%-10s%-10s%-10s%-20s\n","식당번호","식당이름","업종","착한식당","식당주소");
		System.out.println("==========================================================================");
		if(favShop2 == null ||favShop2.isEmpty()) {
			System.out.println("\t\t 조회된 결과가 없습니다.");
		}
		else {
			for(FavShop list : favShop2) {
				
				System.out.printf("%-10d%-10s%-13s%-10s%-15s\n",
				        list.getRestId(),
				        list.getRestName(),
				        list.getCategory(),
				        list.getKindShop(),
				        list.getAddr()
				);
			}
		}
		
	}
		
	
	private void displaykind(List<RestList> kind2) {
		System.out.println("==========================================================================");
		System.out.printf("%-10s%-10s%-10s%-10s%-20s\n","식당번호","식당이름","업종","착한식당","식당주소");
		System.out.println("==========================================================================");
		if(kind2 == null || kind2.isEmpty()) {
			System.out.println("\t\t 조회된 결과가 없습니다.");
			return;
		}
		else {
			for(RestList list : kind2) {
				
				System.out.printf("%-10s%-10s%-10s%-10s%-20s\n",
				        list.getIdNo(),
				        list.getName(),
				        list.getCategory(),
				        list.getKindshop(),
				        list.getAddr()
				);
			}
		}
	}

	private void displayFoodMenus(List<Menu> foodMenus) {
        System.out.printf("%-10s%-10s%-10s\n", "번호", "메뉴명", "가격");
        for (Menu foodMenu : foodMenus) {
            System.out.printf("%-10s%-10s%-10s\n", 
                    foodMenu.getMenuNo(), 
                    foodMenu.getMenuName(), 
                    foodMenu.getPrice());
        }
    }
	
	private void displayrest(String local) {
        String categorymenu="""
                1.한식2.중식 3.일식 4.양식 5.패스트푸드 6.제과점 7.편의점 8.일반대중음식
                """;
        System.out.println(categorymenu);
        System.out.println("검색할 카테고리를 입력하세요: ");
        String category=sc.next();
        lists=memberController.findByrest(local,category);
        displaylist(lists, category, category);
        
    }
    private void displaylist(List<RestList> lists2,String local ,String category) {
        String menu="""
                1.메뉴조회
                0.이전메뉴
                """;

        if(lists2 == null || lists2.isEmpty()) {
            System.out.println("==========================================================================");
            System.out.printf("%-10s%-10s%-10s%-10s%-10s%-20s\n","번호","식당번호","식당이름","업종","착한식당","식당주소");
            System.out.println("==========================================================================");
            System.out.println("\t\t 조회된 결과가 없습니다.");
        }
        else {
            System.out.println("==========================================================================");
            System.out.printf("%-10s%-10s%-10s%-10s%-10s%-20s\n","번호","식당번호","식당이름","업종","착한식당","식당주소");
            System.out.println("==========================================================================");
            for(RestList list : lists2) {
                System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s\n",
                        list.getRownum(),
                        list.getIdNo(),
                        list.getName(),
                        list.getKindshop(),
                        list.getCategory(),
                        list.getAddr()
                );
            }
        }
        while(true) {
            System.out.println("===========================");
            System.out.print(menu);
            System.out.println("===========================");
            String choice = sc.next();
            switch (choice) {
            case"1":
                System.out.print("식당을 선택하세요: ");
                String inputNo=sc.next();
                displayMenu(inputNo,local,category);
                break;
            case"0": 
            	return;
            default:
                System.out.println("잘못 선택하셨습니다");
                return;
            }
        }
        
        
    }
    private void displayMenu(String inputNo, String local, String category) {
        foodMenu=memberController.findMenu(inputNo,local,category);
        displayFoodMenus(foodMenu);
        
    }
	
	
}