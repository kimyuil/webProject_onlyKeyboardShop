package com.spring.webProject.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.ChangePurchaseStateCommand;
import com.spring.webProject.command.DeleteUserReviewCommand;
import com.spring.webProject.command.FindIdCommand;
import com.spring.webProject.command.FindPwCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.JoinCommand;
import com.spring.webProject.command.LoginCommand;
import com.spring.webProject.command.ModifyReivewCommand;
import com.spring.webProject.command.OrderListCommand;
import com.spring.webProject.command.ReviewPageCommand;
import com.spring.webProject.command.PreventReWriteCheckCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.RenewPwCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.command.UserCheckCommand;
import com.spring.webProject.command.UserCheckDeliveryCommand;
import com.spring.webProject.command.UserReviewListCommand;
import com.spring.webProject.command.WriteReivewCommand;
import com.spring.webProject.command.InsertReviewDataCommand;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.ReviewBoardDto;
import com.spring.webProject.dto.UserDto;


@Controller
public class ControllerMembership {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
	///test
	@RequestMapping(value = "/test", method = RequestMethod.POST)
	public String test(HttpServletRequest request, Model model)throws Exception {
		System.out.println("test 실행");
		
		System.out.println(request.getParameter("param1"));
		//mainDao=new TestDao();
		return "home";
	}
	
	//id찾기
	@RequestMapping(value = "/findIdView", method = RequestMethod.GET)
	public String findIdView(Locale locale, Model model) {
		System.out.println("findIdView");
		
		return "membership/findId";
	}
	@RequestMapping(value = "/findId", method = RequestMethod.POST)
	public String findId(HttpServletRequest request, Model model)throws Exception {
		System.out.println("findId");
		command = new FindIdCommand();
		model.addAttribute("email",request.getParameter("email") );
		model.addAttribute("name",request.getParameter("name") );
		
		command.execute(sqlSession, model);
		
		return "membership/findResultId";
	}
	
	//pw찾기
	@RequestMapping(value = "/findPwView", method = RequestMethod.GET)
	public String findPwView(Locale locale, Model model) {
		System.out.println("findPwView");
		
		return "membership/findPw";
	}
	@RequestMapping(value = "/findPw", method = RequestMethod.POST)
	public String findPw(HttpServletRequest request, Model model)throws Exception {
		System.out.println("findPw");
		
		command = new FindPwCommand();
		model.addAttribute("email",request.getParameter("email") );
		model.addAttribute("id",request.getParameter("id") );
		
		command.execute(sqlSession, model);
		
		return "membership/findResultPw";
	}
	@RequestMapping(value = "/renewPw", method = RequestMethod.POST)
	public String renewPw(HttpServletRequest request, Model model)throws Exception {
		System.out.println("findPw");
		
		command = new RenewPwCommand();
		model.addAttribute("pw",request.getParameter("uPw") );
		model.addAttribute("id",request.getParameter("id") );//미정
				
		command.execute(sqlSession, model);
		
		return "membership/login";//미정
	}
	
	//로그아웃
	@RequestMapping(value = "/member/home", method = RequestMethod.GET)
	public String logout(Locale locale, Model model) {
		System.out.println("logout");
		return "home";
	}
	@RequestMapping(value = "/admin/home", method = RequestMethod.GET)
	public String logout2(Locale locale, Model model) {
		System.out.println("logout2");
		return "home";
	}
	
	
	//회원가입
	@RequestMapping(value = "/joinView", method = RequestMethod.GET)
	public String joinView(Locale locale, Model model) {
		System.out.println("joinView");
		
		return "membership/join";
	}
	
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String join(UserDto user, Model model) throws Exception {
		System.out.println("join");
		command = new JoinCommand();
		model.addAttribute("user",user);
		command.execute(sqlSession, model);

		return "redirect:login";
	}
	
	@ResponseBody  //ajax 회원가입시 아이디중복체크
	@RequestMapping(value = "/idCheck", method = RequestMethod.POST)
	public String join(HttpServletRequest request, Model model)throws Exception {
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
		
	//권한 잘못 들어왔을때
	@RequestMapping(value = "/errorPage", method = RequestMethod.GET)
	public String errorPageView(Locale locale, Model model) {
		System.out.println("errorPageView");
		
		return "membership/errorPage";
	}
	

}
