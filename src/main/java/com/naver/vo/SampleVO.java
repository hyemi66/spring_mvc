package com.naver.vo;

public class SampleVO {
	private int mno;// JSON . 변수명이 의 키이름이 된다
	private String firstName;//성
	private String lastName;//이름
	
	public int getMno() { return mno; }
	public void setMno(int mno) { this.mno = mno; }
	
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
}
