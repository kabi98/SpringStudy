package kr.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController { // Service(X)->Controller(POJO)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list")
	public String list(Model model) {
		logger.info("BoardController list");
		
		List<Board> list = mapper.getLists();
		
		//객체 바인딩
		model.addAttribute("list", list);
		
		return "board/list"; // /WEB/views/board/list.jsp : forward
//		return "list";
	}
	
	@GetMapping("/register")
	public String register() {
		logger.info("BoardController register");
		
		return "board/register"; 
	}
	
	@PostMapping("/register")
	public String register(Board vo) { // title, content, writer
		logger.info(String.format("BoardController register %s %s %s", vo.getTitle(), vo.getContent(), vo.getWriter()) );
		mapper.register(vo);
		// 등록 성공후에는 다시 리스트 페이지로(/list) 로 경로 변경하기.
		
		return "redirect:/list"; 
	}
	
	@RequestMapping("/get")  // query string "?num=9"
	public String get(int num, Model model) {
		Board vo = mapper.get(num);
		 
		model.addAttribute("vo", vo);
		
		// 조회수 누적
		mapper.count(num);

		return "board/get"; // get.jsp -> ${vo.title}
	}

	@RequestMapping("/remove")
	public String remove(int num, Model model) {
		mapper.remove(num);
		return "redirect:/list";
	}
	
	@GetMapping("/modify")
	public String modify(int num, Model model) {
		Board vo = mapper.get(num);
		model.addAttribute("vo", vo);
		return "board/modify"; // modify.jsp
	}

	@PostMapping("/modify")
	public String modify(Board vo, RedirectAttributes rttr) {
		logger.info(String.format("BoardController modify %s %s %s", vo.getTitle(), vo.getContent(), vo.getWriter()) );
		mapper.modify(vo);
		// 수정 성공후에 다시 리스트 페이지로 이동. (/list)
		// 수정 성공후에 다시 상세보기 페이지로 이동. (/get?num=10)
		//return "redirect:/list"; // modify.jsp
		//return "redirect:/get?num=" + String.valueOf(vo.getNum());   // modify.jsp
		// return "redirect:/get?num=" + vo.getNum();   // modify.jsp
		rttr.addAttribute("num", vo.getNum()); // ?num=10&page=XXXX&search=XXX
		return "redirect:/get";    
	}
	
}
