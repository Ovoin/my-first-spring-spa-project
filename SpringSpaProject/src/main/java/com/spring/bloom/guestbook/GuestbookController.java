package com.spring.bloom.guestbook;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class GuestbookController {
	
	@Autowired
	GuestbookDao dao;
	
	@RequestMapping("/guestbook/guestbook_select")
	public ModelAndView select(GPageVo gVo) {
		ModelAndView mv = new ModelAndView();
		
		List<GuestbookVo> list = dao.select(gVo);
		// List를 mv에 추가
		gVo = dao.getGPageVo(); // 새로 갱신된 페이지정보
		
		System.out.println("findStr : " + gVo.findStr);
		System.out.println("nowPage : " + gVo.nowPage);
		System.out.println("totSize : " + gVo.totSize);
		
		mv.addObject("gVo",gVo);
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook_select");
		return mv;	
	}
	
	@RequestMapping("/guestbook/guestbook10")
	public ModelAndView guestbook10(GPageVo gVo) {
		ModelAndView mv = new ModelAndView();
		
		List<GuestbookVo> list = dao.select(gVo);
		// List를 mv에 추가
		gVo = dao.getGPageVo(); // 새로 갱신된 페이지정보
		
		System.out.println("totSize : " + gVo.totSize);
		
		mv.addObject("gVo",gVo);
		mv.addObject("list", list);
		mv.setViewName("guestbook/guestbook10");
		return mv;	
	}
	
	@RequestMapping("/guestbook/guestbook_insert")
	public void insert(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.insert(vo);
		System.out.println(" id : " + vo.id);
		System.out.println(" pwd : " + vo.pwd);
		try {
			PrintWriter out = resp.getWriter();
			if( !b ) {
				out.print("<script>");
				out.print(" 	alert('저장 중 오류 발생!')");
				out.print("</script>");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/guestbook/guestbook_delete")
	public void delete(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.delete(vo);
		
		System.out.println(" sno :" + vo.sno);
		System.out.println(" pwd : " + vo.pwd);
		try {
			PrintWriter out = resp.getWriter();
			if( !b ) {
				out.print("<script>");
				out.print(" 	alert('삭제 중 오류 발생!')");
				out.print("</script>");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/guestbook/guestbook_update")
	public void update(GuestbookVo vo, HttpServletResponse resp) {
		boolean b = dao.update(vo);
		
		System.out.println(" sno :" + vo.sno);
		System.out.println(" pwd : " + vo.pwd);
		try {
			PrintWriter out = resp.getWriter();
			if( !b ) {
				out.print("<script>");
				out.print(" 	alert('수정 중 오류 발생!')");
				out.print("</script>");
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
