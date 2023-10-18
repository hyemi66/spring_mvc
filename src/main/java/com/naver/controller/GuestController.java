package com.naver.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.naver.service.GuestService;
import com.naver.vo.GuestVO;

@Controller
@RequestMapping("/guest/*")
public class GuestController {
	/*
		문제
		1. 다음과 같은 컬럼 정보를 가진 방명록 테이블 tbl_guest를 생성한다
		컬럼명	자료(크기)		제약조건
		gno		int				primary key
		gname	varchar2(20)	not null
		gtitle	varchar2(200)	not null
		gcont	varchar2(4000)	not null
		ghit	int				default 0
		gdate	date			default sysdate
		
		2. 1부터 시작해서 1씩 증가하고, 임시 메모리를 사용하지 않는 gno_seq2 시퀀스를 생성한다
		3. GuestService.java, GuestServiceImpl.java를 com.naver.service패키지에 생성한다
		4. GuestDAO.java, GuestDAOImpl.java를 com.naver.dao패키지에 생성한다
		5. com.naver.mappers.mybatis 패키지에 guest.xml를 만들어서 mybatis매퍼태그로 sql문을 작성한다
		6. /WEB-INF/views/guestbook폴더에 뷰페이지 파일을 만든다
			가. 먼저 방명록 글쓴이, 글제목, 글내용을 입력하는 입력폼을 만들고 자바스크립트와 jQuery를 사용한 유효성 검증메세지를 띄운다
				입력폼 매핑주소는 guest_write로 한다
			나. 방명록 저장되는 매핑주소 guest_write_ok를 작성한다
			다. 페이징과 책갈피 기능이 되는 guest_list 방명록 목록보기 매핑주소를 작성한다
			라. 조회수 증가와 내용보기가 되는 guest_cont 매핑주소를 작성한다.
				조회수증가와 내용보기는 GuestServiceImpl.java에서 나중에 스프링 AOP를 통한 트랜잭션을 적용할 수 있게 논리적 단위로 묶는다
			마. 방명록 수정폼 guest_edit매핑주소를 작성하고, 수정완료되게 guest_edit_ok 매핑주소를 작성한다
			바. 번호를 기준으로 삭제되게 guest_del매핑주소를 작성한다
				모든 매핑주소를 처리할 때 책갈피 기능이 구현되게 get방식으로 페이지 번호를 전달한다
			사. com.naver.vo패키지에 GuestVO.java빈클래스 생성한다
	*/
	
	@Autowired
	private GuestService guestService;
	
	// 게스트 글쓰기 폼
	@RequestMapping(value="/guest_write", method=RequestMethod.GET)
	// /guest_write매핑주소 등록
	private void guest_write(Model wm, HttpServletRequest request) {
		int page = 1;
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		wm.addAttribute("page", page);
	}
	
	// 게스트 글쓰기 저장
	@PostMapping("/guest_write_ok")
	public ModelAndView guest_write_ok(GuestVO g, RedirectAttributes rttr) {
		this.guestService.insertGuest(g);
		rttr.addFlashAttribute("msg", "success");
		
		return new ModelAndView("redirect:/guest/guest_list");
	}
	
	// 게스트 목록
	@RequestMapping("/guest_list")
	public String guest_list(Model listG, HttpServletRequest request,
			@ModelAttribute GuestVO g) {
		
		// 페이징시작
		int page = 1; // 쪽번호
		int limit = 5; // 한페이지에 보여질 목록 개수
		if(request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		g.setStartrow((page - 1) * 5 + 1); // 시작 행번호
		g.setEndrow(g.getStartrow() + limit - 1); // 끝 행번호
		// 페이징끝
		
		int totalCount = this.guestService.getTotalCount();
		List<GuestVO> glist = this.guestService.getGuestList(g);
		
		// 페이징 연산 시작
		int maxpage = (int)((double)totalCount/limit + 0.95); // 총 페이지 수
		int startpage = (((int)((double)page / 5 + 0.9)) - 1) * 5 + 1; // 현재 페이지에 보여질 시작페이지
		int endpage = maxpage; // 현재 페이지에 보여질 마지막 페이지
		
		if(endpage > startpage + 5 - 1) {
			endpage = startpage + 5 - 1;
		}
		// 페이징 연산 끝
		
		listG.addAttribute("totalCount", totalCount);
		listG.addAttribute("glist", glist);
		listG.addAttribute("startpage", startpage);
		listG.addAttribute("endpage", endpage);
		listG.addAttribute("maxpage", maxpage);
		listG.addAttribute("page", page);
		
		return "guest/guest_list";
	}
	
}
