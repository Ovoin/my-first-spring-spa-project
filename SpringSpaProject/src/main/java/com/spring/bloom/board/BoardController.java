package com.spring.bloom.board;

import java.net.http.HttpResponse;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BoardController {
	
	@Autowired
	BoardDao dao;
	
	@Autowired
	BoardService service;
	
	@RequestMapping("/board/board_select")
	public ModelAndView select(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = dao.select(pVo);
		pVo = service.getpVo();
		
		mv.addObject("list", list);
		mv.addObject("pVo",pVo);
		mv.setViewName("board/board_select");
		return mv;
	}
	
	@RequestMapping("/board/board10")
	public ModelAndView board10() {
		ModelAndView mv = new ModelAndView();
		List<BoardVo> list = dao.board10();
		
		
		mv.addObject("list", list);
		mv.setViewName("board/board10");
		return mv;
	}
	
	@RequestMapping("/board/board_insert")
	public ModelAndView insert(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("pVo", pVo);
		mv.setViewName("board/board_insert");
		return mv;
	}
	
	@RequestMapping("/board/board_view")
	public ModelAndView view(BoardVo bVo, BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		//조회수 증가
		bVo = service.view(bVo.getSno(),"up");
		
		// doc안에 있는 \n 기호를 <br/>로 변경
        String temp = bVo.getDoc();
        bVo.setDoc(temp.replace("\n", "<br/>"));
        mv.addObject("bVo", bVo);
        mv.addObject("pVo",pVo);
		mv.setViewName("board/board_view");
		return mv;
	}
	
	@RequestMapping("/board/board_update")
	public ModelAndView update(BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		BoardVo bVo = service.view(pVo.getSno(), "");
		mv.addObject("pVo", pVo);
		mv.addObject("bVo", bVo);
		mv.setViewName("/board/board_update");
		return mv;
	}
	
	@RequestMapping("/board/board_delete")
	public ModelAndView delete(BoardVo bVo, BPageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		boolean b = service.delete(bVo);
		if(!b) msg = "삭제중 오류 발생!";
		
		mv = select(pVo);
		mv.addObject("msg", msg);
		mv.addObject("pVo", pVo);
		mv.setViewName("board/board_select");
		return mv;
	}
	
	@RequestMapping("/board/board_repl")
	public ModelAndView repl(BoardVo bVo,BPageVo pVo) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("pVo", pVo);
		mv.addObject("bVo", bVo);
		mv.setViewName("/board/board_repl");
		return mv;
	}
}
