package com.spring.bloom.board;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class FileUploadController {
	// 폴더명 우클릭 -> 속성에 들어가면 나오는 파일경로
	static String path = "/Users/ovoin/eclipse-workspace/SpringSpaProject/src/main/resources/static/upload/";
	
	@Autowired
	BoardService service;
	
	@Autowired
	BoardController controller;
	
	@RequestMapping("/board/board_insertR")
	public synchronized String insertR(@RequestParam("attFile") List<MultipartFile> mul,
									   @ModelAttribute BoardVo vo,
									   @ModelAttribute BPageVo pVo) { 
		// 파일태그는 RequestParam으로 받고 나머지는 ModelAttribute로 받음
		
		String msg = "";
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			
			// 본문을 저장
			
			
			// --- 
			attList = fileUpload(mul);
			vo.setAttList(attList);
			
			boolean flag = service.insertR(vo);
			if(!flag) msg =  "저장 중 오류 발생!";
			
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@RequestMapping("/board/board_updateR")
	public synchronized ModelAndView update(@RequestParam("attFile") List<MultipartFile> mul,
									  @RequestParam(name="delFile",required = false) String[] delFiles,
						              @ModelAttribute BoardVo bVo, 
						              @ModelAttribute BPageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		try {
			List<AttVo> attList = fileUpload(mul);
			bVo.setAttList(attList);
			boolean flag = service.updateR(bVo,delFiles);
			if( !flag ) msg = "수정 중 오류 발생!";
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("msg", msg);
		mv = controller.select(pVo);
		
		return mv;
	}
	
	@RequestMapping("/board/board_replR")
	public synchronized ModelAndView replR(@RequestParam("attFile") List<MultipartFile> mul,
			             			 @ModelAttribute BoardVo bVo, 
			             			 @ModelAttribute BPageVo pVo) {
		String msg = "";
		ModelAndView mv = new ModelAndView();
		try {
			List<AttVo> attList = new ArrayList<AttVo>();
			attList = fileUpload(mul);
			bVo.setAttList(attList);
			
			
			boolean flag = service.replR(bVo);
			if( !flag ) msg = "댓글등록 중 오류 발생!";
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		mv.addObject("msg", msg);
		mv = controller.select(pVo);
		
		return mv;
	}
	
	// 파일을 업로드하는 메서드(insertR,updateR,replR)
	public List<AttVo> fileUpload(List<MultipartFile> mul) throws Exception{
		List<AttVo> attList = new ArrayList<AttVo>();
		
		for(MultipartFile m : mul) {
			if(m.isEmpty()) continue;
			
			UUID uuid = UUID.randomUUID();
			String oriFile = m.getOriginalFilename();
			String sysFile = "";
			
			File temp = new File(path + oriFile);
			m.transferTo(temp);
			sysFile = (uuid.getLeastSignificantBits()*-1) + "-" + oriFile;
			File f = new File(path + sysFile);
			temp.renameTo(f);
			
			AttVo attVo = new AttVo();
			attVo.setOriFile(oriFile);
			attVo.setSysFile(sysFile);
			
			attList.add(attVo);
			
			System.out.println(m.getOriginalFilename());
			System.out.println(uuid.getLeastSignificantBits());
		}
		
		return attList;
	}
}
