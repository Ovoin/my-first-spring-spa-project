<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>guestbook_select.jsp</title>
<link rel="stylesheet" href="css/guestbook.css"/>
<script defer src="js/guestbook.js"></script>
</head>
<body>
	<div id="guest_list">
	
	<%@include file="guestbook_insert.jsp" %>
	
		<form class="frm_search" method='post' >
			<div class="search_zone">
				<span class="icon"><i class="fa fa-search fa-2xl"></i></span>
				<input type='search' name='findStr' value="${gVo.findStr}"
					   placeholder="검색어를 입력하세요"	/>
 				<input type='button' value='검색' class='btnSearch'/> 
			</div>
			<input type='hidden' name='nowPage' value="${gVo.nowPage}" size="1"/>
			<input type='hidden' name='serial' value="${gVo.sno }"/>
		</form>
		
		<div class="guest_items">
			<c:forEach var="vo" items="${list}">
				<div class="item">
					<form class="frm frm_guest" method="post">
						<div>
							<input type='button' value='수정하기' class='btnUpdate'
									onclick="gb.modifyView(this.form)"/>
							<input type='button' value='X' class='btnDelete'
									onclick="gb.modalView(this.form)"/>
						</div>
						<label>작성자</label>
						<input type="text" name="id" value="${vo.getId()}"/>
						<label>작성일</label>
						<input type="date" name="nal" value="${vo.getNal()}"/>
						<textarea name="doc" cols="35" id="guest_doc" disabled >
							${vo.getDoc()}
						</textarea>
						<div class="updateZone">
							<label>암호</label>
							<input type="password" name="pwd"/>
							<input type="button" value="수정" class="btnGuestUpdate"
								   onclick ="gb.update(this.form)"/>
							<input type="button" value="취소" 
									onclick = "gb.modifyCancel(this.form)"/>
						</div>
						
						<input type="hidden" name="sno" value="${vo.sno}"/>
					</form>
				</div>
			</c:forEach>	
		</div>
		<div class="guest_btn">
			<c:if test="${gVo.getStartNo() > 1}">
				<input type="button" value="&lt;" class="btnPrev" />
			</c:if>
			<c:if test="${gVo.getTotSize()> gVo.getEndNo()}">
				<input type="button" value="&gt;" class="btnNext"/>
			</c:if>
		</div>
	</div>
	
	<div id="modal">
		<div id="modal-content">
			<input type="button" id="btnClose" value="X"/>
			<span>암호를 입력하세요</span>
			<input type="password" id="pwd" />
			<input type="button" value="확인" id="btnCheck"/>
		</div>
	</div>
	
</body>
</html>