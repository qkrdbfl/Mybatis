package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        EnvironmentTestService elementTestService = new EnvironmentTestService();

        do {
            System.out.println("========== Mapper element 테스트 메뉴 ==========");
            System.out.println("1. <cache> 테스트");
            System.out.println("2. <resultMap> 서브 메뉴");
            System.out.println("3. <sql> 테스트");
            System.out.println("4. <insert> 서브 메뉴");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : elementTestService.selectCacheTest();break;
                case 2 : resultMapSubMenu();break;
                case 3 : elementTestService.selectSqlTest(); break;
                case 4 : elementTestService.insertCategoryAndMenuTest(inputMenuAndCategory()); break;
            }
        } while (true);
    }


    //resultMap
    private static void resultMapSubMenu() {
        Scanner sc = new Scanner(System.in);
        EnvironmentTestService environmentTestService = new EnvironmentTestService();

        do {
            System.out.println("========== <resultMap> 서브 메뉴 ==========");
            System.out.println("1. <resultMap> 테스트");
            System.out.println("2. <constructor> 테스트");
            System.out.println("3. <association> 테스트");
            System.out.println("4. <collection> 테스트");
            System.out.print("메뉴 번호를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 : environmentTestService.selectResultMapTest(); break;
                case 2 : environmentTestService.selectResultMapConstructorTest();break;
                case 3 : environmentTestService.selectResultMapAssociationTest();break;
                case 4 : environmentTestService.selectResultMapCollectionTest();break;
            }
        }while (true);
    }

    private static MenuAndCategoryDTO inputMenuAndCategory() {
        Scanner sc = new Scanner(System.in);
        System.out.println("신규카테고리명을 입력 : ");
        String categoryName = sc.nextLine();
        System.out.println("메뉴 이름을 입력 : ");
        String menuName = sc.nextLine();
        System.out.println("메뉴 가격을 입력 : ");
        int price = sc.nextInt();
        sc.nextLine();
        System.out.println("바로 판매 등록을 할까요?(Y/N) : ");
        String orderableStatus = sc.nextLine();

        MenuAndCategoryDTO menuAndCategory = new MenuAndCategoryDTO();
        CategoryDTO category = new CategoryDTO();

        category.setName(categoryName);

        menuAndCategory.setName(menuName);
        menuAndCategory.setPrice(price);
        menuAndCategory.setCategory(category);
        menuAndCategory.setOrderableStatus(orderableStatus);

        return menuAndCategory;
    }

}
