/**
 *  guestbook.js
 */


/* guestbook.select.jsp */

(gb = function(){
	
	$('.btnSearch').on('click',function(){
		var frm = $('.frm_search')[0];
		frm.nowPage.value = 1;
		let param = $(frm).serialize();
		$('#content').load('/guestbook/guestbook_select',param);
	});
	
	$('.btnPrev').on('click',function(){
		var frm = $('.frm_search')[0];
		console.log(frm);
		frm.nowPage.value = Number(frm.nowPage.value)-1;
		let param = $(frm).serialize();
		$('#content').load('/guestbook/guestbook_select',param);
	});

	$('.btnNext').on('click',function(){
		var frm = $('.frm_search')[0];
		frm.nowPage.value = Number(frm.nowPage.value)+1;
		let param = $(frm).serialize();
		$('#content').load('/guestbook/guestbook_select',param);
	});
	
	$('.btnSave').on('click',function(){
		var frm = $('.frm_guest_insert')[0];
		var param = $(frm).serialize();
		$.post('/guestbook/guestbook_insert', param,function(){
			frm = $('.frm_search')[0];
			param = $(frm).serialize();
			$('#content').load('/guestbook/guestbook_select',param);
		})
	})
	
	gb.modifyView = function(frm){
		var doc = frm.doc;
		if(doc.disabled == true){
			doc.disabled = false;
		} else{
			doc.disabled = true;
		}
		let div =  frm.querySelector('.updateZone');
		div.style.visibility = 'visible';
		
		
	}
	
	gb.modifyCancel = function(frm){
		let div =  frm.querySelector('.updateZone');
		div.style.visibility = 'hidden';
		
	}
	
	/* modal box ----------- */
	$('#btnClose').on('click', function(){
		$('#modal').css('display','none');
	})
	
	var delForm;
	
	gb.modalView = function(frm){
		delForm = frm;
		$('#modal').css('display','block');
	}
	
	/* 방명록 삭제 --------- */
	$('#btnCheck').on('click',function(){
		delForm.pwd.value = $('#pwd').val();
		
		var frm = delForm;
		
		var param = $(frm).serialize();
		$.post('/guestbook/guestbook_delete', param,function(){
			frm = $('.frm_search')[0];
			param = $(frm).serialize();
			$('#content').load('/guestbook/guestbook_select',param);
		})
	})
	
	/* 방명록 수정하기 ---------- */
	
	gb.update = function(frm){
		let param = $(frm).serialize();
		
		$.post('/guestbook/guestbook_update', param,function(){
			frm = $('.frm_search')[0];
			param = $(frm).serialize();
			$('#content').load('/guestbook/guestbook_select',param);
		})
	}
		
		
})();

