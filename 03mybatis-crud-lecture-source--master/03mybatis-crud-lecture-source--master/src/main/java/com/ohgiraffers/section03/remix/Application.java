package com.ohgiraffers.section03.remix;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        // XML 방법과 JAVA CODE 방법 두가지의 장점들로 믹스해 만들어보자

        Scanner sc = new Scanner(System.in);
        MenuController menuController = new MenuController();

        do {
            System.out.println("=============메뉴관리=============");
            System.out.println("1. 메뉴 전체 조회");
            System.out.println("2. 메뉴 코드로 메뉴 조회");
            System.out.println("3. 신규메뉴 추가");
            System.out.println("4. 메뉴수정");
            System.out.println("5. 메뉴 삭제");
            System.out.println("메뉴관리 번호 입력 : ");
            int no = sc.nextInt();

            switch (no){
                case 1: menuController.selectAllMenu(); break; //전체조회하는 메소드를 만듬
                case 2: menuController.selectMenuByCode(inputMenuCode()); break; //selectMenuByCode를 inputMenuCode메소드 만들어서 스캐너로 입력받을것
                case 3: menuController.registMenu(inputMenu()); break;
                case 4: menuController.modifyMenu(inputModifyMenu()); break;
                case 5: menuController.deleteMenu(inputMenuCode()); break;
                default:
                    System.out.println("잘못된 메뉴를 선택하셨습니다");
            }
        }while (true);
    }

    //메뉴 코드로 메뉴 조회 , 삭제할때도 참조함
    private static Map<String, String> inputMenuCode() {

        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴코드를 입력하세요 : ");
        String code = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();//위 스캐너로 받은걸 맵에다가 넣어서 반환하겠다 그게 inputMenuCode로 넘어간다(문자열로)
        parameter.put("code", code);

        return parameter;
    }

    //메뉴 등록
    private static Map<String, String> inputMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("메뉴 이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.println("메뉴 가격을 입력 하세요 : ");
        String price = sc.nextLine();
        System.out.println("카테고리 코드를 입력하세요 : ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("name",name);
        parameter.put("price", price);
        parameter.put("categoryCode",categoryCode);

        return parameter;
    }
    //4번 메뉴 수정
    private static Map<String, String> inputModifyMenu() {

        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 메뉴 코드를 입력하세요 : ");
        String code = sc.nextLine();
        System.out.println("수정할 메뉴 이름를 입력하세요 : ");
        String name = sc.nextLine();
        System.out.println("수정할 메뉴 가격을 입력 하세요 : ");
        String price = sc.nextLine();
        System.out.println("수정할 카테고리 코드를 입력하세요 : ");
        String categoryCode = sc.nextLine();

        Map<String, String> parameter = new HashMap<>();
        parameter.put("code",code);
        parameter.put("name",name);
        parameter.put("price", price);
        parameter.put("categoryCode",categoryCode);

        return parameter;
    }
}
