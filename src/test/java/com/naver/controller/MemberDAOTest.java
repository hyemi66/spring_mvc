package com.naver.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.naver.dao.MemberDAO;
import com.naver.vo.MemberVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/*.xml"})
public class MemberDAOTest {
	
	@Autowired
	private MemberDAO memDao;
	
	@Test
	public void testMemberInsert() throws Exception {
		MemberVO mem = new MemberVO();
		
		mem.setUserid("ddddd10");
		mem.setUserpw("99999");
		
		mem.setUsername("홍길동");
		mem.setEmail("hong@naver.com");
		
		this.memDao.insertMember(mem); // 회원저장
		
	}
	
}
