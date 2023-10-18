package com.naver.vo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserVO { // tbl_user테이블의 컬럼명과 일치하는 데이터 저장빈 클래스 정의
	
	private String uid2;
	private String upw;
	private String uname;
	private int upoint;
	
}
