package com.oggiraffers.section01.javaconfig;

import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.apache.ibatis.session.Configuration; //자동으로 안만들어주냐 왜 --^

import java.util.Date;

public class Application {

    private static String DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
    private static String USER = "C##OHGIRAFFERS";
    private static String PASSWORD = "OHGIRAFFERS";

    // 1)java code. 2)xml 설정파일. 두가지 방법중에 1번째 방법으로 작성함
    //XML 을 사용하지 않고 SqlSessionFactory 빌드하기//
    // XML 보다 자바를 사용해서 직접 설정하길 원한다면 XML 파일과 같은 모든 설정을 제공하는 Configuration 클래스를 사용
    public static void main(String[] args) {

        //DB 접속에 관한 환경 설정
        //JdbcTransactionFactory : 수동 커밋
        // ManagedTransactionFactory : 자동 커밋
        //PooledDataSource : ConnectionPool 사용
        //UnPooledDataSource : ConnectionPool 사용

        //접속을 위한 환경 생성 //()안에 3개의 인수값 필요
        Environment environment =
                new Environment("dav"         //환경 정보 이름
                        , new JdbcTransactionFactory()  // 트랜직션 매니저 종류 결정 (JDBC or MANAGED)
                        , new PooledDataSource(DRIVER, URL, USER, PASSWORD)); //4가지 전달 연결로 만든다     // Connection Pool 사용 유무 (Pooled or UnPooled) // 커넥션 풀을 안쓰면 응답기능이 좀 느림 그래서 거의 씀

        //생성한 환경 정보로 MyBatis 설정 객체 생성
        Configuration configuration = new Configuration(environment);//////////////////

        // 설정 객체에 매퍼 등록
        configuration.addMapper(Mapper.class); //매퍼라는 인터페이스에 등록

        //SqlSessionFactory : SqlSession 객체를 생성하기 위한 팩토리 역할의 인터페이스
        //SqlSessionFactoryBuilder : SqlSessionFactory 인터페이스 타입의 하위 구현 객체를 생성하기 위한 빌드 역할
        //build() : 설정에 대한 정보를 담고 있는  Configuration 타입의 객체 혹은 외부 설정 파일과 연결 된 Stream을
        //매개변수로 전달하면 SqlSessionFactory 인터페이스 타입의 객체를 반환하는 메소드임.
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration); // sqlSessionFactory객체 생성해주는 공작을 해주는게 SqlSession"Factory"고 꼭 빌더(build)를 해줘야함 // configuration에 매퍼를 전달해라는 의미

        // openSession() :  SqlSession 인터페이스 타입의 객체를 반환하는 메소드로 boolean 타입을 인자로 전달
        //- fales : DML 수행 후 auto commit 옵션을  fales로 지정(권장)
        //- true : DML 수행 후 auto commit 옵션을  true 지정
        SqlSession sqlSession = sqlSessionFactory.openSession(false); // SqlSession를 이용해서 여러 기능들 사용가능

        // getMapper() : Configuration 에 등록 된 매퍼를 동일 타입에 대해 반환하는 메소드
        Mapper mapper = sqlSession.getMapper(Mapper.class);

        // Mapper 인터페이스에 작성된 메소드를 호출하여 쿼리 실행
        Date date = mapper.selectSysdate();

        System.out.println(date); //출력해서 확인

        // close() : SqlSession 객체 반납
        sqlSession.close();
    }
}
