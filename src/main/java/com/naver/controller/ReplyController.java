package com.naver.controller;

import java.util.List;

import org.aspectj.lang.annotation.RequiredTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.service.ReplyService;
import com.naver.vo.ReplyVO;

@RestController // @RestController 애노테이션을 추가함으로써 해당 컨트롤러는 REST API 서비스 프로그램을 개발할 수 있게 된다
@RequestMapping("/replies") // 컨트롤러 자체 매핑주소 등록
public class ReplyController {
	
	@Autowired
	private ReplyService replyService;
	
	// 댓글 등록
	@RequestMapping(value="", method=RequestMethod.POST) // POST로 접근하는 매핑주소를 처리
	public ResponseEntity<String> addReply(@RequestBody ReplyVO vo) {
		/*
			@RequestBody 애노테이션은 키, 값 쌍의 JSON 형태로 전송된 데이터를 ReplyVO 객체 타입으로 변경해줌
		*/
		
		ResponseEntity<String> entity = null;
		
		try {
			this.replyService.addReply(vo); // 댓글 등록
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			// 댓글 저장 성공시 SUCCESS 문자를 반환하고 동시에 200정상 상태코드(HttpStatus.OK)가 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			// 댓글 저장 실패시 예외 에러 메세지와 나쁜상태 코드가 반환
		}
		
		return entity;
	} // addReply()
	
	// 게시판 번호에 해당하는 댓글 목록 -> get방식
	@RequestMapping(value="/all/{bno}", produces = "application/json")
	public ResponseEntity<List<ReplyVO>> getReplyList(@PathVariable("bno") int bno) {
		/*
			@PathVariable("bno")는 매핑주소로부터 게시판 번호값을 추출하는 용도로 활용된다
		*/
		ResponseEntity<List<ReplyVO>> entity = null;
		
		try {
			entity = new ResponseEntity<>(this.replyService.listReply(bno), HttpStatus.OK);
			// 게시판 번호에 해당하는 댓글목록이 반환
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	} // getReplyList()
	
	// 댓글 수정
	@RequestMapping(value="/{rno}", method={RequestMethod.PUT, RequestMethod.PATCH})
	// PUT은 전체자료 수정, PATCH는 일부자료형 수정
	public ResponseEntity<String> editReply(@PathVariable("rno") int rno, @RequestBody ReplyVO vo) {
		ResponseEntity<String> entity = null;
		
		try {
			vo.setRno(rno); // 댓글 번호 저장
			this.replyService.updateReply(vo); // 댓글 수정
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	} // editReply()
	
	// 댓글 삭제
	@RequestMapping(value="{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> del(@PathVariable("rno") int rno) {
		ResponseEntity<String> entity = null;
		
		try {
			this.replyService.delReply(rno); // 댓글 삭제
			entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
			/*
				문제) 댓글 번호를 기준으로 삭제되게 serviceImpl, DAOImpl, reply.xml까지 완성해서
				arc에서 개발자 테스트까지 완료해본다
			*/
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
}
