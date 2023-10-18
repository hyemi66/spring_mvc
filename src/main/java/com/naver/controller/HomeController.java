package com.naver.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* 스프링 컨트롤러 클래스 */
@Controller // @Controller 애노테이션을 사용함으로써 인터넷 웹상에서 작동하는 스프링 컨트롤로 인식함
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET) 
	// get으로 접근하는 매핑주소를 처리, 매핑 주소가 /(루트경로)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate ); 
		// 문자열 속성키이름인 serverTime에 값으로 날짜 포맷정보를 저장 -> 키, 값 쌍으로 저장
		
		return "home"; 
		// 뷰페이지 경로(뷰리졸브 경로라고도 함) -> /WEB-INF/views/home.jsp
		// home이 뷰페이지인 jsp파일명
	}
	
	// 댓글 뷰페이지 작업
	@RequestMapping("/test") // test 매핑주소 등록
	public void test() {
		// 메소드 리턴타입이 없는 void형이먄 매핑주소인 test가 뷰페이지 파일명 즉 test.jsp가 된다
	}
	
}
