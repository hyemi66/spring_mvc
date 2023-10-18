<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script src="/controller/resources/js/jquery.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="2">공지 게시판 내용</th>
		</tr>
		<tr>
			<th>제목</th>
			<td>${gc.title}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${gc.name}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${gcont}</td>
		</tr>
		<tr>
			<th colspan="2">
				<input type="button" value="수정">
				<input type="button" value="삭제">
				<input type="button" value="목록"
				onclick="location='/controller/gongji/gongji_list';">
			</th>
		</tr>
	</table>
</body>
</html>