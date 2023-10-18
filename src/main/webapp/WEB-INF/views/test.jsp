<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>비동기식 아작스 댓글 연습</title>
<style>
#modDiv { /* 댓글 수정폼 ui */
	width: 300px; height: 100px;
	background-color: gray;
	position: absolute; /* 절대위치 */
	top: 50%;
	left: 50%;
	margin-top: -50px;
	margin-left: -150px;
	padding: 10px;
	z-index: 1000;
	/* position 속성이 absolute나 fixed(고정위치)로 설정된 곳에서 사용한다
	이속성은 요소가 겹쳐지는 순서를 제어할 수 있다. 숫자값이 큰 것이 먼저 앞에 나온다 */
}
</style>
</head>
<body>
	<%-- 댓글 수정화면 --%>
	<div id="modDiv" style="display:none;">
		<%-- display: none; css로 최초 실행시 댓글 수정화면을 안나오게 한다 --%>
		<div class="modal-title"></div> <%-- 댓글 번호 --%>
		<div>
			<textarea rows="3" cols="30" id="replytext"></textarea>
		</div>
		<div>
			<button type="button" id="replyModBtn">댓글 수정</button>
			<button type="button" id="replyDelBtn">댓글 삭제</button>
			<button type="button" id="closeBtn" onclick="modDivClose();">닫기</button>
		</div>
	</div>
	
	<h2>아작스 댓글 연습페이지</h2>
	<div>
		<div>
			댓글 작성자 : <input type="text" name="replyer" id="newReplyWriter">
		</div>
		<br>
		<div>
			댓글내용 : <textarea rows="5" cols="30" name="replytext" id="newReplyText"></textarea>
		</div>
		<br>
		<button type="button" id="replyAddBtn">댓글 등록</button>
	</div>
	
	<br>
	<hr>
	<br>
	
	<%-- 댓글 목록 --%>
	<ul id="replies"></ul>
	
	<%-- jQuery 라이브러리 읽어옴 --%>
	<script type="text/javascript" src="/controller/resources/js/jquery.js"></script>
	<script type="text/javascript">
		$bno = 15; // 게시판 bno 임시적으로 할당
		
		getAllList(); // 댓글 목록함수 호출
		function getAllList() {
			$.getJSON("/controller/replies/all/" + $bno, function(data){
				// get방식으로 JSON데이터를 처리하는 비동기식 jQuery 아작스함수
				// -> 서버로부터 가져온 JSON 데이터 목록은 data매개변수에 저장됨
				$result = "";
				$(data).each(function() { // jQuery each()함수로 반복
					$result += "<li data-rno='" + this.rno + "' class='replyLi'>"
					+ this.rno + " : <span class='com' style='color:blue; font-weight:bolder;'>"
					+ this.replytext + "</span> <button type='button'>댓글수정</button></li><br>";
				});
				$('#replies').html($result); // html() jQuery함수는 문자와 태그를 함께 변경 적용
				/*
					문제) 매핑주소 test가 실행되면 웹브라우저로 댓글 목록이 나오게 만들어보자
				*/
			});
		} // getAllList()
		
		// 댓글 추가
		$('#replyAddBtn').on("click", function() {
			$replyer = $('#newReplyWriter').val(); // 댓글 작성자
			$replytext = $('#newReplyText').val(); // 댓글 내용
			
			$.ajax({
				type : 'post', // 메소드 방식을 POST로 지정
				url : '/controller/replies', // 매핑주소
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "POST"
				},
				dataType : 'text', // 자료형
				data : JSON.stringify({
					bno : $bno, // bno 파라미터 이름에 번호값을 담아서 전달(파라미터 : 값)
					replyer : $replyer, // 키, 값 쌍으로 전달
					replytext : $replytext  // 댓글 내용(JSON)
				}),
				success : function(result) { // 비동기식으로 가져오는 것이 성공시 호출되는 콜백함수
					// 받아온 자료는 result매개변수에 저장
					if(result == 'SUCCESS') {
						alert('댓글이 등록되었습니다!');
						getAllList(); // 댓글 목록 함수 호출
					}
				}
			}); // jQuery 비동기식 아작스 함수
		});
		
		// 댓글 수정화면
		$('#replies').on('click', '.replyLi button', function(){
			var reply = $(this).parent(); // 부모 요소인 li태그를 구함
			var rno = reply.attr("data-rno"); // 댓글 번호를 구함 -> li태그의 data-rno속성값인 댓글번호를 구함
			var replytext = reply.children('.com').text();
			// li태그의 자식요소중 클래스 선택자 com을 구함 -> 댓글 내용을 구함
			
			$('.modal-title').html(rno); // modal-title클래스 선택자 영역에 댓글 번호를 변경 적용
			$('#replytext').val(replytext); // textarea 입력박스 영역에 댓글 내용을 표시
			$('#modDiv').show('slow'); // 댓글 수정화면을 천천히 보이게 하기
		});
		
		// 댓글 수정화면 닫기
		function modDivClose() {
			$('#modDiv').hide('slow');
		}
		
		// 댓글 수정완료
		$('#replyModBtn').on('click', function() {
			$rno = $('.modal-title').html(); // 댓글번호
			$replytext = $('#replytext').val(); // 댓글 수정 내용
			
			$.ajax({
				type : 'put',
				url : '/controller/replies/' + $rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "PUT"
				},
				data : JSON.stringify({
					replytext : $replytext
				}),
				dataType : 'text',
				success : function(result) {
					if(result == 'SUCCESS') {
						alert('댓글 수정되었습니다');
						$('#modDiv').hide('slow'); // 댓글 수정완료시 댓글 수정화면을 닫음
						getAllList(); // 댓글 목록 함수 호출
					}
				}
			}); // jQuery 비동기식 ajax()함수
		});
		
		// 댓글 삭제
		$('#replyDelBtn').on('click', function() {
			$rno = $('.modal-title').html(); // 댓글번호
			
			$.ajax({
				type : 'delete',
				url : '/controller/replies/' + $rno,
				headers : {
					"Content-Type" : "application/json",
					"X-HTTP-Method-Override" : "DELETE"
				},
				dataType : 'text',
				success : function(result) {
					if(result == 'SUCCESS') {
						alert('댓글 삭제되었습니다');
						$('#modDiv').hide('slow'); // 댓글 삭제완료시 댓글 수정화면을 닫고
						getAllList(); // 댓글 목록 함수를 호출
					}
				}
			});
		});
	</script>
</body>
</html>