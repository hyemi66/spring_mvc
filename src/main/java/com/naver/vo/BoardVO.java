package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Getter // getter()메소드 자동제공
@Setter // setter()메소드 자동제공
public class BoardVO { // tbl_board테이블의 컬럼명과 데이터 저장 빈 클래스 변수명이 일치하는 클래스 정의
	// lombok.jar 라이브러리를 추가해서 setter(), getter() 메소드를 생략한다
	
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;
	private String regdate;
	private int replycnt;
	
	// 페이징 -> 쪽 나누기
	private int startrow; // 시작행 번호
	private int endrow; // 끝행 번호
	
	
}
