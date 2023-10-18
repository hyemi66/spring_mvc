<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<srcipt src="../resouces/js/jquery.js"></srcipt>
<!--
<script>
	$msg = "${msg}";
	
	if($msg == 'success') {
		alert("공지 게시판에 등록되었습니다");
	}
</script>
-->
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="5">공지 게시판 목록</th>
		</tr>
		<tr>
			<td colspan="5" align="right">
				<span style="font-weight:bolder;">총 게시물 수 : ${totalCount}</span>
			</td>
		</tr>
		<tr>
			<th>번호</th> <th>제목</th> <th>작성자</th> <th>조회수</th> <th>등록날짜</th>
		</tr>
		<c:if test="${!empty glist}">
			<c:forEach var="g" items="${glist}">
				<tr>
					<th>${g.gongji_no}</th>
					<th>
						<a href="/controller/gongji_cont?gongji_no=${g.gongji_no}">
							${g.gongji_title}
						</a>
					</th>
					<th>${g.gongji_name}</th>
					<th>${g.gongji_hit}</th>
					<th><fmt:formatDate value="${g.gongji_date}" pattern="yyyy/MM/dd"/></th>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty glist}">
			<tr>
				<th colspan="5">목록이 없습니다</th>
			</tr>
		</c:if>
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="게시물 등록" onclick="location='/controller/gongji/gongji_insert';">
			</td>
		</tr>
	</table>
</body>
</html>