<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지 MVC 입력 폼</title>
<script src="../resources/js/jquery.js"></script>
<script>
	function write_check() {
		if($.trim($("#gongji_name").val()) == "") {
			alert("작성자를 입력하세요!");
			$('#gongji_name').val("").focus();
			return false;
		}
		
		if($.trim($("#gongji_title").val()) == "") {
			alert("제목을 입력하세요!");
			$("#gongji_title").val("").focus();
			return false;
		}
		
		if($.trim($("#gongji_cont").val()) == "") {
			alert("내용을 입력하세요");
			$("#gongji_cont").val("").focus();
			return false;
		}
	}
</script>
</head>
<body>
	<form method="post" action="gongji_insert_ok" onsubmit="return write_check();">
	<table border="1">
		<tr>
			<th colspan="2">공지게시판</th>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input name="gongji_name" id="gongji_name" size="14"></td>
		</tr>
		<tr>
			<th>제목</th>
			<td><input name="gongji_title" id="gongji_title" size="20"></td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="gongji_cont" id="gongji_cont" rows="12" cols="36"></textarea></td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="submit" value="등록">
				<input type="reset" value="취소" onclick="$('#gongji_name').focus();">
			</th>
		</tr>
	</table>
</body>
</html>