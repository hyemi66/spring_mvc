package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.GongjiDAO;
import com.naver.vo.GongjiVO;

@Service
public class GongjiServiceImpl implements GongjiService {
	
	@Autowired
	private GongjiDAO gongjiDao;

	@Override
	public void insertGongji(GongjiVO g) {
		this.gongjiDao.insertGongji(g);
	}

	@Override
	public int getTotalCount() {
		return this.gongjiDao.getTotalCount();
	}

	@Override
	public List<GongjiVO> getGongjiList(GongjiVO g) {
		return this.gongjiDao.getGongjiList(g);
	}

	@Override
	public GongjiVO getGongjiCont(int gongji_no) {
		return gongjiDao.getGongjiCont(gongji_no);
	}
	
}
