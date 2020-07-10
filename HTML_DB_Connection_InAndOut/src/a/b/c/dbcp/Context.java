package a.b.c.dbcp;

import org.apache.commons.dbcp2.BasicDataSource;

//커넥션풀을 설정한다.
public class Context {
	// 인스턴스 변수를 생성한다.
	public BasicDataSource basicDataSource;

	// 기본생성자
	public Context() {
		// 인스턴스 생성
		basicDataSource = new BasicDataSource();

		// DB정보 입력
		basicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		basicDataSource.setUrl("jdbc:oracle:thin:@192.168.0.131:1521:xe");
		basicDataSource.setUsername("scott");
		basicDataSource.setPassword("tiger");

		// 커넥션풀 설정
		// 초기 인스턴스 갯수
		basicDataSource.setInitialSize(4);
		// 커넥션인스턴스 최대저장갯수
		basicDataSource.setMaxIdle(100);
		// 커넥션인스턴스 최소저장갯수
		basicDataSource.setMinIdle(5);
	}

}