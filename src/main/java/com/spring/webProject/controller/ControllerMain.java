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


/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerMain {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerMain.class);
	
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
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	public String about(Locale locale, Model model) {
		System.out.println("about");
		return "brand/about";
	}
	@RequestMapping(value = "/location", method = RequestMethod.GET)
	public String location(Locale locale, Model model) {
		System.out.println("location");
		return "brand/location";
	}
	
	
	@RequestMapping(value = "/88keyboard", method = RequestMethod.GET)
	public String keyboard88(Locale locale, Model model) {
		System.out.println("88keyboard");
		model.addAttribute("category", "88keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/88keyboard/88category";
	}
	@RequestMapping(value = "/76keyboard", method = RequestMethod.GET)
	public String keyboard76(Locale locale, Model model) {
		System.out.println("76keyboard");
		
		model.addAttribute("category", "76keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/76keyboard/76category";
	}
	@RequestMapping(value = "/61keyboard", method = RequestMethod.GET)
	public String keyboard61(Locale locale, Model model) {
		System.out.println("61keyboard");
		
		model.addAttribute("category", "61keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/61keyboard/61category";
	}
		
	
	@RequestMapping(value = "/productPage", method = RequestMethod.GET)
	public String productPage(HttpServletRequest request, Model model) {
		
		System.out.println("productPage");
		String category = request.getParameter("category");
		String pId = request.getParameter("pId");
		
		model.addAttribute("pId", pId); //pid 전달
		command = new ProductPageCommand();
		command.execute(sqlSession, model);
		
		
		System.out.println(model.toString());
		
		
		if (category.equals("61keyboard"))
			return "product/61keyboard/61product";
		else if (category.equals("76keyboard"))
			return "product/76keyboard/76product";
		else
			return "product/88keyboard/88product";
	}
	
	@RequestMapping(value = "/buyPage", method = RequestMethod.POST)
	public String buyPage(HttpServletRequest request, Model model) throws UnsupportedEncodingException {
		System.out.println("buy page");
		
		command = new BuyPageCommand();
		
		return "home";
	}
	
	
	
	
	
	@RequestMapping(value = "/freeboard", method = RequestMethod.GET)
	public String freeboard(Locale locale, Model model) {
		System.out.println("freeboard");
		return "community/freeboard";
	}
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String notice(Locale locale, Model model) {
		System.out.println("notice");
		return "community/notice";
	}
	
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		System.out.println("login");
		return "membership/login";
	}
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Locale locale, Model model) {
		System.out.println("mypage");
		return "membership/mypage";
	}
	
}
