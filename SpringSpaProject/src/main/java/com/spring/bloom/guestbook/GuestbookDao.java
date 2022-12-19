package com.spring.bloom.guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.spring.bloom.mybatis.GuestbookMapper;

@Component
@Transactional 
public class GuestbookDao {
	GPageVo gVo;
	
	@Autowired
	GuestbookMapper mapper;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	public int getTotSize(GPageVo gVo) {
		int totSize = 0;
		totSize = mapper.totSize(gVo);
		
		return totSize;
	}
	
	public List<GuestbookVo> select(GPageVo gVo){
		List<GuestbookVo> list = null;
		
		int totSize = getTotSize(gVo);
		gVo.setTotSize(totSize); // compute 메서드도 함께 실행됨.
		this.gVo = gVo;
		
		list = mapper.list(gVo);
		return list;
	}
	
	public List<GuestbookVo> select10(GPageVo gVo){
		List<GuestbookVo> list = null;
		
		list = mapper.list(gVo);
		return list;
	}
	
	public boolean insert(GuestbookVo vo) {
		boolean r = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.insert(vo);
		if(cnt > 0 ) {
			r = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return r;
	}
	
	public boolean delete(GuestbookVo vo) {
		boolean r = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.delete(vo);
		if(cnt > 0) {
			r = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return r;
	}
	
	public boolean update(GuestbookVo vo) {
		boolean r = false;
		status = manager.getTransaction(new DefaultTransactionDefinition());
		Object savePoint = status.createSavepoint();
		
		int cnt = mapper.update(vo);
		if(cnt > 0) {
			r = true;
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
		
		return r;
	}
	
	public GPageVo getGPageVo() {
		return gVo;
	}

	
}
