package com.naver.dao;

import java.util.List;

import com.naver.vo.GuestVO;

public interface GuestDAO {

	void insertGuest(GuestVO g);

	int getTotalCount();

	List<GuestVO> getGuestList(GuestVO g);


}
