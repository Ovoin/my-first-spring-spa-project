package com.spring.bloom.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.spring.bloom.guestbook.GPageVo;
import com.spring.bloom.guestbook.GuestbookVo;

@Mapper
@Repository
public interface GuestbookMapper {
	public int totSize(GPageVo gVo);
	public List<GuestbookVo> list(GPageVo gVo);
	public List<GuestbookVo> listTen(GPageVo gVo);
	public int insert(GuestbookVo vo);
	public int delete(GuestbookVo vo);
	public int update(GuestbookVo vo);
}
