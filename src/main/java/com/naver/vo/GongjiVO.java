package com.naver.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GongjiVO {
	private int gongji_no;
	private String gongji_name;
	private String gongji_title;
	private String gongji_cont;
	private int gongji_hit;
	private Date gongji_date;
}
