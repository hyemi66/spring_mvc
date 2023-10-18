package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.GongjiVO;

@Repository
public class GongjiDAOImpl implements GongjiDAO {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertGongji(GongjiVO g) {
		this.sqlSession.insert("gongji_in", g);
	}

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("gongji_total");
	}

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.sqlSession.selectList("gongji_list",g);
	}

	@Override
	public GongjiVO getGongjiCont(int gongji_no) {
		return this.sqlSession.selectOne("gongji_view", gongji_no);
	}
	
}
