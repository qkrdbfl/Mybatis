package com.ohgiraffers.section02.provider;

import org.apache.ibatis.jdbc.SelectBuilder;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("========= 구문 빌더 API를 이용한 동적 SQL ==========");
            System.out.println("1. SelectBuilder 테스트하기");
            System.out.println("2. SqlBuilder 테스트 하기");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : selectBuilderSubMenu(); break;
                case 9 :
                    System.out.println("프로그램을 종료합니다"); return;
            }
        }while (true);

    }

    //1.SelectBuilder
    private static void selectBuilderSubMenu() {
        Scanner sc = new Scanner(System.in);
        SelectBuilderService selectBuilderService = new SelectBuilderService();
        do {
            System.out.println("========= SelectBuilder 서브 메뉴 ==========");
            System.out.println("1. SelectBuilder 기본 구문 사용하기");
            System.out.println("2. SelectBuilder를 이용한 동적 SQL 사용하기 ");
            System.out.println("9. 이전 메뉴로");
            System.out.println("메뉴를 입력하세요 : ");
            int no = sc.nextInt();

            switch (no){
                case 1 : selectBuilderService.testSimpleStatement(); break;
                case 9 : return;
            }
        }while (true);

    }
}
