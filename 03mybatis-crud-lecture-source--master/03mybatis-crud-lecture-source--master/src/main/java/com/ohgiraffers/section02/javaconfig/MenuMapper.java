package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.annotations.*;

import java.util.List;

public interface MenuMapper {
    //1.
    @Results(id = "menuResultMap", value = {
            @Result(id = true, property = "code", column = "MENU_CODE"),
            @Result(property = "name", column = "MENU_NAME"),
            @Result(property = "price", column = "MENU_PRICE"),
            @Result(property="categoryCode", column="CATEGORY_CODE"),
            @Result(property="orderableStatus", column="ORDERABLE_STATUS")
    })
    @Select("       SELECT\n" +
            "              MENU_CODE\n" +
            "            , MENU_NAME\n" +
            "            , MENU_PRICE\n" +
            "            , CATEGORY_CODE\n" +
            "            , ORDERABLE_STATUS\n" +
            "         FROM TBL_MENU\n" +
            "        WHERE ORDERABLE_STATUS = 'Y'\n" +
            "        ORDER BY MENU_CODE")
    List<MenuDTO> selectAllMenu(); //어노테이션을 이용해서 매퍼 파일안에 있던걸 인터페이스에 작성해서 매핑해주기

    //2.
    @Select("SELECT\n" +
            "              MENU_CODE\n" +
            "            , MENU_NAME\n" +
            "            , MENU_PRICE\n" +
            "            , CATEGORY_CODE\n" +
            "            , ORDERABLE_STATUS\n" +
            "         FROM TBL_MENU\n" +
            "        WHERE MENU_CODE = #{code}")
    @ResultMap("menuResultMap")// 위의 작성된 @Results의 id를 이용해서 재사용함
    MenuDTO selectMenuByCode(int code);

    @Insert("INSERT\n" +
            "        INTO TBL_MENU\n" +
            "        (\n" +
            "          MENU_CODE\n" +
            "        , MENU_NAME\n" +
            "        , MENU_PRICE\n" +
            "        , CATEGORY_CODE\n" +
            "        , ORDERABLE_STATUS\n" +
            "        )\n" +
            "        VALUES\n" +
            "        (\n" +
            "          SEQ_MENU_CODE.NEXTVAL\n" +
            "        , #{name} \n" +
            "        , #{price} \n" +
            "        , #{categoryCode}\n" +
            "        ,'Y'\n" +
            "        )")
    int insertMenu(MenuDTO menu);

    //3
    @Update("UPDATE\n" +
            "                TBL_MENU\n" +
            "          SET MENU_NAME = #{name}\n" +
            "            , MENU_PRICE = #{price}\n" +
            "            , CATEGORY_CODE = #{categoryCode}\n" +
            "        WHERE MENU_CODE = #{code}")
    int updateMenu(MenuDTO menu);


    //4
    @Delete("DELETE\n" +
            "                FROM TBL_MENU\n" +
            "        WHERE MENU_CODE = #{code}")
    int deleteMenu(int code);
}
