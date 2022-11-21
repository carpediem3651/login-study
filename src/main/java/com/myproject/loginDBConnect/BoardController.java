package com.myproject.loginDBConnect;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board")
public class BoardController {
	@GetMapping("/list")
	public String list(HttpServletRequest request) {
		if(!loginCheck(request)) //로그인했니??
			return "redirect:/login/login?toURL="+request.getRequestURL(); //로그인을 안했으면 로그인 화면으로 되돌아간다, 게시판의 주소를 알려주기 위해 getRequestURL()을 가져온다.
		return "boardList"; // 로그인 한 상태이면, 게시판으로 이동한다.
	}

	private boolean loginCheck(HttpServletRequest request) {
//		1. 세션을 얻는다.
		HttpSession session = request.getSession();
//		2. 세션에 id가 있는지 확인한다. 있으면 true, 없으면 false 반환
		if(session.getAttribute("id")!=null)
			return true;
		else
			return false;
	}
}
