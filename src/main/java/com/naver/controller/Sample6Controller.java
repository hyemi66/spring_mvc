package com.naver.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.naver.vo.SampleVO;

@RestController
@RequestMapping("/sample")
public class Sample6Controller {
	@RequestMapping("/hello")
	// /hello , get or post 라는 매핑주소를 등록 처리가능
	public String hello() {
		return "rest begin";//문자열 결과가 반환
	}
	
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {
		// SampleVO JSON 리턴 타입이 이면 변수명이 객체의
		// . 키이름이 된다
		SampleVO vo=new SampleVO();
		vo.setFirstName("홍");
		vo.setLastName("길동");
		vo.setMno(7);
		return vo;
	}
	
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<>();
		for(int i=1;i<=10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕님");
			list.add(vo);//컬렉션 추가
		}//for
		return list;
	} // sendList()

	
	// , Map JSON 키 값 쌍의 타입
	@RequestMapping("/sendMap")
	public Map<Integer,SampleVO> sendMap(){
		Map<Integer,SampleVO> map = new HashMap<>();
		for(int i=1;i<=10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("이");
			vo.setLastName("순신");
			map.put(i,vo);// , 컬렉션에 키 값 저장
		}//for
		return map;
	} // sendMap()

	
	@RequestMapping("/sendError")
	public ResponseEntity<Void> sendError() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*
			@RestController는 별도의 jsp뷰페이지를 만들지 않고도 Rest api 서비스를 실행하기 때문에
			결과 데이터에 대한 예외 오류가 많이 발생한다. 이런 경우에는 스프링에서 제공하는 내장 api인
			ResponseEntity 타입을 사용해서 개발자에게 문제가 되는 HTTP 에러 상태코드인 404,500등과 같은
			나쁜 상태코드를 데이터와 함께 브라우저로 전송되어 져서 좀더 세밀한 에러 제어를 할 수 있게 한다.
			
			BAD_REQUEST는 400 나쁜 상태 코드를 브라우저로 전송한다
		*/
	} // sendError()
	
	// 컬렉션 List 제네릭 타입 json정상적인 데이터와 404(해당 경로에 파일 찾을 수 없음 에러)
	// 나쁜 상태코드를 동시에 브라우저로 전송
	@RequestMapping(value="/sendListErrorNot", produces="application/json")
	public ResponseEntity<List<SampleVO>> sendListNot() {
		List<SampleVO> list = new ArrayList<>();
		
		for(int i=3; i>=1; i--) {
			SampleVO vo = new SampleVO();
			vo.setMno(i);
			vo.setFirstName("이");
			vo.setLastName("순신");
			
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list, HttpStatus.NOT_FOUND);
		// NOT_FOUND => 404에러 상태 코드
	}

}
