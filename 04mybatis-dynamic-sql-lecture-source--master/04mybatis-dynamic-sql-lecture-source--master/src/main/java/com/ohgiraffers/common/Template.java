package com.ohgiraffers.common;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class Template {

    private static SqlSessionFactory sqlSessionFactory; // static키워드를 붙이면 클래스 변수라 얘를 저장할 공간이 딱하나 프로그램 실행시 생김

    public static SqlSession getSqlSession() { //getSqlSession 메소드가 호출될때 확인 해줘야함

        if (sqlSessionFactory == null){ //null이면 // 최초로 호출될땐 널임 {}안에 sqlSessionFactory를 생성하는 코드를 쓴다
            String resource = "config/mybatis-config.xml";

            try {
                InputStream inputStream = Resources.getResourceAsStream(resource);
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);//필요한 순간에만 만들어서 메소드를 호출하지 않으면 사라지게
            }catch (IOException e){
                throw new RuntimeException(e);
            }
        }

        //sqlSession은 스레드에 안전하지 않고 공유 되지 않아야 하므로 요청 시마다 생성해야한다
        SqlSession sqlSession = sqlSessionFactory.openSession(false);

        return sqlSession;
    }


}
