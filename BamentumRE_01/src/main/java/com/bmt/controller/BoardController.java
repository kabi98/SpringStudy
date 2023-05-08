package com.bmt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmt.entity.Bmt_post;
import com.bmt.entity.Criteria;
import com.bmt.entity.PageMaker;
import com.bmt.mapper.BoardMapper;


@Controller  // 컨트롤러로 인식을한다.
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list")
	public String list(Criteria cri, Model model) {
		logger.info("BoardController list");
		
		List<Bmt_post> list = mapper.getLists(cri);
		model.addAttribute("list", list);
		
		PageMaker pm=new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(mapper.totalCount(cri));
		logger.info("BoardController list page {}, ", pm.getCri().getPage());
		model.addAttribute("pm", pm); // ${pm.cri.page}
		return "board/list";
	}

	
	

}
