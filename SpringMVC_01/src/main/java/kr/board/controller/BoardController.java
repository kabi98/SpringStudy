package kr.board.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BoardController {
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request) {
		List<String> list = new ArrayList<String>();
		
		list.add("사과");
		list.add("바나나");
		list.add("오렌지");
		
		request.setAttribute("list", list);
		
		return "flist";
	}

}
