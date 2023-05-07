package com.bmt.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bmt.entity.Bmt_user;
import com.bmt.mapper.BoardMapper;

@Controller  // 컨트롤러로 인식을한다.
public class LoginController {
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired // 외부에 만들어진 객체를 주입하여 연결하는 방법(의존성 주입 : DI)
	private BoardMapper mapper;

	@RequestMapping("/login") // username, password -> User(X), Member(O)
	public String login(Bmt_user mvo, HttpSession session) {
		logger.info("LoginController list {}", mvo);
		logger.info("LoginController list email:{}, pw:{}", mvo.getEmail(), mvo.getPw());
		Bmt_user vo = mapper.login(mvo);
		if (vo != null) { // 회원인증에 성공
			session.setAttribute("mvo", vo);
		}
		return "redirect:/list";
	}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션을 무효화
		return "redirect:/list";
	}

}
