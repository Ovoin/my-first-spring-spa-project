<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>board10.jsp</title>
<script defer src="js/index.js"></script>
</head>
<body>
	<c:forEach var="vo" items="${list}">
		<div>
			<li> 작성자 : ${vo.id}
			<li> 제목 : ${vo.subject}
			<li> 내용 : ${vo.doc}
		</div>
		<hr/>
	</c:forEach>
</body>
</html>