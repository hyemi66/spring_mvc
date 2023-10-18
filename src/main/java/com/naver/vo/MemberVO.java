package com.naver.vo;

import java.util.Date;

public class MemberVO { // 데이터 저장빈 클래스
	private String userid, userpw, username, email;
	private Date regdate;
	
	public String getUserid() { return userid; }
	public void setUserid(String userid) { this.userid = userid; }
	
	public String getUserpw() { return userpw; }
	public void setUserpw(String userpw) { this.userpw = userpw; }
	
	public String getUsername() { return username; }
	public void setUsername(String username) { this.username = username; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	public Date getDate() { return regdate; }
	public void setDate(Date regdate) { this.regdate = regdate; }
	
	
	
}
