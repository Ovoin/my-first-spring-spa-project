package com.spring.bloom.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.spring.bloom.board.AttVo;
import com.spring.bloom.board.BPageVo;
import com.spring.bloom.board.BoardVo;

@Mapper
@Repository
public interface BoardMapper {
	// 조회
	public int totList(BPageVo bVo);
	public List<BoardVo> select(BPageVo pVo);
	public List<BoardVo> board10();
	
	// 상세보기
	public void hitUp(int sno);
	public BoardVo view(int sno);
	public List<AttVo> attList(int sno);
	
	// 입력 
	public int insertR(BoardVo vo);
	public int insertAttList(List<AttVo> attList);
	
	//수정
	public int update(BoardVo vo);
	public int attUpdate(BoardVo vo);
	public int attDelete(String[] delFiles);
	
	// 삭제
	public int replCheck(BoardVo vo);
	public int delete(BoardVo vo);
	public List<String> delFileList(int sno);
	public int attDeleteAll(int sno);
	
	// 답글
	public void seqUp(BoardVo vo);
	public int repl(BoardVo vo);
	
}
