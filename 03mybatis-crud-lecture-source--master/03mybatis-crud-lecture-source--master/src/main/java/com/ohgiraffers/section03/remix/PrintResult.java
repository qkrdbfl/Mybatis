package com.ohgiraffers.section03.remix;

import java.util.List;

public class PrintResult {


    public void printMenuList(List<MenuDTO> menuList) {
        menuList.forEach(System.out::println);
    }

    public void printMenu(MenuDTO menu) {

        System.out.println(menu); // 성공 조회 출력 구문
    }

    public void printErrorMessage(String errorCode) {
        String errorMessage = ""; //에러메세지 문자열로 한ㄴ다 라는 의미
        switch (errorCode){
            case "selectList" : errorMessage = "메뉴 목록 조회에 실패"; break;
            case "selectOne" : errorMessage = "메뉴 조회에 실패"; break;
            case "insert" : errorMessage = "메뉴등록에 실패"; break;
            case "update" : errorMessage = "메뉴 수정이 실패"; break;
            case "delete" : errorMessage = "메뉴 삭제에 실패"; break;
        }
        System.out.println(errorMessage);
    }


    public void printSuccessMessage(String successCode) {
        String successMessage = "";
        switch (successCode){
            case "insert" : successMessage = "신규메뉴 등록이 완료"; break;
            case "update" : successMessage = "메뉴 수정이 완료"; break;
            case "delete" : successMessage = "메뉴 삭제가 완료 "; break;
        }
        System.out.println(successMessage);
    }
}
