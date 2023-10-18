<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 아작스 파일첨부 연습</title>
<script src="./resources/js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		
		$('#uploadBtn').on('click', function(e){
			
			var formData = new FormData(); // 폼데이터 객체 생성
			/*
				첨부파일을 업로드 하는 또 다른 방식은 jQuery 비동기식 아작스를 이용해서 폼데이터 객체를 이용하는 것이다
				이 방식은 IE 10버전 이후에서만 지원된다. 폼데이터를 사용하려면 웹브라우저 제약이 발생한다
			*/
			
			var inputFile = $("input[name='uploadFile']");
			/*
				jQuery로 입력양식에 접근하는 방식으로 유일 아이디 선택자 방법이 있고,
				또 다른 방식은 네임피라미터(네임속성명) 이름으로 접근하는 방법이다
				여기서는 네임피라미터 이름으로 input type="file"에 접근한다
			*/
			
			var files = inputFile[0].files; // 첫번째 input type='file'로 첨부한 파일을 배열로 구한다
			
			// 첨부파일을 폼데이터에 추가
			for(var i=0; i<files.length; i++) {
				forData.append('uploadFile', files[i]);
			}
			
			$.ajax({
				url:'/controller/uploadAjaxAction', // 서버매핑주소
				processData: false, // 컨텐트 타입에 맞게 반환여부
				contentType: false, // 요청 컨텐트 타입
				data: formData, // 폼데이터 객체를 전송
				type: 'POST', // 보내는 방식
				success: function(result) {
					// 비동기식으로 받아오는게 성공시 호출되는 콜백함수
				}
			});
			
		});
		
	});
</script>
</head>
<body>
	<h1>Upload with Ajax</h1>
	<input type="file" name="uploadFile" multiple>
	<%-- 최근 웹브라우저는 multiple속성을 지원하는데 이를 사용하면 하나의 input type='file'로 다중 첨부파일을
	동시에 서버로 업로드 할 수 있다. 이 속성은 IE 10 이상에서만 사용가능하다 --%>
	
	<button type="button" id="uploadBtn">File upload</button>
</body>
</html>