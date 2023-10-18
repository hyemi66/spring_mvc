package com.naver.service;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface GongjiService {

	void insertGongji(GongjiVO g);

	int getTotalCount();

	List<GongjiVO> getGongjiList(GongjiVO g);

	GongjiVO getGongjiCont(int gongji_no);

}
