package kr.board.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.PageMaker;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController { // Service(X)->Controller(POJO)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list")
	public String list(Criteria cri, Model model) {
		logger.info("BoardController list");
		
		List<Board> list = mapper.getLists(cri);
		
		//객체 바인딩
		model.addAttribute("list", list);
		// 페이징 처리에 필요한 부분
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(mapper.totalCount());
		
		model.addAttribute("pm", pm);
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
	public String get(int num, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = mapper.get(num);
		 
		model.addAttribute("vo", vo);
		
		// 조회수 누적
		mapper.count(num);

		return "board/get"; // get.jsp -> ${vo.title}
	}

	@RequestMapping("/remove")
	public String remove(int num, Criteria cri, RedirectAttributes rttr) {
		mapper.remove(num);
		rttr.addAttribute("page", cri.getPage());
		return "redirect:/list";
	}
	
	@GetMapping("/modify")
	public String modify(int num, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = mapper.get(num);
		model.addAttribute("vo", vo);
		return "board/modify"; // modify.jsp
	}

	@PostMapping("/modify")
	public String modify(Board vo, RedirectAttributes rttr, Criteria cri) {
		logger.info(String.format("BoardController modify %s %s %s", vo.getTitle(), vo.getContent(), vo.getWriter()) );
		mapper.modify(vo);
		// 수정 성공후에 다시 리스트 페이지로 이동. (/list)
		// 수정 성공후에 다시 상세보기 페이지로 이동. (/get?num=10)
		//return "redirect:/list"; // modify.jsp
		//return "redirect:/get?num=" + String.valueOf(vo.getNum());   // modify.jsp
		// return "redirect:/get?num=" + vo.getNum();   // modify.jsp
		rttr.addAttribute("num", vo.getNum()); // ?num=10&page=XXXX&search=XXX
		rttr.addAttribute("page", cri.getPage()); // ?num=10&page=XXXX&search=XXX
		return "redirect:/get";    
	}

	@GetMapping("/reply") // ?num=10
	public String reply(int num, Model model, @ModelAttribute("cri") Criteria cri) {
		logger.info(String.format("BoardController reply ") );
		Board vo = mapper.get(num);
		model.addAttribute("vo", vo);
		return "board/reply"; // modify.jsp
	}

	// 5개 받음 부모글 - num, 답글 - username, title, content, write
	// 만들어 줘야함. - bgroup, bseq, blevel, bdelete
	@PostMapping("/reply") 
	public String reply(Board vo, Criteria cri, RedirectAttributes rttr) {
		logger.info(String.format("BoardController reply ") );
		// 답글에 필요한 정보 만들기..
		// 1. 부모글(원글)의 정보를 가져오기.
		Board parent = mapper.get(vo.getNum());
		
		// 2. 부모글의 bgroup을 답글의 bgroup으로 저장.
		vo.setBgroup( parent.getBgroup() );
		// 3. 부모글의 bseq을 답글의 bseq로 저장.
		vo.setBseq( parent.getBseq() + 1);
		// 4. 부모글의 blevel을 답글의 blevel로 저장.
		vo.setBlevel( parent.getBlevel() + 1);
		// 5. 부모의 bgroup과 같고 and 부모의 bseq 보다 큰 답글의 
		// 		bseq를 모두 1을 더하는 작업
		mapper.replySeqUpdate(parent);
		// 6. 답글을 저장하기.
		mapper.replyInsert(vo);
		
		rttr.addAttribute("page", cri.getPage());
		
		return "redirect:/list"; // modify.jsp
	}
}
