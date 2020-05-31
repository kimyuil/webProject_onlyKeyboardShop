package com.spring.webProject.controller;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.BuyPageCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.JoinCommand;
import com.spring.webProject.command.LoginCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.dto.UserDto;


@Controller
public class ControllerMembership {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
	///test
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(Locale locale, Model model) {
		command = new TestCommand();
		command.execute(sqlSession,model);
		System.out.println("test 실행");
		//mainDao=new TestDao();
		return "home";
	}
	
	
	@RequestMapping(value = "/findMembership", method = RequestMethod.GET)
	public String findMembership(Locale locale, Model model) {
		System.out.println("findMembership");
		
		return "membership/findIdPw";
	}
	
	@RequestMapping(value = "/joinView", method = RequestMethod.GET)
	public String joinView(Locale locale, Model model) {
		System.out.println("joinView");
		
		return "membership/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserDto user, Model model) {
		System.out.println("join");
		command = new JoinCommand();
		model.addAttribute("user",user);
		command.execute(sqlSession, model);

		return "redirect:login";
	}
	
	@ResponseBody  //ajax
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String join(HttpServletRequest request, Model model) {
		System.out.println("inCheck");
		
		command = new IdCheckCommand();
		
		String id = request.getParameter("uId");
		model.addAttribute("id", id);
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		Integer result = (Integer) map.get("result");
		
		if(result == 1)
			return "success";
		else
			return "fail";

	}
	
//	@ResponseBody  //ajax
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	public String loginAction(HttpServletRequest request, Model model) {
		System.out.println("loginAction");
//		command = new LoginCommand();
//		
//		model.addAttribute("uId",request.getParameter("uId"));
//		model.addAttribute("uPw",request.getParameter("uPw"));
//		command.execute(sqlSession, model);
//		
//		Map<String, Object> map = model.asMap();
//		String uName = (String)map.get("uName");
//		
//		if(uName==null) //로그인 결과가 없을때
//			return "fail";
//		else
//			return "success";
		return "home";
				
	}
	
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public String errorPageView(Locale locale, Model model) {
		System.out.println("errorPageView");
		
		return "membership/errorPage";
	}

	@RequestMapping(value = "/member/test", method = RequestMethod.GET)
	public String memberTest(Model model) {
		System.out.println("memberTest");
		
		return "membership/memberTest";
	}

	@RequestMapping(value = "/admin/test", method = RequestMethod.GET)
	public String adminTest(Model model) {
		System.out.println("adminTest");
		
		return "membership/adminTest";
	}
	
}
