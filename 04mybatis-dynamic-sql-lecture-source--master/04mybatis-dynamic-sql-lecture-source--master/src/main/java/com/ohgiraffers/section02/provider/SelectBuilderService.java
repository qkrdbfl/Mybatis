package com.ohgiraffers.section02.provider;

import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.common.Template.getSqlSession;

//SelectBuilder (1)서브 메뉴
public class SelectBuilderService {

    private SelectBuilderMapper mapper; //매퍼 연결~

    public void testSimpleStatement() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(SelectBuilderMapper.class);

        List<MenuDTO> menuList = mapper.selectAllMenu();

        if (menuList != null && menuList.size() > 0){
            menuList.forEach(System.out::println);
        } else {
            System.out.println("검색 결과가 존재하지 않습니다.");
        }
        sqlSession.close();

    }
}
