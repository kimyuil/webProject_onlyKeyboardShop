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
import com.spring.webProject.command.PageCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.PurchaseItemsCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.command.UserCheckCommand;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.ReviewBoardDto;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerProduct {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerProduct.class);
	
	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	

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
	
	@ResponseBody  //ajax 구매할때 본인확인하는 작업
	@RequestMapping(value = "/userCheck", method = RequestMethod.POST)
	public String userCheck(HttpServletRequest request, Model model)throws Exception {
		System.out.println("userCheck");
		
		command = new UserCheckCommand();
		
		String id = request.getParameter("uId");
		model.addAttribute("id", id);
		String pw = request.getParameter("uPw");
		model.addAttribute("pw", pw);
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		Integer result = (Integer) map.get("result");
		
		if(result == 1) {
			System.out.println("success");
			return "success";
		}
		else {
			System.out.println("fail");
			return "fail";
		}

	}

	
	
}
