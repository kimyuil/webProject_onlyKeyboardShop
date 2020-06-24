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

import com.spring.webProject.command.FreeBoardPageCommand;
import com.spring.webProject.command.FreeboardLikeMinusCommand;
import com.spring.webProject.command.FreeboardLikePlusCommand;
import com.spring.webProject.command.DeleteFreeboardCommand;
import com.spring.webProject.command.FreeBoardContentViewCommand;
import com.spring.webProject.command.FreeBoardListCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.InsertFreeboardCommand;
import com.spring.webProject.command.ModifyFreeboardCommand;
import com.spring.webProject.command.ReviewPageCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.PurchaseItemsCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.dto.FreeBoardDto;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.ReviewBoardDto;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerCommunity {
		
	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
	//자유게시판 리스트
	@RequestMapping(value = "/freeboardList", method = RequestMethod.GET)
	public String freeboard(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardList");
		
		String page = request.getParameter("page");
		if (page==null)
			page="1";
		
		command = new FreeBoardListCommand();
		command.execute(sqlSession, model);
		
		command = new FreeBoardPageCommand(Integer.parseInt(page));
		command.execute(sqlSession, model);
		
		return "community/freeboard";
	}
	//freeboardContentView
	@RequestMapping(value = "/freeboardContentView", method = RequestMethod.GET)
	public String freeboardContentView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardContentView");
		
		String fbId = request.getParameter("fbId");
		String page = request.getParameter("page");
		
		model.addAttribute("fbId", fbId);
		model.addAttribute("page", page);
				
		command = new FreeBoardContentViewCommand();
		command.execute(sqlSession, model);
		
		
		return "community/freeboardContentView";
	}
	//freeboardContentView
	@RequestMapping(value = "/member/freeboardWriteView", method = RequestMethod.GET)
	public String freeboardWriteView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardWriteView");
		
		String page = request.getParameter("page");
		model.addAttribute("page", page);	
		
		return "community/freeboardWriteView";
	}
	//freeboardWrite
	@RequestMapping(value = "/freeboardWrite", method = RequestMethod.POST)
	public String freeboardWrite(FreeBoardDto board , Model model) throws Exception {
		System.out.println("freeboardWrite");
		
		command = new InsertFreeboardCommand();
		model.addAttribute("board", board);
		command.execute(sqlSession, model);
		
		return "redirect:freeboardList";
	}
	//freeboard 추천버튼
	@ResponseBody
	@RequestMapping(value = "/freeboardLikePlus", method = RequestMethod.POST)
	public String freeboardLikePlus(HttpServletRequest request, Model model) throws Exception {
		System.out.println("freeboardLikePlus");
		
		model.addAttribute("fbId",request.getParameter("fbId"));
		
		command = new FreeboardLikePlusCommand();
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		if(result=="success")
			return result;
		else
			return null;
	}
	//freeboard 추천 취소버튼
	@ResponseBody
	@RequestMapping(value = "/freeboardLikeMinus", method = RequestMethod.POST)
	public String freeboardLikeMinus(HttpServletRequest request, Model model) throws Exception {
		System.out.println("freeboardLikeMinus");
		
		model.addAttribute("fbId",request.getParameter("fbId"));
		
		command = new FreeboardLikeMinusCommand();
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		if(result=="success")
			return result;
		else
			return null;
	}
	//freeboardModifyView
	@RequestMapping(value = "/member/freeboardModifyView", method = RequestMethod.POST)
	public String freeboardModifyView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardModifyView");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("fbId", request.getParameter("fbId"));
		model.addAttribute("fbTitle", request.getParameter("fbTitle"));
		model.addAttribute("fbContent", request.getParameter("fbContent"));
		return "community/freeboardModifyView";
	}
	//freeboardContentView
	@RequestMapping(value = "/freeboardModify", method = RequestMethod.POST)
	public String freeboardModify(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardModify");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("fbId", request.getParameter("fbId"));
		model.addAttribute("fbTitle", request.getParameter("fbTitle"));
		model.addAttribute("fbContent", request.getParameter("fbContent"));
		
		command = new ModifyFreeboardCommand();
		command.execute(sqlSession, model);
		
		
		return "redirect:freeboardContentView";
	}
	//deleteFreeboard
	@RequestMapping(value = "/deleteFreeboard", method = RequestMethod.POST)
	public String deleteFreeboard(HttpServletRequest request , Model model) throws Exception {
		System.out.println("deleteFreeboard");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("fbId", request.getParameter("fbId"));
		
		command = new DeleteFreeboardCommand();
		command.execute(sqlSession, model);
		
		return "redirect:freeboardList";
	}
	

	
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String notice(Locale locale, Model model) {
		System.out.println("noticeList");
		return "community/notice";
	}//community 지워야함
	
	
	
	
	
}
