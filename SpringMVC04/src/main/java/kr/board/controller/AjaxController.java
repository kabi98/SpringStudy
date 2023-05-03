package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@RestController
public class AjaxController {
	
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/ajaxlist")  //
	public List<Board> ajaxList() {
		List<Board> list=mapper.ajaxList();
		
		return list; 
	}

}
