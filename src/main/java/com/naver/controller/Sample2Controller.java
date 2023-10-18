package com.naver.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

public class Sample2Controller {
	
	@RequestMapping("/doC") // doc 매핑주소 등록
	public String doc(@ModelAttribute("msg2") String msg) {
		/*
			@ModelAttribute("msg2")는 msg2 피라미터 이름에 인자값을 담아서 전달
			주소창에 노출되는 방식인 doC?msg2=인자값 형태도 전달된다
		*/
		return "result"; // WEB-INF/views/result.jsp
	}
	
}
