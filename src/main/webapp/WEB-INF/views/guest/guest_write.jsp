<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게스트 작성폼</title>
<script src="../resources/js/jquery.js"></script>
<script>
	function guest_check() {
		if($.trim($("#gname").val()) == "") {
			alert("글쓴이를 입력하세요!");
			$('#gname').val("").focus();
			return false;
		}
		
		if($.trim($("#gtitle").val()) == "") {
			alert("글제목을 입력하세요!");
			$('#gtitle').val("").focus();
			return false;
		}
		
		if($.trim($("#gcont").val()) == "") {
			alert("글내용을 입력하세요!");
			$('#gcont').val("").focus();
			return false;
		}
	}
</script>
</head>
<body>
	<form method="post" action="guest_write_ok" onsubmit="return guest_check()">
	<h2>스프링 MVC 게스트 작성폼</h2>
	글쓴이 : <input name="gname" id="gname"><br><br>
	글제목 : <input name="gtitle" id="gtitle"><br><br>
	글내용 : <textarea name="gcont" id="gcont" rows="10" cols="36"></textarea>
	<hr>
	<input type="submit" value="글쓰기">
	<input type="reset" value="취소" onclick="$('#gname').focus();">
</body>
</html>