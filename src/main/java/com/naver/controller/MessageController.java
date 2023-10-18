package com.naver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.service.MessageService;
import com.naver.vo.MessageVO;

@RestController
@RequestMapping("/message")
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	
	// 메시지 추가
	@RequestMapping(value="/message_write", method=RequestMethod.POST) // post로 접근하는 매핑주소 처리
	public ResponseEntity<String> message_write(@RequestBody MessageVO vo) {
		/*
			@RequestBody MessageVo vo
			전송되는 JSON 키, 값 쌍의 사전적인 자료를 MessageVO 객체 타입으로 변환시켜준다
		*/
		
		ResponseEntity<String> entity = null;
		
		try {
			this.messageService.insertMessage(vo); // 메세지 추가
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// 에러가 발생하면 예외 에러 메시지와 나쁜 상태 코드가 반환
		}
		
		return entity;
	}
	
}
