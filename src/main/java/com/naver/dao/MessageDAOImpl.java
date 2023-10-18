package com.naver.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.MessageVO;

@Repository
public class MessageDAOImpl implements MessageDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertM(MessageVO vo) {
		this.sqlSession.insert("message_in", vo); // message_in은 message.xml 매퍼태그에서 설정할 유일한 아이디명
	} // 메세지 추가
	
}
