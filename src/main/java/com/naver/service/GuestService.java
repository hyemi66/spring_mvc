package com.naver.service;

import java.util.List;

import com.naver.vo.GuestVO;

public interface GuestService {

	void insertGuest(GuestVO g);

	int getTotalCount();

	List<GuestVO> getGuestList(GuestVO g);
	
}
