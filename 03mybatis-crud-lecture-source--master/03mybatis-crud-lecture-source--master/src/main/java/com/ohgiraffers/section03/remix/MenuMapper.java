package com.ohgiraffers.section03.remix;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MenuMapper {
    //1.
    List<MenuDTO> selectAllMenu();

    //2.
    MenuDTO selectMenuByCode(int code);

    int insertMenu(MenuDTO menu);

    //3
    int updateMenu(MenuDTO menu);

    //4
    int deleteMenu(int code);
}
