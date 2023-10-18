<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스프링 MVC 게스트 목록</title>
<script src="../resources/js/jquery.js"></script>
</head>
<body>
	<table border="1">
		<tr>
			<th colspan="5">스프링 MVC 게스트 목록</th>
		</tr>
		<tr>
			<th colspan="5" align="right">
				<span style="font-weight:bolder;">총 게시물 수 : ${totalCount}</span>
			</th>
		</tr>
		<tr>
			<th>번호</th> <th>글쓴이</th> <th>글제목</th> <th>조회수</th> <th>등록날짜</th>
		</tr>
		<c:if test="${!empty glist}">
			<c:forEach var="g" items="${glist}">
				<tr>
					<th>${g.gno}</th>
					<th>${g.gname}</th>
					<th>
						<a href="#">${g.gtitle}</a>
					</th>
					<th>${g.ghit}</th>
					<th>${g.gdate}</th>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty glist}">
			<tr>
				<th colspan="5">게시물 목록이 없습니다</th>
			</tr>
		</c:if>
		<%-- 페이징 시작 --%>
		<tr>
			<th colspan="5">
				<c:if test="${page<=1}">
					[이전]&nbsp;
				</c:if>
				<c:if test="${page>1}">
					<a href="/controller/guest/guest_list?page=${page-1}">[이전]</a>&nbsp;
				</c:if>
				
				<%-- 쪽번호 출력 부분 --%>
				<c:forEach var="a" begin="${startpage}" end="${endpage}" step="1">
					<c:if test="${a==page}">
						<${a}>
					</c:if>
					<c:if test="${a!=page}">
						<a href="/controller/guest/guest_list?page=${a}">[${a}]</a>&nbsp;
					</c:if>
				</c:forEach>
				
				<c:if test="${page>=maxpage}">
					[다음]
				</c:if>
				<c:if test="${page<maxpage}">
					<a href="/controller/guest/guest_list?page=${page+1}">[다음]</a>
				</c:if>
			</th>
		<%-- 페이징 끝 --%>
		<tr>
			<td colspan="5" align="right">
				<input type="button" value="글쓰기" onclick="location='/controller/guest/guest_write';">
			</td>
		</tr>
	</table>
</body>
</html>