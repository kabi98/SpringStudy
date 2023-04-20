package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController { // Service(X)->Controller(POJO)
	
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list")
	public String list(Model model) {
		
		List<Board> list = mapper.getLists();
		
		//객체 바인딩
		model.addAttribute("list", list);
		
		return "board/list"; // /WEB/views/board/list.jsp : forward
//		return "list";
	}
	
	@GetMapping("/register")
	public String register() {
		
		return "board/register"; 
	}
	
	@PostMapping("/register")
	public String register(Board vo) { // title, content, writer
		mapper.register(vo);
		// 등록 성공후에는 다시 리스트 페이지로(/list) 로 경로 변경하기.
		
		return "redirect:/list"; 
	}
	
	@RequestMapping("/get")  // query string "?num=9"
	public String get(int num, Model model) {
		Board vo = mapper.get(num);
		 
		model.addAttribute("vo", vo);

		return "board/get"; // get.jsp -> ${vo.title}
	}

}
