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
import com.spring.webProject.command.CheckCommentPwCommand;
import com.spring.webProject.command.CommentListCommand;
import com.spring.webProject.command.DeleteCommentCommand;
import com.spring.webProject.command.DeleteFreeboardCommand;
import com.spring.webProject.command.DeleteNoticeCommand;
import com.spring.webProject.command.FreeBoardContentViewCommand;
import com.spring.webProject.command.FreeBoardListCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.InsertFreeboardCommand;
import com.spring.webProject.command.InsertNoticeCommand;
import com.spring.webProject.command.ModifyCommentCommand;
import com.spring.webProject.command.ModifyFreeboardCommand;
import com.spring.webProject.command.ModifyNoticeCommand;
import com.spring.webProject.command.NoticeBoardListCommand;
import com.spring.webProject.command.NoticeBoardPageCommand;
import com.spring.webProject.command.NoticeContentViewCommand;
import com.spring.webProject.command.ReviewPageCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.PurchaseItemsCommand;
import com.spring.webProject.command.QNAListCommand;
import com.spring.webProject.command.QNAPageCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.command.WrtieCommentCommand;
import com.spring.webProject.command.WrtieReCommentCommand;
import com.spring.webProject.dto.FreeBoardDto;
import com.spring.webProject.dto.FreeCommentDto;
import com.spring.webProject.dto.NoticeDto;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.QNABoardDto;
import com.spring.webProject.dto.ReviewBoardDto;


/**
 * Handles requests for the application home page.
 */
@Controller
public class ControllerNotice {
		
	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
	
	//notice
	@RequestMapping(value = "/noticeList", method = RequestMethod.GET)
	public String notice(HttpServletRequest request, Model model)  throws Exception {
		System.out.println("noticeList");
		
		String page = request.getParameter("page");
		if (page==null)
			page="1";
		
		command = new NoticeBoardListCommand();
		command.execute(sqlSession, model);
		
		command = new NoticeBoardPageCommand(Integer.parseInt(page));
		command.execute(sqlSession, model);
		
		return "community/notice";
	}
	//noticeContentView 공지사항내용
	@RequestMapping(value = "/noticeContentView", method = RequestMethod.GET)
	public String noticeContentView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("noticeContentView");
		
		String nbId = request.getParameter("nbId");
		String page = request.getParameter("page");
		
		model.addAttribute("nbId", nbId);
		model.addAttribute("page", page);
				
		command = new NoticeContentViewCommand();
		command.execute(sqlSession, model);
		
		return "community/noticeContentView";
	}
	
	//공지사항 글쓰기화면 (admin)
	@RequestMapping(value = "/admin/noticeWriteView", method = RequestMethod.GET)
	public String noticeWriteView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("noticeWriteView (admin)");
	
		String page = request.getParameter("page");
		model.addAttribute("page", page);	
		
		return "community/NoticeWriteView";
	}
	//공지사항 글쓰기 (admin) db입력
	@RequestMapping(value = "/noticeWrite ", method = RequestMethod.POST)
	public String freeboardWrite(NoticeDto notice , Model model) throws Exception {
		System.out.println("freeboardWrite");
		
		command = new InsertNoticeCommand();
		model.addAttribute("notice", notice);
		command.execute(sqlSession, model);
		
		return "redirect:noticeList";
	}
	//noticeModifyView 수정 view
	@RequestMapping(value = "/admin/noticeModifyView", method = RequestMethod.POST)
	public String noticeModifyView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("noticeModifyView");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("nbId", request.getParameter("nbId"));
		model.addAttribute("nbTitle", request.getParameter("nbTitle"));
		model.addAttribute("nbContent", request.getParameter("nbContent"));
		return "community/NoticeModifyView";
	}
	// 자유게시글 수정
	@RequestMapping(value = "/modifyNotice", method = RequestMethod.POST)
	public String modifyNotice(HttpServletRequest request , Model model) throws Exception {
		System.out.println("modifyNotice");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("nbId", request.getParameter("nbId"));
		model.addAttribute("nbTitle", request.getParameter("nbTitle"));
		model.addAttribute("nbContent", request.getParameter("nbContent"));
		
		command = new ModifyNoticeCommand();
		command.execute(sqlSession, model);
		
		return "redirect:noticeContentView";
	}	
	@RequestMapping(value = "/deleteNotice", method = RequestMethod.POST)
	public String deleteNotice(HttpServletRequest request , Model model) throws Exception {
		System.out.println("deleteFreeboard");
		
		String page = request.getParameter("page");
		model.addAttribute("nbId", request.getParameter("nbId"));
		
		command = new DeleteNoticeCommand();
		command.execute(sqlSession, model);

		return "redirect:noticeList?page="+page;
	}
}
