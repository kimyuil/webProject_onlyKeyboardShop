package com.spring.webProject.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.ReviewPageCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.PurchaseItemsCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.ReviewBoardDto;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerMain {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerMain.class);
	
	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		return "home";
	}
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home2(Locale locale, Model model) {
		
		return "home";
	}
	
	//brand
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
	
	
	//product
	
	@RequestMapping(value = "/88keyboard", method = RequestMethod.GET)
	public String keyboard88(Locale locale, Model model) throws Exception {
		System.out.println("88keyboard");
		model.addAttribute("category", "88keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
				
		return "product/keyboard/category";
	}
	@RequestMapping(value = "/76keyboard", method = RequestMethod.GET)
	public String keyboard76(Locale locale, Model model) throws Exception {
		System.out.println("76keyboard");
		
		model.addAttribute("category", "76keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		
		return "product/keyboard/category";
	}
	@RequestMapping(value = "/61keyboard", method = RequestMethod.GET)
	public String keyboard61(Locale locale, Model model) throws Exception {
		System.out.println("61keyboard");
		
		model.addAttribute("category", "61keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
				
		return "product/keyboard/category";
	}
		
	
	@RequestMapping(value = "/productPage", method = RequestMethod.GET)
	public String productPage(HttpServletRequest request, Model model) throws Exception {
		
		System.out.println("productPage");
		String pId = request.getParameter("pId");
		
		model.addAttribute("pId", pId); //pid РќДо
		command = new ProductPageCommand();
		command.execute(sqlSession, model);
		
		return "product/keyboard/product";
		
	}
		
	
	//community
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
	
	//membership
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		System.out.println("login");
		return "membership/login";
	}
	@RequestMapping(value = "/mypage", method = RequestMethod.GET)
	public String mypage(Locale locale, Model model) {
		System.out.println("mypage");
		return "membership/mypage/mypage";
	}
	
	
	
}
