package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

public interface SelectBuilderMapper {
//구문빌더
//SelectBuilder (1)서브 메뉴
    @Results(id = "menuResultMap", value = {
            @Result(id = true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property="categoryCode", column="CATEGORY_CODE"),
            @Result(property="orderableStatus", column="ORDERABLE_STATUS")
    })
    @SelectProvider(type = SelectBuilderProvider.class, method = "selectAllMenu")
    List<MenuDTO> selectAllMenu();
}
