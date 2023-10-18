package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.MemberVO;

@Repository // @Repository 애노테이션은 DAO를 스프링에서 인식하게 한다
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired // 자동의존성 주입(DI)
	private SqlSession sqlSession; // mybatis 쿼리문 수행 객체 sqlSession생성

	@Override
	public void insertMember(MemberVO mem) { // 회원저장
		this.sqlSession.insert("mem_in", mem);
		/*
			mybatis에서 insert()메소드는 레코드를 저장시켜준다
			member.xml에서 유일한 아이디명(중복X) mem_in을 설정해줘야 한다
			mem_in을 호출해서 쿼리문을 수행한다
		*/
	} 
	
}
