package com.spring.webProject.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.webProject.command.TestCommand;
import com.spring.webProject.dao.IDao;
import com.spring.webProject.dao.TestDao;

/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerMain {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerMain.class);
	
	TestCommand tc;
	
	@Autowired
	private SqlSession sqlSession;
	
	///test
		@RequestMapping(value = "/test", method = RequestMethod.GET)
		public String test(Locale locale, Model model) {
			tc=new TestCommand();
			tc.dodo(sqlSession);
			
			//mainDao=new TestDao();
			return "home";
		}
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		//model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	///menubar_top
	@RequestMapping(value = "/menubar_top", method = RequestMethod.GET)
	public String menubar_top(Locale locale, Model model) {
		return "menubar_top";
	}
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public String main(Locale locale, Model model) {		
		return "/main";
	}
	@RequestMapping(value = "/infobar_bottom", method = RequestMethod.GET)
	public String infobar_bottom(Locale locale, Model model) {	
		return "infobar_bottom";
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
		return "product/88keyboard/88category";
	}
	@RequestMapping(value = "/76keyboard", method = RequestMethod.GET)
	public String keyboard76(Locale locale, Model model) {
		System.out.println("76keyboard");
		return "product/76keyboard/76category";
	}
	@RequestMapping(value = "/61keyboard", method = RequestMethod.GET)
	public String keyboard61(Locale locale, Model model) {
		System.out.println("61keyboard");
		return "product/61keyboard/61category";
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
