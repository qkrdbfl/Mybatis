package com.ohgiraffers.section01.xmlconfig;
import org.apache.ibatis.session.SqlSession;

import java.sql.SQLPermission;
import java.util.List;
import static com.ohgiraffers.section01.xmlconfig.Template.getSqlSession;

public class MenuService {

    private  final MenuDAO menuDAO; //DAO 쓸거라 일단 생성하는 구문 필드에 작성함

    public MenuService(){
        menuDAO = new MenuDAO();
    }
    public List<MenuDTO> selectAllMenu() {

        SqlSession sqlSession = getSqlSession(); //파일을 읽어옴
            //DAO 요청해서 받아오기
        List<MenuDTO> menuList = menuDAO.selectAllMenu(sqlSession);//메뉴의 목록을전달받음

        sqlSession.close();

        return menuList;
    }

    public MenuDTO selectMenuByCode(int code) {

        SqlSession sqlSession = getSqlSession(); //1. SqlSession 만든다 SqlSession에서 밑의 구문을 쓸거라서

        MenuDTO menu = menuDAO.selectMenuByCode(sqlSession,code); //2. DAO에 selectMenuByCode 메소드 생성 기능정의

        sqlSession.close();

        return menu;
    }

    public boolean regisMenu(MenuDTO menu) {

        SqlSession sqlSession= getSqlSession();

        int result = menuDAO.insertMenu(sqlSession,menu);

        //결과에 따라 커밋또는 롤백
        if (result >0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close(); // 닫아주기

        return result >0; //블리언 타입으로 리턴하기로 했으니 .
    }
        //메뉴수정
    public boolean modifyMenu(MenuDTO menu) {

        SqlSession sqlSession= getSqlSession();// 이거 꼭 요청하고

        int result = menuDAO.updateMenu(sqlSession,menu); // insertMenu요청하고

        //결과에 따라 커밋또는 롤백
        if (result >0){
            sqlSession.commit();
        }else {
            sqlSession.rollback();
        }
        sqlSession.close(); // 닫아주기

        return result >0;
    }

    public boolean deleteMenu(int code) {

        SqlSession sqlSession = getSqlSession();

        int result = menuDAO.deleteMenu(sqlSession, code);

        if(result > 0) {
            sqlSession.commit();
        } else {
            sqlSession.rollback();
        }

        sqlSession.close();

        return result > 0;
    }
}
