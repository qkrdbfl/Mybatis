package com.oggiraffers.section02.xmlconfig;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

    //XML에서 SqlSessionFactory 빌드하기//
    // 1)java code. 2)xml 설정파일. 두가지 방법중에 2번째 방법으로 작성함
public class Application {

    public static void main(String[] args) {

        String resource = "mybatis-config.xml"; //읽어오고자 하는 파일 작성

        //InputStream inputStream = null; // Resourcesd에 resource연결 // InputStream을 전달 밑의 inputStream를 읽어오는.
        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);// SqlSessionFactoryBuilder를 build함

            SqlSession session = sqlSessionFactory.openSession(false);

            Date date = session.selectOne("mapper.selectSysdate"); //selectOne : 조회 할건데 오는건 한 행이다 그리고 반환 받는다
            System.out.println(date);

            //SqlSession 안에서 실행하는데 안전하게 닫는다는 내부적인 의미로 파이널은 안씀
            session.close(); // 반납

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
