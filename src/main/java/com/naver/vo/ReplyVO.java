package com.naver.vo;

import lombok.Data;

@Data // setter(), getter(), toString() 등 메소드 자동 제공
public class ReplyVO { // 댓글 데이터 저장빈 클래스 -> tbl_reply 테이블의 컬럼명과 빈클래스 변수명을 일치시킨다
	private int rno; // 댓글번호
	private int bno; // 게시판번호
	private String replyer; // 댓글 작성자
	private String replytext; // 댓글 내용
	private String regdate; // 댓글 등록 날짜
	private String updatedate; // 댓글 수정 날짜
}
