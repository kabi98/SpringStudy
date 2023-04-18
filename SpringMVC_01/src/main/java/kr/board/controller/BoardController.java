package kr.board.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.board.mapper.BoardDAO;

@Controller
public class BoardController { // Service(X)->Controller(POJO)
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		
		BoardDAO dao = new BoardDAO();
		List<Board> list = dao.getLists();
		
		//객체 바인딩
		request.setAttribute("list", list);
		
		return "board/list";
//		return "list";
	}

}
