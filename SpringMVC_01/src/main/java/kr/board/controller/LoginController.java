package kr.board.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Member;
import kr.board.mapper.BoardMapper;

@Controller
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

//	@Inject
	@Autowired // 외부에서 만들어진 객체를 주입하여 연결하는 방법(의존성 주입 : DI) Dependecy Inject
	private BoardMapper mapper;
	
	// username, password -> 이미 스프링 시큐리티에서 정의 되어 있음. 클래스 User(X), 
	// 클래스 Member(O)
	
	@RequestMapping("/login") 
	public String login(Member mvo, HttpSession session) {
		logger.info("LoginController login {}", mvo.getName());
		Member vo = mapper.login(mvo);
		if(vo != null) { // 회원인증에 성공
			// 모든 jsp 에서 인증여부 확인을 위해서
			// model(request)에 넣지 않고 session 에 넣는다.
			session.setAttribute("mvo", vo);
		}
		return "redirect:/list";
	}
	
	@RequestMapping("/logout") 
	public String logout(HttpSession session) {
		logger.info("LoginController logout");
		session.invalidate();
		return "redirect:/list";
	}

}
