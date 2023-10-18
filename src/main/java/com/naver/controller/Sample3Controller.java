package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.naver.vo.ProductVO;

@Controller
public class Sample3Controller {
	
	@RequestMapping("/price_name") // price_name이라는 매핑주소 등록
	public String price_name(Model pm) {
		ProductVO p = new ProductVO("노트북", 1700000);
		
		pm.addAttribute("p",p); // p문자열 속성 키이름에 p객체를 저장
		return "shop/notebook"; // 뷰페이지 경로 즉 뷰리졸브 설정 => /WEB-INF/views/shop/notebook.jsp
	}
	
}
