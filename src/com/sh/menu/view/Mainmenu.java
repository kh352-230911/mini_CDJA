package com.sh.menu.view;

import java.util.List;
import java.util.Scanner;

import com.sh.member.view.MemberMenu;
import com.sh.menu.controller.Controller;
import com.sh.menu.controller.LoginController;
import com.sh.menu.model.entity.LoginMember;
import com.sh.menu.model.entity.LoginOwner;
import com.sh.menu.model.entity.MemberMr;
import com.sh.menu.model.entity.OwnerMr;
import com.sh.menu.model.entity.RestListMr;
import com.sh.owner.view.OwnerMenu;

public class Mainmenu {
	private LoginController loginController  = new LoginController();
	private Controller controller = new Controller();
	Scanner sc = new Scanner(System.in);
	String id = null;
	String password = null;
	LoginMember loginMember = null;
	LoginOwner loginOwner = null;

	public void mainMenu() {
		String menu = """
				===== 무럭무럭 =====
				1. 일반 회원 로그인
				2. 사장 회원 로그인
				3. 회원가입
				0. 프로그램 종료
				==================
				선택 : """;
		while (true) {
			System.out.println();
			System.out.print(menu);
			String choice = sc.next();
			switch (choice) {
			case "1":
				id = inputId();
				password = inputPassword();
				loginMember = loginController.login(id,password);
				loginOX(loginMember);
				//일반회원 로그인 
				break;
			case "2":
				id = inputId();
				password = inputPassword();
				loginOwner = loginController.login2(id,password);
				loginOX2(loginOwner);
				//사장회원 로그인 
				break;
			case "3":
				// 회원가입
				inputMember1();
                break;
			case "0":
				// 프로그램종료
				return;
			default:
				System.out.println("잘못 입력하셨습니다.");
				break;
			}
		}

	}
	private void inputMember1() {
        MemberMr membermr = null;
        OwnerMr ownermr = null;
        String restname = null; 
        String name = null;
        List<RestListMr> restList = null;
        String menu = """
                ===== 무럭무럭 =====
                1. 일반회원 가입
                2. 사장회원 가입
                0. 프로그램 종료
                ==================    
                선택 : """;
        System.out.println(menu);
        String choice = sc.next();
        int result = 0;

        switch (choice) {
            case "1":
                // 일반회원 가입
                membermr = inputMember();
                result = controller.insertMember(membermr);
                displayResult("회원가입", result);
                break;
            case "2":
                // 사장회원가입
            	// 가게이름검색
            	restname = inputRestName(); 
            	restList = controller.findByName(restname);
            	displayRest(restList);
            	if (restList.isEmpty())
            		 return;
            	int choiceNum = inputChoice();
//                ownermr = inputMember2(restname,choiceNum);
                result = checkRestOwner(restname,ownermr,choiceNum);
                displayResult("회원가입", result);
                break;
            case "0":
                // 프로그램 종료
                break;
            default:
                System.out.println("잘못된 선택입니다. 다시 시도하세요.");
                break;
        }
//        MemberMr membermr1 = new MemberMr(); // Member 객체를 생성하고 초기화
//        return inputMember();
	}
	
	private void displayResult(String type, int result) {
       if (result > 0)
    	   System.out.println("🎉🎉 " + type + " 성공! 🎉🎉");
	   else
		   System.out.println("😭😭 " + type + " 실패! 😭😭");
	}
	 
	private OwnerMr inputMember2(String restname, int choiceNum) {
        OwnerMr ownermr = new OwnerMr();
        
        ownermr.setId_no(choiceRest(restname, choiceNum));
        System.out.println("> 회원정보를 입력하세요.");
        System.out.print("> 아이디 : ");
        ownermr.setId(sc.next());
        System.out.print("> 비밀번호 : ");
        ownermr.setPassword(sc.next());
        System.out.print("> 이름 : ");
        ownermr.setName(sc.next());

        return ownermr;
    }
	

    private MemberMr inputMember() {
        MemberMr membermr = new MemberMr(); // MemberMr 객체를 생성하고 초기화
        System.out.println("> 회원정보를 입력하세요.");
        System.out.print("> 아이디 : ");
        membermr.setId(sc.next());
        System.out.print("> 비밀번호 : ");
        membermr.setPassword(sc.next());
        System.out.print("> 이름 : ");
        membermr.setName(sc.next());
        System.out.print("> 닉네임 : ");
        membermr.setNickname(sc.next());
        return membermr;
    }

	private String inputRestName() {
		sc.nextLine();
		System.out.println("> 가게이름 검색 : ");
		return sc.nextLine();
	}
	
	private void displayRest(List<RestListMr> restlist) {
	    System.out.println("=======================================================================================");
	    System.out.printf("%-10s %-10s %-10s %-15s %-30s %n","번호", "가게이름","업종" ,"고유번호" , "주소");
	    System.out.println("=======================================================================================");

	    if (restlist.isEmpty()) {
	        System.out.println("\t\t 조회된 결과가 없습니다.");
	    } else {
	    	for(int i = 0; i < restlist.size(); i++) {
	    		System.out.printf("%-10s %-10s %-10s %-15s %-30s %n",
	    				(i + 1), 
	    				restlist.get(i).getName(), 
	    				restlist.get(i).getCategoryName(), 
	    				restlist.get(i).getId_no(),
	    				restlist.get(i).getAddr()); 
	    	}
	    }
	    System.out.println("=======================================================================================");
	}
	
	private int choiceRest(String restname, int choiceNum ) {
		List<RestListMr> restlist = controller.findByName(restname);
		int index = choiceNum -1;
		int idNo = 0;
		idNo = restlist.get(index).getId_no();
		
		return idNo;
	}
	private int inputChoice() {
		System.out.println("자신의 가게 번호를 선택해주세요.");
		int choiceNum = sc.nextInt();
		return choiceNum;
	}
	private int checkRestOwner(String restname, OwnerMr ownermr, int choiceNum) {
		List<RestListMr> restlist = controller.findByName(restname);
		//입력받은 식당이름을 찾고, 그이름의 식당리스트를 넣어둠 
		int index = choiceNum -1;
		int idNo = restlist.get(index).getId_no();
		int result = 0;
		ownermr = inputMember2(restname,choiceNum);
//		for(int i = 0; i < restlist.size(); i++) {
			if (controller.checkRestOwnerByIdNo(idNo) == true) {
				System.out.println("이미 가입자가 있는 매장입니다.");
				result = 0;
			} else {
				result = controller.insertMember(ownermr);
			}
//		}
		return result;
	}
	
	private String inputId() {
		System.out.print("아이디 : ");
		return sc.next();
	}
	private String inputPassword() {
		System.out.print("비밀번호 : ");
		return sc.next();
	}
	

	private void loginOX(LoginMember loginMember) {
		if (loginMember == null) {
			System.out.println("로그인을 실패했습니다.");
			return;
		}else if(loginMember.getidNo() == null){
			String id = loginMember.getId();
			MemberMenu membermenu=new MemberMenu();
			membermenu.mainMenu(id);
		}
	}
	
	private void loginOX2(LoginOwner loginOwner) {
		if (loginOwner == null) {
			System.out.println("로그인을 실패했습니다.");
			return;
		}else {
			int idNo = loginOwner.getId_no();
			new OwnerMenu().mainMenu(idNo);
		}
	}
}
