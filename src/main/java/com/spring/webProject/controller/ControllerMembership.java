package com.spring.webProject.controller;
import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.webProject.command.BuyPageCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.TestCommand;


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
		System.out.println("test ½ÇÇà");
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
}
