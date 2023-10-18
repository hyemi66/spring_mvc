package com.naver.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import oracle.jdbc.proxy.annotation.Post;

@Controller // 스프링 이진파일 업로드 컨트롤러 클래스(자료실: 파일 첨부 기능 추가)
public class UploadController {
	
	@GetMapping("/uploadForm") // get으로 접근하는 매핑주소를 처리
	public void uploadForm() {
		// 리턴타입이 없는 void형이면 매핑주소가 jsp파일명이 된다
		
	} // uploadForm()
	
	@PostMapping("/uploadFormAction")
	public void uploadFormAction(MultipartFile[] uploadFile, HttpServletRequest request) {
		/*
	 		MultipartFile 스프링 api를 사용해서 업로드 되는 파일 데이터를 쉽게 처리.
	 		다중 업로드 파일은 배열로 받는다. 매개변수(전달인자명) uploadFile과 input type="file" name="uploadFile"
	 		의 네임피라티머 이름이 같아야 한다
		*/
		String uploadFolder = request.getRealPath("/resources/upload"); // 첨부 파일 업로드 서버 실제 경로를 구함
		System.out.println("첨부파일 업로드 실제 경로 = " + uploadFolder);
		
		for(MultipartFile multi : uploadFile) {
			System.out.println("=============");
			System.out.println("Upload File Name : " + multi.getOriginalFilename()); // 첨부 파일 실제 원본 파일명을 구함
			System.out.println("Upload File Size : " + multi.getSize()); // 업로드 파일크기
			File saveFile = new File(uploadFolder, multi.getOriginalFilename());
			
			try {
				multi.transferTo(saveFile); // 업로드 폴더에 첨부파일 실제 업로드 됨
			} catch(Exception e) { e.printStackTrace(); }
			
			/*
				문제) 톰켓 WAS서버에 의해서 실제 upload폴더에 첨부파일이 업로드 되게 해보자
				개발자 테스트까지 해보자. 한개 파일도 업로드 해보고 다중 파일도 업로드 해본다
			*/
		}
	} // uploadFormAction()
	
	//비동기식 이진파일 업로드 폼 뷰 페이지 작성
	@GetMapping("/uploadAjax")//uploadAjax 매핑주소
	public ModelAndView uploadAjax() {
		ModelAndView um = new ModelAndView();
		
		um.setViewName("uploadAjaxForm"); // 뷰페이지 경로 - /WEB_INF/views/uploadAjaxForm.jsp
		
		return um;      
	}//uploadAjax()
	
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxAction(MultipartFile[] uploadFile) {
		// 리턴 타입이 없는 void형이면 매핑주소가 뷰페이지 파일명이 된다
		
		System.out.println("upload ajax post...");
		String uploadFolder = "c:\\upload"; // 이진파일 업로드 서버경로
		
		for(MultipartFile multi : uploadFile) {
			System.out.println("---------------------");
			System.out.println("첨부된 원본파일명 : " + multi.getOriginalFilename());
			System.out.println("첨부된 파일크기 : " + multi.getSize());
			
			String uploadFileName = multi.getOriginalFilename();
			uploadFileName = uploadFileName.substring(uploadFileName.lastIndexOf("\\") + 1);
			/*
				IE인 경우 전체 파일 경로가 전송되기 때문에 마지막 경로 구분 '\'이후부터 마지막 문자까지 구한다.
				즉, 첨부된 실제 파일명만 구한다
			*/
			System.out.println("only file name : " + uploadFileName);
			
			File saveFile = new File(uploadFolder, uploadFileName);
			
			try {
				multi.transferTo(saveFile); // 실제 첨부파일을 업로드
			} catch(Exception e) { e.printStackTrace(); }
			
			/*
				문제) 비동기식으로 한개나 다중 첨부파일이 c:\\upload폴더에 실제 업로드 되는지 확인해보자
			*/
		}
	} // uploadAjaxAction()
	
}
