<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SpringSpaProject</title>
<link rel="stylesheet" href=css/index.css/>
<script src="https://kit.fontawesome.com/59e9622ad2.js" crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.6.1.min.js"></script>
<script defer src="js/index.js"></script>
</head>
<body>
<div id='main'>
    <header>
    	<h2 id='title'>Spring SPA Project</h2>
        <nav>
            <a href='/'>HOME</a>
            <a href='#' class='btnGuest'>방명록</a>
            <a href='#' class='btnBoard'>게시판</a>
        </nav>
    </header>
    <div id='content'>
    	<div class="info" id="guest">최신방명록 10개
    	<i class="fa-solid fa-users fa-5x"></i> 
    	</div>
    	<div class="info" id="board">최신게시물 10개
    	<i class="fa-solid fa-rectangle-list fa-5x"></i>
    	</div>           
    </div>
    <footer>
        Spring SPA Project
    </footer>
</div>
</body>
</html>