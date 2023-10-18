package com.naver.controller;

import java.sql.Connection;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class DataSourceTest {
	
	@Inject // 자동의존성 주입 => DI
	private DataSource ds; // 참조변수 ds에 객체주소를 대입해서 실제 사용할 수 있게 한다
	/*
		스프링에서 의존성인 di 주입하는 방법
		1. 생성자를 통한 의존성 주입
		2. setter()메소드를 통한 의존성 주입
	*/
	
	@Test
	public void testCon() throws Exception {
		try(Connection con = ds.getConnection()) { // dbcp 커넥션 풀 관리 ds로 con생성
			System.out.println(con);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
