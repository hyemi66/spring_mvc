<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<h2>노트북 상품명과 상품가격</h2>
상품명 : ${p.name}<hr> <%-- ${p.name} EL은 자바코드로 표현하면 p.getName();호출한것과 동일 --%>
상품가격 : ${p.price}원<hr>
</body>
</html>