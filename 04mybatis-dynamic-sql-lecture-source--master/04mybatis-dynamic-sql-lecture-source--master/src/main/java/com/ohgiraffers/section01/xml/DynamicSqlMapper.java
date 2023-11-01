package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;

import java.util.List;
import java.util.Map;

public interface DynamicSqlMapper {
    //if(1)
    List<MenuDTO> selectMenuByPrice(Map<String, Integer> map);

    //if(2)
    List<MenuDTO> searchMenu(SearchCriteria searchCriteria);

    //choose
    List<MenuDTO> searchMenuBySupCategory(SearchCriteria searchCriteria);

    //foreach
    List<MenuDTO> searchMenuByRandomMenuCode(Map<String, List<Integer>> criteria);

    //trim(1)
    List<MenuDTO> searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria);

    //trim(2)
    List<MenuDTO> searchMenuByNameOrCategory(Map<String, Object> criteria);

    //trim(3)
    int modifyMenu(Map<String, Object> criteria);
}
