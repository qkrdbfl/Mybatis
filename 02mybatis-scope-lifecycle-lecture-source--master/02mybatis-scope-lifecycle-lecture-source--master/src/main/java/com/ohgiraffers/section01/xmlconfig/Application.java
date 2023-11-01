package com.ohgiraffers.section01.xmlconfig;

import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class Application {

    public static void main(String[] args) {

        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession());
        System.out.println(getSqlSession()); //5번 반환해줘 // 5번 다 다른값을 내놈

    }
}
