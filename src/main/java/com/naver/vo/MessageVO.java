package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter // setter() 메소드 자동 제공
@Getter // getter() 메소드 자동 제공
public class MessageVO { // tbl_message 테이블의 컬럼명과 일치하는 변수명을 가진 데이터 저장빈 클래스를 정의
	
	private int mid;
	private String targetid;
	private String sender;
	private String message;
	private String senddate;
	
}
