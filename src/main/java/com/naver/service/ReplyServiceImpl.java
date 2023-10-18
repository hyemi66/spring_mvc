package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.naver.dao.BoardDAO;
import com.naver.dao.ReplyDAO;
import com.naver.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService {
	
	@Autowired
	private ReplyDAO replyDao;
	
	@Autowired
	private BoardDAO boardDao;
	
	// 스프링의 AOP를 통한 트랜잭션 적용대상 -> 댓글이 추가되면 댓글 카운터 1증가
	@Transactional // 트랜잭션 적용함으로써 데이터 일관성 유지(데이터 불일치 현상을 제거)
	@Override
	public void addReply(ReplyVO vo) {
		this.replyDao.addreply(vo);
		this.boardDao.updateReplyCnt(vo.getBno(), 1);
		// 새로운 댓글이 추가되면 replycnt컬럼에 댓글 개수 1 증가 -> 게시판 번호를 기준으로 replycnt를 수정
	}

	@Override
	public List<ReplyVO> listReply(int bno) {
		return this.replyDao.listReply(bno);
	}

	@Override
	public void updateReply(ReplyVO vo) {
		this.replyDao.updateReply(vo);
	}
	
	// 스프링의 AOP를 통한 트랜잭션 적용대상 -> 댓글이 삭제되면 댓글 카운터 1감소
	@Transactional
	@Override
	public void delReply(int rno) {
		int bno = this.replyDao.getBno(rno); // 댓글 삭제되기 전에 댓글 번호를 기준으로 게시판 번호값을 구함
		this.replyDao.delReply(rno);
		this.boardDao.updateReplyCnt(bno, -1); // 댓글 하나 삭제되면 댓글 개수 1 감소
	}
	
}
