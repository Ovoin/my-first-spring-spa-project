package com.spring.bloom.board;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;

import com.spring.bloom.mybatis.BoardMapper;



@Service
@Transactional
public class BoardService {
	BPageVo pVo;
	
	@Autowired
	PlatformTransactionManager manager;
	TransactionStatus status;
	
	@Autowired
	BoardMapper mapper;
	Object savePoint;
	
	
	public BPageVo getpVo() {
		return pVo;
	}

	public List<BoardVo> select(BPageVo pVo){
		int totSize = mapper.totList(pVo);
		pVo.setTotSize(totSize);
		this.pVo = pVo;
		
		List<BoardVo> list = mapper.select(pVo);
		return list;
	}
	
	public List<BoardVo> board10(){
		List<BoardVo> list = mapper.board10();
		return list;
	}
	
	public BoardVo view(int sno, String up) {
		BoardVo bVo = null;
		if(up !=null && up.equals("up")) {
			mapper.hitUp(sno);
		}
		bVo = mapper.view(sno);
		List<AttVo> attList = mapper.attList(sno);
		bVo.setAttList(attList);
		
		return bVo;
		
	}
	
	public boolean insertR(BoardVo vo) {
		status = manager.getTransaction(new DefaultTransactionAttribute());
		savePoint = status.createSavepoint();
		boolean flag = true;
		
		int cnt = mapper.insertR(vo);
		cnt = 0;
		if(cnt < 1) {
			status.rollbackToSavepoint(savePoint);
			List<AttVo> list = vo.getAttList();
			if(list.size() > 0) {
				for(AttVo aVo : list) {
					System.out.println("파일도 삭제됨~");
					File delFile = new File(FileUploadController.path + aVo.sysFile);
					if(delFile.exists()) delFile.delete();
				}
			}
			
			flag = false;
		}
		
		
		return flag;
	}
	
	public boolean updateR(BoardVo bVo, String[] delFiles) {
        status = manager.getTransaction(new DefaultTransactionAttribute());
		savePoint = status.createSavepoint();
        
       
        boolean b=true;
        int cnt = mapper.update(bVo);
        if(cnt<1) {
        	
            b=false;
            
        }else if(bVo.getAttList().size()>0) {
            int attCnt = mapper.attUpdate(bVo);
            if(attCnt<1) b=false;
        }
       
        if(b) {
        	manager.commit(status);
            if(delFiles.length>0) {
                // 첨부 파일 데이터 삭제
                cnt = mapper.attDelete(delFiles);
                if(cnt>0) {
                    fileDelete(delFiles); // 파일 삭제
                }else {
                    b=false;
                }
            }
        } else {
        	status.rollbackToSavepoint(savePoint);
        }
        
       
        return b;
    }
	
	public void insertAttList(List<AttVo> attList) {
		int cnt = mapper.insertAttList(attList);
		if(cnt>0) {
			manager.commit(status);
		} else {
			status.rollbackToSavepoint(savePoint);
		}
	}
	
	public boolean delete(BoardVo bVo) {
		boolean b = true;
		status = manager.getTransaction(new DefaultTransactionAttribute());
		Object savePoint = status.createSavepoint();
		// 같은 grp안에 자신의 seq보다 1더 큰 seq의 자료에서
        // deep이 자신 보다 큰것이 있으면 댓글이 있는 것임.
        int replCnt = mapper.replCheck(bVo);
        System.out.println("replCnt : " + replCnt);
        
        if(replCnt > 0) {
        	b = false;
        	return b;
        }
        
        // 테이블(board)에서 삭제
        int cnt = mapper.delete(bVo);
        System.out.println("cnt : " + cnt);
        if(cnt < 1) {
        	b = false;
        } else {
        	//sno를 pSno로 바꾸어 첨부 테이블에서 첨부파일(sysFile) 목록 가져오기
        	List<String> attList = mapper.delFileList(bVo.getSno());
        	// 첨부 테이블(boardAttt)에서 삭제
        	if(attList.size() > 0) {
        		cnt = mapper.attDeleteAll(bVo.getSno());
        		if(cnt>0) {
        			// 첨부 파일 삭제
        			String[] delList = attList.toArray(new String[0]);
        			System.out.println("delList : "+ delList.toString());
        			fileDelete(delList);
        		} else {
            		b=false;
            	}
        	} 
        }
        
        if(b) manager.commit(status);
        else status.rollbackToSavepoint(savePoint);
		
		return b;
	}
	
	public synchronized boolean replR(BoardVo bVo) {
        boolean b = true;
        status = manager.getTransaction(new DefaultTransactionAttribute());
		savePoint = status.createSavepoint();
		
        mapper.seqUp(bVo);
        int cnt = mapper.repl(bVo);
        if(cnt<1) {
            b=false;
        }else if(bVo.getAttList().size()>0) {
           int attCnt =  mapper.insertAttList(bVo.attList);
           if(attCnt<0) b=false;
        }
       
        if(b) manager.commit(status);
        else  status.rollbackToSavepoint(savePoint);
        return b;
    }
	
	public void fileDelete(String[] delList) {
		String path = FileUploadController.path;
		for(String f : delList) {
			File file = new File(path+f);
			if(file.exists()) file.delete();
		}
	}
	
}
