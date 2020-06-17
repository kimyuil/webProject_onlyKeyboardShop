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

import com.spring.webProject.command.AddBasketCommand;
import com.spring.webProject.command.AddBookmarkCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.PageCommand;
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
	
	
	//product 관련 요청들
	
	@RequestMapping(value = "/88keyboard", method = RequestMethod.GET)
	public String keyboard88(Locale locale, Model model) throws Exception {
		System.out.println("88keyboard");
		model.addAttribute("category", "88keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/88keyboard/88category";
	}
	@RequestMapping(value = "/76keyboard", method = RequestMethod.GET)
	public String keyboard76(Locale locale, Model model) throws Exception {
		System.out.println("76keyboard");
		
		model.addAttribute("category", "76keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/76keyboard/76category";
	}
	@RequestMapping(value = "/61keyboard", method = RequestMethod.GET)
	public String keyboard61(Locale locale, Model model) throws Exception {
		System.out.println("61keyboard");
		
		model.addAttribute("category", "61keyboard");
		command = new ProductCommand();
		command.execute(sqlSession, model);
		System.out.println(model.toString());
		
		return "product/61keyboard/61category";
	}
		
	
	@RequestMapping(value = "/productPage", method = RequestMethod.GET)
	public String productPage(HttpServletRequest request, Model model) throws Exception {
		
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
	
	@ResponseBody  //ajax
	@RequestMapping(value = "/addBookmark", method = RequestMethod.POST)
	public String addBookmark(HttpServletRequest request, Model model)throws Exception {
		System.out.println("addBookmark");
		
		command = new AddBookmarkCommand();
		
		String uId = request.getParameter("uId");
		String pId = request.getParameter("pId");
		model.addAttribute("uId", uId);
		model.addAttribute("pId", pId);
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String) map.get("result");
		
		return result;
	}
	/*
	 * @ResponseBody //ajax
	 * 
	 * @RequestMapping(value = "/addBasket", method = RequestMethod.POST) public
	 * String addBasket(HttpServletRequest request, Model model) {
	 * System.out.println("addBasket");
	 * 
	 * command = new AddBasketCommand();
	 * 
	 * String uId = request.getParameter("uId"); String pId =
	 * request.getParameter("pId"); model.addAttribute("uId", uId);
	 * model.addAttribute("pId", pId); command.execute(sqlSession, model);
	 * 
	 * Map<String, Object> map = model.asMap(); String result = (String)
	 * map.get("result");
	 * 
	 * return result; }
	 */
	
	//구매페이지이동 (로그인필요) post(상품페이지에서)
	@RequestMapping(value = "member/buyPage", method = RequestMethod.POST)
	public String buyPost(HttpServletRequest request, Model model) {
		System.out.println("buy POST");
		return "product/buyPage";
	}
	//구매페이지이동 (로그인필요) get (장바구니에서 올때)
	@RequestMapping(value = "member/buyPage", method = RequestMethod.GET)
	public String buyGet(HttpServletRequest request, Model model) {
		System.out.println("buy GET");
		return "product/buyPage";
	}
	
	
	//구매페이지 (로그인필요) post(상품페이지에서)
	@Transactional
	@RequestMapping(value = "member/buyAction", method = RequestMethod.POST)
	public String buyAction(HttpServletRequest request, Model model) throws RuntimeException {
		System.out.println("buyAction test page");
		
		command = new PurchaseItemsCommand();
 
		model.addAttribute("uId", request.getParameter("uId"));
		model.addAttribute("uName", request.getParameter("uName"));
		model.addAttribute("uAdress", request.getParameter("uAdress"));
		model.addAttribute("uPhone", request.getParameter("uPhone"));
		model.addAttribute("deliverMessage", request.getParameter("deliverMessage"));
		model.addAttribute("pId", request.getParameter("pId"));
		model.addAttribute("pName", request.getParameter("pName"));
		model.addAttribute("pColor", request.getParameter("pColor"));
		model.addAttribute("pImage", request.getParameter("pImage"));
		model.addAttribute("pNumof", request.getParameter("pNumof"));
		
		try {
		command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}
		
		
		Map<String, Object> map = model.asMap();
		String error = (String) map.get("error");
		
		if(error!=null) // 에러가 있으면
			return "home";
		else
			return "product/buySuccess";
	}
	
	  //ajax 후기 게시판 정보전송
	@RequestMapping(value = "/reviewList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> reviewList(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("reviewList");
		
		command = new ReviewListCommand();
		
		String pId = request.getParameter("pId");
		model.addAttribute("pId", pId);
		System.out.println(pId);
		command.execute(sqlSession, model); //게시판 리스트를 받아옴
		
		
		String page = request.getParameter("reviewPage"); //페이지정보
		command = new PageCommand(Integer.parseInt(page));
		command.execute(sqlSession, model);
				
		
		Map<String, Object> map = model.asMap();
				
		ArrayList<ReviewBoardDto> reviews = (ArrayList<ReviewBoardDto>)map.get("reviews");//게시판리스트들
		PageDto pageInfo = (PageDto)map.get("pageInfo");//페이징정보
		
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("reviews", reviews);
		result.put("pageInfo", pageInfo);
		
		return result;
		
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
	
	
	
}
