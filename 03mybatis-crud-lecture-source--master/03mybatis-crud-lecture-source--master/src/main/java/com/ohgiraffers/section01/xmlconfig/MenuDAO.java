package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MenuDAO {

    public List<MenuDTO> selectAllMenu(SqlSession sqlSession) {

        //첫번째 인자로 mapper XML 파일의 namespace.id 를 문자열로 전달한다
        return sqlSession.selectList("MenuMapper.selectAllMenu"); // selectList는 sqlSession안에 있는거고 문자를 전달받고 이렇게만 리턴해도 됨 여러개일떄 씀

    }

    public MenuDTO selectMenuByCode(SqlSession sqlSession, int code) {

        return sqlSession.selectOne("MenuMapper.selectMenuByCode", code); //selectMenuByCode이 매퍼 파일에 기제 되어야함 매퍼파일가서 작성하자.
    }

    public int insertMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.insert("MenuMapper.insertMenu", menu); //활용해야 할걸 두번쨰 인자에 적음
    }

    //메뉴 수정
    public int updateMenu(SqlSession sqlSession, MenuDTO menu) {

        return sqlSession.update("MenuMapper.updateMenu",menu);
    }


    public int deleteMenu(SqlSession sqlSession, int code) {

        return sqlSession.delete("MenuMapper.deleteMenu", code);
    }
}
