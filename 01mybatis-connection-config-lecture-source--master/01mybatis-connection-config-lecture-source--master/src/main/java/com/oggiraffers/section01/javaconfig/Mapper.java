package com.oggiraffers.section01.javaconfig;

import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface Mapper { // interface인터페이스로 메퍼 만듬

    @Select("SELECT SYSDATE FROM DUAL")
    Date selectSysdate();

}
