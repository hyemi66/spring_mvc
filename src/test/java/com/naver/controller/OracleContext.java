package com.naver.controller;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

// 오라클 연결 테스트
public class OracleContext {
	private static final String DRIVER = "oracle.jdbc.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	private static final String USER = "c##java";
	private static final String PWD = "1234";
	
	@Test // JUnit 연습용 테스트 애노테이션
	public void testOracleCon() throws Exception {
		Class.forName(DRIVER); // 오라클 jdbc 드라이버 클래스 로드
		
		try(Connection con = DriverManager.getConnection(URL, USER, PWD)) {
			/*
				자바7이후부터 AutoCloseable 인터페이스를 구현 상속받은 자손은 try()내에서 con을 생성하면
				finally {}에서 명시적으로 close() 즉 닫지 않아도 자동으로 닫힌다
			*/
			
			System.out.println(con);
		} catch(Exception e) { e.printStackTrace(); }
	}
}
