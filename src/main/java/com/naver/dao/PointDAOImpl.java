package com.naver.dao;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PointDAOImpl implements PointDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void updatePoint(String sender, int point) {
		/*
			DAOImpl에서 mybatis 매퍼태그로 복수개의 파라미터값(인자값)을 전달할 때
			-> java.util패키지의 컬렉션 Map 사전적인 자료구조를 사용한다
		*/
		Map<String, Object> pm = new HashMap<>();
		pm.put("sender", sender);
		pm.put("point", point); // 키, 값 쌍으로 저장
		
		this.sqlSession.update("pointUp", pm);
		// mybatis에서 update()메소드로 레코드를 수정, pointUp은 mybatis point.xml에서 설정할 유일한 아이디명
	} // 메세지를 보낸 사람에게 포인트 점수 10점을 업데이트
	
}
