package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.GuestVO;

@Repository
public class GuestDAOImpl implements GuestDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertGuest(GuestVO g) {
		this.sqlSession.insert("guest_in", g);
	}

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("guest_count");
	}

	@Override
	public List<GuestVO> getGuestList(GuestVO g) {
		return this.sqlSession.selectList("guest_list", g);
	}
	
}
