package com.spring.webProject.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.webProject.command.DeleteNoticeCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.InsertNoticeCommand;
import com.spring.webProject.command.ModifyNoticeCommand;
import com.spring.webProject.command.NoticeBoardListCommand;
import com.spring.webProject.command.NoticeBoardPageCommand;
import com.spring.webProject.command.NoticeContentViewCommand;
import com.spring.webProject.dto.NoticeDto;


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
