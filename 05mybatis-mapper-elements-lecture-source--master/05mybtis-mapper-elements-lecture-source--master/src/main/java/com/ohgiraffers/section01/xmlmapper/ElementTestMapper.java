package com.ohgiraffers.section01.xmlmapper;

import com.ohgiraffers.common.CategoryAndMenuDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;

import java.util.List;

public interface ElementTestMapper {

    //Mapper element 테스트 메뉴 (1)
    List<String> selectCacheTest();
    //resultMap
    List<MenuDTO> selectResultMapTest();

    List<MenuDTO> selectResultMapConstructorTest();

    List<MenuAndCategoryDTO> selectResultMapAssociationTest();

    List<CategoryAndMenuDTO> selectResultMapCollectionTest();

    List<MenuDTO> selectSqlTest();

    int insertNewCategory(MenuAndCategoryDTO menuAndCategory);

    int insertNewMenu(MenuAndCategoryDTO menuAndCategory);
}
