package com.ohgiraffers.section02.javaconfig;

import com.ohgiraffers.section01.xmlconfig.MenuDAO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section02.javaconfig.Template.getSqlSession; //getSqlSession 챕터 2에서 받아온건지 확인 잘하기ㅜ


public class MenuService {

    private MenuMapper menuMapper;
    //1
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession();

        menuMapper = sqlSession.getMapper(MenuMapper.class);
        List<MenuDTO> menuList = menuMapper.selectAllMenu();

        sqlSession.close();

        return menuList;
    }
    //2
    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        MenuDTO menu = menuMapper.selectMenuByCode(code);

        sqlSession.close();

        return menu;
    }
    //3
    public boolean regisMenu(MenuDTO menu) {
        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.insertMenu(menu);

        if (result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result>0;
    }
    //4
    public boolean modifyMenu(MenuDTO menu) {

        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.updateMenu(menu);

        if (result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result>0;
    }
    //5
    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        int result = menuMapper.deleteMenu(code);

        if (result>0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close();

        return result>0;
    }
}
