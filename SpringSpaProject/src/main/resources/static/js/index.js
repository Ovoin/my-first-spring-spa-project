/**
 * 
 */
 var url = "http://localhost:8383";
 
 
 $('#title').on('click',function(){
 	location.href = url;
 });
 
 
 
 /* guestbook */
 
 $('#guest').on('click',function(){
 	$('#guest').load('/guestbook/guestbook10');
 });
 
 $('.btnGuest').on('click',function(){
 	$('#content').load('/guestbook/guestbook_select');
 	
 });
 
 $('.btnBoard').on('click',function(){
 	$('#content').load('/board/board_select');
 });
 
 /* board */
 
 $('#board').on('click',function(){
	 $('#board').load('/board/board10');
 });
 