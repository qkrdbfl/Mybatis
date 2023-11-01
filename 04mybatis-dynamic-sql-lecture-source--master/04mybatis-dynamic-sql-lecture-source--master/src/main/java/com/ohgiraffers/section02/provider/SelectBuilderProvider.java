package com.ohgiraffers.section02.provider;

import org.apache.ibatis.jdbc.SQL;

public class SelectBuilderProvider {
    //SelectBuilder (1)서브 메뉴
    //SQl문을 문자열 형태로 반환하도록 return 타입을 지정한다
    public String selectAllMenu(){

        return new SQL()
                .SELECT("MENU_CODE")
                .SELECT("MENU_NAME")
                .SELECT("MENU_PRICE")
                .SELECT("CATEGORY_CODE")
                .SELECT("ORDERABLE_STATUS")
                .FROM("TBL_MENU")
                .WHERE("ORDERABLE_STATUS = 'Y'")
                .toString(); //이게 스트링 값으로 반환
    }
}
