package kr.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.PageMaker;
import kr.board.mapper.BoardMapper;

@Controller  // 컨트롤러로 인식을한다.
public class BoardController{ // Service(X)->Controller(POJO)
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
   
	@Autowired
	private BoardMapper mapper;
	
	@RequestMapping("/list") // /list---HandlerMapping----->list()
	public String list(Criteria cri, Model model) {
		logger.info(" start {}.", "list");

		// 게시판 3개를 생성하고 List에 저장하기		
		List<Board> list=mapper.getLists(cri);
		// 객체바인딩//         번지(100)--->list
		model.addAttribute("list", list);
		// 페이징 처리에 필요한 부분
		PageMaker pm=new PageMaker();
		pm.setCri(cri);
		pm.setTotalCount(mapper.totalCount(cri));
		model.addAttribute("pm", pm); // ${pm.cri.page}
		
		return "board/list"; // /WEB-INF/views/board/list.jsp : forward
	}
	@GetMapping("/register")
	public String register() {
		
		return "board/register"; // register.jsp
	}
	@PostMapping("/register")
	public String register(Board vo) { // title, content, writer
		mapper.register(vo); 
		// 등록성공 후에는 다시 리스트페이지(/list)로 경로를 변경하기 : redirect
		return "redirect:/list"; // /WEB-INF/views//list.jsp
	}
	@RequestMapping("/get")  // ?num=10&page=10
	public String get(int num, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo=mapper.get(num);
		model.addAttribute("vo", vo);
		//model.addAttribute("cri", cri);
		// 조회수 누적
		mapper.count(num);
		return "board/get"; // get.jsp  -> ${vo.title}
	}
	@RequestMapping("/remove")
	public String remove(int num, Criteria cri, RedirectAttributes rttr) { // ?num=10&page=3
		mapper.remove(num);
		rttr.addAttribute("page", cri.getPage());
		
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/list"; // ?page=3
	}
	@GetMapping("/modify")
	public String modify(int num, Model model, @ModelAttribute("cri") Criteria cri) {  // ?num=10&page=10
		Board vo=mapper.get(num);
		model.addAttribute("vo", vo);
		return "board/modify"; // modify.jsp
	}
	@PostMapping("/modify")
	public String modify(Board vo, RedirectAttributes rttr, Criteria cri) { // num, title, content
		mapper.modify(vo);
		// 수정 성공후에 다시 리스트페이지로 이동(/list)
		// 수정 성공후에 다시 상세보기페이지로 이동(/get?num=10)
		rttr.addAttribute("num", vo.getNum());
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		return "redirect:/get"; //?num=10&page=XXX&search=XXX
	}
	@GetMapping("/reply") // ?num=10
	public String reply(int num, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo=mapper.get(num);//
		model.addAttribute("vo", vo);		
		return "board/reply"; // reply.jsp
	}
	@PostMapping("/reply") // num, username, title, content, writer, bgroup, bseq, blevel, bdelete
	public String reply(Board vo, Criteria cri, RedirectAttributes rttr) {
		// 답글에 필요한 정보 만들기....
		// 1. 부모글(원글)의 정보를 가져오기
		Board parent=mapper.get(vo.getNum());
		// 2. 부모글의 bgroup을 답글의 bgroup에 저장
		vo.setBgroup(parent.getBgroup());
		// 3. 부모글의 bseq에 +1을 해서 답글의 bseq에 저장
		vo.setBseq(parent.getBseq()+1);
		// 4. 부모글의 blevel에 +1을 해서 답글의 blevel에 저장
		vo.setBlevel(parent.getBlevel()+1);
		// 5. 부모의 bgroup과 같고 and 부모의 bseq보다 < 큰 답글의 bseq를 모두 1을 더하는 작업
		mapper.replySeqUpdate(parent);
		// 6. 답글을 저정하기
		mapper.replyInsert(vo);
		rttr.addAttribute("page", cri.getPage());
		
		rttr.addAttribute("type", cri.getType());
		rttr.addAttribute("keyword", cri.getKeyword());
		
		return "redirect:/list";
	}

/*	
	// OpenAPI 개발방법 = IP 제공하면 다른 PC 에서 접근가능
	// http://localhost:8081/mvc04/ajaxlist
	// http://121.179.7.40:8081/mvc04/ajaxlist
	@RequestMapping("/ajaxlist")  //
	public @ResponseBody List<Board> ajaxList() {
		List<Board> list=mapper.ajaxList();
		// @@ResponseBody 가 하는 역할
		// list -> json 변환 (API) - pom.xml 추가 : jackson databind
		// 변환된 json 객체를 응답
		
		return list; 
		// 일반적인 동기 응답 : jsp(forward), Controller(redirect)
	}
*/	
	
	
}




