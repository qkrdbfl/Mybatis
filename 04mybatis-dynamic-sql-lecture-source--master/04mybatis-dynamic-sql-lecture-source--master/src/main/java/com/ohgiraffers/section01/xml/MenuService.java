package com.ohgiraffers.section01.xml;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;

public class MenuService {

    private DynamicSqlMapper mapper;

    //if (1)서브메뉴
    public void selectMenuByPrice(int price) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* 기본 자료형으로 조건문의 값을 비교하기 어려우므로 hashmap을 이용해서 파라미터를 전달한다.
         * (또는 DTO를 이용해서 전달한다.) */
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price);

        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        if (menuList != null && menuList.size() > 0) { //
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close(); //닫아
    }

    //if (2)서브메뉴
    public void searchMenu(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);// searchMenu를 인자로 넘겨서 받아옴

        if (menuList != null && menuList.size() > 0) { //
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close(); //닫아
    }

    //choose 서브메뉴
    public void searchMenuBySupCategory(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySupCategory(searchCriteria);

        if (menuList != null && menuList.size() > 0) { //
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close(); //닫아

    }
    // foreach 서브메뉴
    public void searchMenuByRandomMenuCode(List<Integer> randomMenuCodeList) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        Map<String, List<Integer>> criteria = new HashMap<>();
        criteria.put("randomCodeList", randomMenuCodeList);
        List<MenuDTO> menuList = mapper.searchMenuByRandomMenuCode(criteria); //랜덤코드 리스트 라는 키값을 이용해서 리스트를 뺴옴

        if (menuList != null && menuList.size() > 0) { //
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close(); //닫아

    }

    //trim (1)서브메뉴
    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);

        if (menuList != null && menuList.size() > 0) { //
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }

        sqlSession.close(); //닫아
    }

    //trim (2)서브메뉴
    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);

        if (menuList != null && menuList.size() > 0){
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();
    }

    //trim (3)서브메뉴
    public void modifyMenu(Map<String, Object> criteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);

        if(result > 0) {
            sqlSession.commit();
            System.out.println("메뉴 정보 변경에 성공하였습니다.");
        } else {
            sqlSession.rollback();
            System.out.println("메뉴 정보 변경에 실패하였습니다.");
        }

        sqlSession.close();

    }
}