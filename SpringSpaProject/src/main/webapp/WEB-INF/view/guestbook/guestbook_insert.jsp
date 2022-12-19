<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook_insert.jsp</title>
<link rel="stylesheet" href="css/guestbook.css"/>
</head>
<body>
	<div class="item">
		<form class="frm frm_guest_insert" method="post">
			<label>작성자</label>
			<input type="text" name="id"/>
			<label>작성일</label>
			<input type="date" name="nal"/>
			<textarea name="doc">
			</textarea>
			<label>암호</label>
			<input type="password" name="pwd"/>
			<input type="button" value="작성" class="btnSave"/>
		</form>
	</div>
</body>
</html>