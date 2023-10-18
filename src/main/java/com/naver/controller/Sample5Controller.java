package com.naver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.naver.vo.ProductVO;

@Controller
public class Sample5Controller {
	
	// 키, 값 쌍의 JSON데이터 만드는 실습
	@RequestMapping(value="/product", produces="application/json")
	public @ResponseBody ProductVO doJSON() {
		/*
			@ResponseBody 애노테이션을 사용하면 JSP파일을 만들지 않고도 
			브라우저에 키, 값 쌍의 json데이터를 쉽게 만들 수 있다
			리턴타입이 ProductVO빈클래스 타입이면 JSON데이터의 키이름은 해당 클래스의 변수명이 된다
		*/
		ProductVO p = new ProductVO("에어컨",2500000);
		return p;
	}
	
}
