package com.spring.bloom.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class BoardDao {
	
	@Autowired
	BoardService service;
	
	public List<BoardVo> board10(){
		List<BoardVo> list = null;
		list = service.board10();
		return list;
	}
	
	public List<BoardVo> select(BPageVo pVo){
		List<BoardVo> list = null;
		list = service.select(pVo);
		return list;
	}
}
