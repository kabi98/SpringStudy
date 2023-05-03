package kr.book.bshop;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Board;
import kr.book.entity.Book;
import kr.book.mapper.BookMapper;

// 1. servlet-context.xml 설정 확인
// 	 D:\Git\SpringStudy\SpringTEST\src\main\webapp\WEB-INF\spring\appServlet
// 2. root-context.xml 설정 확인.
//   D:\Git\SpringStudy\SpringTEST\src\main\webapp\WEB-INF\spring

@Controller
public class BookController {
	@Autowired
	private BookMapper mapper;
	
	@RequestMapping("/bookList.do")
	public String list(Model model) {
		
		List<Book> list = mapper.bookList();
		//객체 바인딩
		model.addAttribute("list", list);
		
		return "bookList"; // /WEB/views/board/list.jsp : forward
	}
	

}
