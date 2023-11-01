package com.ohgiraffers.section01.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    // SqlSessionFactory는 어플리케이션이 실행 되는 동안 존재해야 하며,
    //여러차례 다시 빌드하지 않는 것이 가장 좋은 형태이다
    //어플리케이션 스코프를 관리 하기위해 가장 간단한 방법은 "싱글톤 패턴"을 이용하는 것이다
    private static SqlSessionFactory sqlSessionFactory; // static키워드를 붙이면 클래스 변수라 얘를 저장할 공간이 딱하나 프로그램 실행시 생김

    public static SqlSession getSqlSession() { //getSqlSession 메소드가 호출될때 확인 해줘야함

        if (sqlSessionFactory == null){ //null이면 // 최초로 호출될땐 널임 {}안에 sqlSessionFactory를 생성하는 코드를 쓴다
            String resource = "mybatis-config.xml";

            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                /* SqlSessionFactoryBuilder를 SqlSession을 생성한 후에도 유지할 필요는 없다.
                 * 따라서 메소드 스코프로 만든다. */
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//필요한 순간에만 만들어서 메소드를 호출하지 않으면 사라지게
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }
        //sqlSession은 스레드에 안전하지 않고 공유 되지 않아야 하므로 요총 시마다 생성해야한다
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        System.out.println("sqlSessionFactory의 hashCode() : " + sqlSessionFactory.hashCode());// 확인을 위한 코드
        System.out.println("sqlSession의 hashCode() : "+ sqlSession.hashCode());

        return sqlSession;
    }


}
