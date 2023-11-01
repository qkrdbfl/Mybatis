package com.ohgiraffers.section02.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import java.io.IOException;
import java.io.InputStream;

public class Template {
    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String USER = "C##OHGIRAFFERS";
    private static String PASSWORD = "OHGIRAFFERS";

    // SqlSessionFactory는 어플리케이션이 실행 되는 동안 존재해야 하며,
    //여러차례 다시 빌드하지 않는 것이 가장 좋은 형태이다
    //어플리케이션 스코프(범위)를 관리 하기위해 가장 간단한 방법은 "싱글톤 패턴"을 이용하는 것이다
    private static SqlSessionFactory sqlSessionFactory; // static키워드를 붙이면 클래스 변수라 얘를 저장할 공간이 딱하나 프로그램 실행시 생김

    public static SqlSession getSqlSession() { //getSqlSession 메소드가 호출될때 확인 해줘야함

        if (sqlSessionFactory == null) {
            Environment environment = new Environment("dav"
                    , new JdbcTransactionFactory()
                    , new PooledDataSource(DRIVER, URL, USER, PASSWORD));

            Configuration configuration = new Configuration(environment); //설정이라는 객체 만듬 위의 코드가 environment안에 넣고

            configuration.addMapper(MenuMapper.class);// 인터페이스 명칭 전달 매퍼로 추가 한다는 뜻

            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        }
            //sqlSession은 스레드에 안전하지 않고 공유 되지 않아야 하므로 요청 시마다 생성해야한다
            SqlSession sqlSession = sqlSessionFactory.openSession(false);

            return sqlSession;
    }
}



