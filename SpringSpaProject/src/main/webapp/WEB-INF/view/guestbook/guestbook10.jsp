<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook10.jsp</title>
<link rel="stylesheet" href="css/index.css"/>
<script defer src="js/index.js"></script>
</head>
<body>
	<c:forEach var="vo" items="${list}">
	    	<div id="recent_guest">
	    		<label>작성자</label>
				<input type="text" name="id" value="${vo.getId()}"/>
				<label>작성일</label>
				<input type="date" name="nal" value="${vo.getNal()}"/>
				<textarea name="doc" cols="35" id="guest_doc" disabled >
					${vo.getDoc()}
				</textarea>
				<hr/>
	    	</div>
	</c:forEach>
</body>
</html>