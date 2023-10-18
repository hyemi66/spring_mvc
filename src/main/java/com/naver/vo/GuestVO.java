package com.naver.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GuestVO {
	private int gno;
	private String gname;
	private String gtitle; 
	private String gcont;
	private int ghit;
	private Date gdate;
	
	private int startrow;
	private int endrow;
}
