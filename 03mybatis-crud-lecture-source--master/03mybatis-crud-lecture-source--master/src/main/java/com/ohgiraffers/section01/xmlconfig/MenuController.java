package com.ohgiraffers.section01.xmlconfig;

import java.util.List;
import java.util.Map;

public class MenuController {

    private  final  MenuService menuService; // 파이널은 여기 초기화 하고나면 다른 값이 담길수 없다 라는 뜻임 그래서 할당된 값이 변하지 않음
    private final PrintResult printResult; //필드하나 더 생성

    public  MenuController(){
        menuService = new MenuService(); //메뉴 서비스를 호출 할거라서 미리 만들어둠
        printResult = new PrintResult(); //결과 보여주는
    }

    //메뉴 전체 조회
    public void selectAllMenu() {
                            //메뉴서비스 클래스에 selectAllMenu 매소드생성
        List<MenuDTO> menuList = menuService.selectAllMenu(); //모든 메뉴를 조회한다는 이름을 가지고 DTO라는 형태로 결과를 가져오면 좋겠다 라는 의미

        //결과에 따라 사용자에게 어떤 화면을 보여줄건지 쓰기
        if (menuList != null){
            printResult.printMenuList(menuList); //null이 아니면 printMenuList를 호출해서 출력
        }else {
            printResult.printErrorMessage("selectList");//아니면 여기 에러메세지를 출력
        }
    }

    //메뉴 코드로 메뉴 조회
    public void selectMenuByCode(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code")); //int로 가공함

        MenuDTO menu = menuService.selectMenuByCode(code);  //서비스쪽에서 메소드 생성

        if (menu != null){
            printResult.printMenu(menu); // null아니면 menu로
        }else {
            printResult.printErrorMessage("selectOne"); //에러 발생한거 출력
        }
    }

    //신규메뉴 추가
    public void registMenu(Map<String,String> parameter) {

        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu= new MenuDTO();
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        if (menuService.regisMenu(menu)){
            printResult.printSuccessMessage("insert");
        }else {
            printResult.printErrorMessage("insert");
        }
    }

    //메뉴 수정
    public void modifyMenu(Map<String, String> parameter ) {
        int code = Integer.parseInt(parameter.get("code"));
        String name = parameter.get("name");
        int price = Integer.parseInt(parameter.get("price"));
        int categoryCode = Integer.parseInt(parameter.get("categoryCode"));

        MenuDTO menu= new MenuDTO();
        menu.setCode(code);
        menu.setName(name);
        menu.setPrice(price);
        menu.setCategoryCode(categoryCode);

        if (menuService.modifyMenu(menu)){
            printResult.printSuccessMessage("update");
        }else {
            printResult.printErrorMessage("update");
        }
    }

    //메뉴 삭제
    public void deleteMenu(Map<String, String> parameter) {

        int code = Integer.parseInt(parameter.get("code"));

        if(menuService.deleteMenu(code)) {
            printResult.printSuccessMessage("delete");
        } else {
            printResult.printErrorMessage("delete");
        }
    }
}
