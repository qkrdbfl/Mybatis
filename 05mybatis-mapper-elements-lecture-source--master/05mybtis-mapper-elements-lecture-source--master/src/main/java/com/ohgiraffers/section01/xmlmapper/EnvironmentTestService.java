package com.ohgiraffers.section01.xmlmapper;
import com.ohgiraffers.common.CategoryAndMenuDTO;
import com.ohgiraffers.common.MenuAndCategoryDTO;
import com.ohgiraffers.common.MenuDTO;
import org.apache.ibatis.session.SqlSession;
import java.util.List;
import static com.ohgiraffers.common.Template.getSqlSession;

public class EnvironmentTestService {

    //Mapper element 테스트 메뉴(1)
    private ElementTestMapper mapper;
    public void selectCacheTest() {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        //최초 요청시에는 시간이 소요되지만 그 이후에는 캐싱된 데이터를 불러오기 때문에 속도가 빠름
        for (int i=0; i<10; i++){

            long startTime = System.currentTimeMillis();

            List<String> nameList = mapper.selectCacheTest();
            System.out.println(nameList);

            long endTime = System.currentTimeMillis();

            long interval = endTime - startTime;
            System.out.println(" 수행시간 : "+ interval+ "(ms)");
        }
        sqlSession.close();
    }

    //resultMap
    public void selectResultMapTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapTest();
        menuList.forEach(System.out::println);

        sqlSession.close();

    }

    public void selectResultMapConstructorTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectResultMapConstructorTest();
        menuList.forEach(System.out::println);

        sqlSession.close();

    }

    public void selectResultMapAssociationTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuAndCategoryDTO> menuList = mapper.selectResultMapAssociationTest();
        menuList.forEach(System.out::println);

        sqlSession.close();

    }

    public void selectResultMapCollectionTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<CategoryAndMenuDTO> categoryList = mapper.selectResultMapCollectionTest();
        categoryList.forEach(System.out::println);

        sqlSession.close();

    }

    public void selectSqlTest() {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        List<MenuDTO> menuList = mapper.selectSqlTest();
        menuList.forEach(System.out::println);

        sqlSession.close();

    }

    public void insertCategoryAndMenuTest(MenuAndCategoryDTO menuAndCategory) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(ElementTestMapper.class);

        int result1 = mapper.insertNewCategory(menuAndCategory);
        int result2 = mapper.insertNewMenu(menuAndCategory);

        if (result1>0 && result2>0){
            sqlSession.commit();
            System.out.println(" 신규 메뉴 등록 완료");
        }else {
            sqlSession.rollback();
            System.out.println(" 신규 메뉴 등록 실패");
        }

        sqlSession.close();
    }
}
