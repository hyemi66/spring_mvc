package com.naver.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.ReplyVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문 수행 sqlSession

	@Override
	public void addreply(ReplyVO vo) {
		this.sqlSession.insert("reply_in", vo);
	} // 댓글 저장

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.sqlSession.selectList("reply_list", bno);
		// mybatis에서 selectList()메소드는 한 개 이상의 레코드를 검색해서 컬렉션 List로 반환
	} // 댓글 목록

	@Override
	public void updateReply(ReplyVO vo) {
		this.sqlSession.update("reply_edit", vo);
	} // 댓글 수정

	@Override
	public void delReply(int rno) {
		this.sqlSession.delete("reply_del", rno);
	} // 댓글 삭제

	@Override
	public int getBno(int rno) {
		return this.sqlSession.selectOne("reply_bno", rno);
	} // 댓글 번호에 해당하는 게시판 번호 구하기
	
}
