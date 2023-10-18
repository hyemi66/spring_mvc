package com.naver.dao;

import java.util.List;

import com.naver.vo.GongjiVO;

public interface GongjiDAO {

	void insertGongji(GongjiVO g);

	int getTotalCount();

	List<GongjiVO> getGongjiList(GongjiVO g);

	GongjiVO getGongjiCont(int gongji_no);

}
