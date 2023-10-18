package com.naver.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.vo.BoardVO;

@Repository
public class BoardDATOImpl implements BoardDAO {
	
	@Autowired
	private SqlSession sqlSession; // mybatis 쿼리문을 수행할 sqlSession생성

	@Override
	public void insertBoard(BoardVO b) {
		this.sqlSession.insert("board_in", b);
		// mybatis에서 insert()메소드는 레코드를 저장시키는 기능을 한다.
		// board_in은 board.xml에서 설정할 유일한 아이디명이다
	} // 스프링 MVC 게시판 저장

	@Override
	public int getTotalCount() {
		return this.sqlSession.selectOne("board_count");
		// mybatis에서 selectOne()메소드는 단 한개의 레코드값만 반환
		// board_count는 board.xml 매퍼태그에서 설정할 유일한 아이디명
	} // 게시물 총 레코드 개수

	@Override
	public List<BoardVO> getBoardList(BoardVO b) {
		return this.sqlSession.selectList("board_list", b);
		// mybatis에서 selectList()메소드는 하나 이상의 레코드를 검색해서 컬렉션 List로 반환
		// board_list는 board.xml 매퍼태그에서 설정할 유일한 아이디명
	} // 게시물 목록

	@Override
	public void updateHit(int bno) {
		this.sqlSession.update("board_hit", bno);
		// mybatis에서 update()메소드는 레코드를 수정
		// board_hit는 board.xml 매퍼태그에서 설정할 유일한 아이디명
	} // 조회수 증가

	@Override
	public BoardVO getBoardCount(int bno) {
		return this.sqlSession.selectOne("board_cont", bno);
	} // 내용보기

	@Override
	public void editBoard(BoardVO eb) {
		this.sqlSession.update("board_edit", eb);
	} // 게시물 수정

	@Override
	public void delBoard(int bno) {
		this.sqlSession.delete("board_del", bno);
		// mybatis에서 delete()메소드는 레코드를 삭제한다
	} // 게시물 삭제

	@Override
	public void updateReplyCnt(int bno, int count) {
		Map<String, Object> pm = new HashMap<>();
		// 키, 값 쌍으로 저장하는 컬렉션 사전적인 자료구조
		
		pm.put("bno", bno);
		pm.put("count", count);
		
		this.sqlSession.update("updateReplyCnt", pm);
	} // 댓글 개수 카운터 -> 인자값 복수개가 board.xml로 전달할 때는 컬렉션인 java.util 패키지의 Map을 사용한다
	
	
	
}
