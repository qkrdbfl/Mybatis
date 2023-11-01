package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.SearchCriteria;

import java.util.*;

// 섹션 1은 XML로 써본거
public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("========== 마이바티스 동적 SQL ==========");
            System.out.println("1. if 확인하기");
            System.out.println("2. choose (when, otherwise) 확인하기");
            System.out.println("3. foreach 확인하기");
            System.out.println("4. trim (where, set) 확인하기");
            System.out.println("9. 종료하기");
            System.out.print("메뉴를 선택하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : ifSubMenu(); break;
                case 2 : chooseSudMenu(); break;
                case 3 : foreachSudMenu(); break;
                case 4 : trimSubMenu(); break;
                case 9 :
                    System.out.println("프로그램을 종료합니다."); return;
            }

        } while(true);
    }

    //마이바티스 1번
    private static void ifSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("========== if 서브 메뉴 ==========");
            System.out.println("1. 원하는 금액대에 적합한 추천 메뉴 목록 보여주기");
            System.out.println("2. 메뉴 이름 혹은 카테고리명으로 검색하여 메뉴 목록 보여주기");
            System.out.println("9. 이전 메뉴로");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : menuService.selectMenuByPrice(inputPrice()); break; //메뉴서비스에 selectMenuByPrice 메소드 생성.List로 목록을 받음
                case 2 : menuService.searchMenu(inputSearchCriteria()); break;
                case 9 : return;
            }
        } while(true);
    }

    //1.if (1)서브메뉴
    private static int inputPrice() { //inputPrice 생성. 가격 전달 받음
        Scanner sc = new Scanner(System.in);
        System.out.print("검색하실 가격의 최대 금액을 입력해주세요 : ");
        int price = sc.nextInt();

        return price;
    }

    //2.if (2)서브메뉴
    private static SearchCriteria inputSearchCriteria() {

        Scanner sc = new Scanner(System.in);

        System.out.println("검색 기준을 입력하세요(name or category) : ");
        String condition = sc.nextLine();
        System.out.println("검색어를 입력하세요 : ");
        String value = sc.nextLine();

        return new SearchCriteria(condition,value); //사용자에 따라 조건이 달라짐
    }

    //마이바티스 2번
    private static void chooseSudMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("============choose 서브 메뉴===========");
            System.out.println("1. 카테고리 상위 분류별 메뉴 보여주기 (식사, 음료, 디저트)");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuBySupCategory(inputSupCategory()); break;
                case 9 : return;
            }
        }while (true);
    }

    //choose 서브메뉴
    private static SearchCriteria inputSupCategory() {
        Scanner sc = new Scanner(System.in);

        System.out.println("상위 분류를 입력해 주세요 (식사, 음료, 디저트) :");
        String value = sc.nextLine();

        return new SearchCriteria("category",value);
    }

    //마이바티스 3번
    private static void foreachSudMenu() {

        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("============ foreach 서브 메뉴 ===========");
            System.out.println("1. 랜덤한 메뉴 5개 추출해서 조회하기");
            System.out.println("9. 이전 메뉴로 ");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByRandomMenuCode(createRandomMenuCodeList()); break;
                case 2 : menuService.searchMenuByNameOrCategory(inputSearchCriteriaMap()); break;
                case 9 : return;
            }
        }while (true);
    }

    // foreach 서브메뉴
    private static List<Integer> createRandomMenuCodeList() {

        Set<Integer> set = new HashSet<>(); //중복 저장안함 순서 없음
        while (set.size()<5){ //사이즈가 0에서 5가 되는동안 중복값 안생김
            int temp = ((int)(Math.random()*21)) +1; // 1~21 값
            set.add(temp);
        }
        List<Integer> list = new ArrayList<>(set); // 생성자를 set을 써서 리스트가 됨
        Collections.sort(list); // 정렬됨

        return list;
    }

    //마이바티스 3번
    private static void trimSubMenu() {

        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();
        do {
            System.out.println("============ trim 서브 메뉴 ===========");
            System.out.println("1. 검색조건이 있는 경우 메뉴코드로 조회, 단 없으면 전체 조회");
            System.out.println("2. 메뉴 혹은 카테고리코드로 검색, 단 메뉴와 카테고리 둘다 일치하는 경우도 검색하며, " +
                    "검색 조건이 없는 경우 전체 검색");
            System.out.println("3. 원하는 메뉴 정보만 수정하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : menuService.searchMenuByCodeOrSearchAll(inputAllOrOne()); break;
                case 2 : menuService.searchMenuByNameOrCategory(inputSearchCriteriaMap()); break;
                case 3 : menuService.modifyMenu(inputChangeInfo()); break;
                case 9 : return;
            }
        }while (true);
    }

    //trim (1)서브메뉴
    private static SearchCriteria inputAllOrOne() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 조건을 입력하시겠습니까?(예 or 아니오) : ");
        boolean hasSearchValue = "예".equals(sc.nextLine());

        SearchCriteria searchCriteria = new SearchCriteria();
        if (hasSearchValue){
            System.out.print("검색할 메뉴 코드를 입력하세요 : ");
            String code = sc.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(code);
        }
        return searchCriteria;
    }

    //trim (2)서브메뉴
    private static Map<String,Object> inputSearchCriteriaMap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("검색할 조건을 입력하세요 (category or name or both or null) : ");
        String condition = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        //조건을 나눠봄 1, 2, 3
        if ("category".equals(condition)){
            System.out.println("검색할 카테고리 코드를 입력하세요 : ");
            int categoryValue = sc.nextInt();
            criteria.put("categoryValue", categoryValue);
        //========
        }else if ("name".equals(condition)){
            System.out.println("검색할 이름를 입력하세요 : ");
            String nameValue = sc.nextLine();
            criteria.put("nameValue", nameValue);
        //========
        }else  if ("both".equals(condition)){
            System.out.println("검색할 이름를 입력하세요 : "); //이름먼저 치고 코드 쳐야지
            String nameValue = sc.nextLine();
            System.out.println("검색할 카테고리 코드를 입력하세요 : ");
            int categoryValue = sc.nextInt();
            criteria.put("nameValue", nameValue);
            criteria.put("categoryValue", categoryValue);
        }
        return criteria;
    }
    //trim (3)서브메뉴
    private static Map<String, Object> inputChangeInfo() {

        Scanner sc = new Scanner(System.in);
        System.out.println("변경할 메뉴 코드를 입력하세요 : ");
        int code = sc.nextInt();
        sc.nextLine();
        System.out.println("변경할 메뉴 이름을 입력하세요 : ");
        String name = sc.nextLine();
        System.out.println("변경할 카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        sc.nextLine();
        System.out.println("판매 여부를 결정해주세요(Y/N)");
        String orderableStatus = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        criteria.put("code",code);
        criteria.put("name",name);
        criteria.put("categoryCode",categoryCode);
        criteria.put("orderableStatus",orderableStatus);

        return criteria;
    }

}