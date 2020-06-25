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
	//freeboardContentView 게시글화면 
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
	//freeboardContentView 게시글쓰기 화면
	@RequestMapping(value = "/member/freeboardWriteView", method = RequestMethod.GET)
	public String freeboardWriteView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardWriteView");
		
		String page = request.getParameter("page");
		model.addAttribute("page", page);	
		
		return "community/freeboardWriteView";
	}
	//freeboardWrite 글쓰기 db입력
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
	//freeboardModifyView 수정 view
	@RequestMapping(value = "/member/freeboardModifyView", method = RequestMethod.POST)
	public String freeboardModifyView(HttpServletRequest request , Model model) throws Exception {
		System.out.println("freeboardModifyView");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("fbId", request.getParameter("fbId"));
		model.addAttribute("fbTitle", request.getParameter("fbTitle"));
		model.addAttribute("fbContent", request.getParameter("fbContent"));
		return "community/freeboardModifyView";
	}
	// 자유게시글 수정
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
	@Transactional
	@RequestMapping(value = "/deleteFreeboard", method = RequestMethod.POST)
	public String deleteFreeboard(HttpServletRequest request , Model model) throws RuntimeException {
		System.out.println("deleteFreeboard");
		
		model.addAttribute("page", request.getParameter("page"));
		model.addAttribute("fbId", request.getParameter("fbId"));
		
		command = new DeleteFreeboardCommand();
		try {
			command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}
		
		return "redirect:freeboardList";
	}
	
	// comment~~~~~~~~~~~~~~~
	
	//ajax로 댓글정보 전송
	@RequestMapping(value = "/commentList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> commentList(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("commentList");
		
		String fbId = request.getParameter("fbId");
		model.addAttribute("fbId", fbId);

		command = new CommentListCommand();
		command.execute(sqlSession, model);//게시판 리스트를 받아옴
						
		
		Map<String, Object> map = model.asMap();

		
		ArrayList<FreeCommentDto> comments = (ArrayList<FreeCommentDto>)map.get("comments");
		ArrayList<FreeCommentDto> recomments = (ArrayList<FreeCommentDto>)map.get("recomments");
		
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("comments", comments);
		result.put("recomments", recomments);
		
		return result;
	}
		
	//ajax comment wirte
	@Transactional
	@ResponseBody
	@RequestMapping(value = "/writeComment", method = RequestMethod.POST)
	public String writeComment(HttpServletRequest request, Model model) throws RuntimeException {
		System.out.println("writeComment");
				
		model.addAttribute("fbId",request.getParameter("fbId"));
		model.addAttribute("cName",request.getParameter("cName"));
		model.addAttribute("cPw",request.getParameter("cPw"));
		model.addAttribute("cComment",request.getParameter("cComment"));
		
		command = new WrtieCommentCommand();
		try {
			command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}  
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		return result;
		
	}
	//ajax comment wirte 대댓글
	@ResponseBody
	@RequestMapping(value = "/writeReComment", method = RequestMethod.POST)
	public String writeReComment(HttpServletRequest request, Model model) throws Exception {
		System.out.println("write ReComment");
				
		model.addAttribute("fbId",request.getParameter("fbId"));
		model.addAttribute("cParentId",request.getParameter("cParentId"));
		model.addAttribute("cName",request.getParameter("cName"));
		model.addAttribute("cPw",request.getParameter("cPw"));
		model.addAttribute("cComment",request.getParameter("cComment"));
		
		command = new WrtieReCommentCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		return result;
		
	}
	@ResponseBody  //댓글 비밀번호 확인
	@RequestMapping(value = "/checkCommentPw", method = RequestMethod.POST)
	public String checkCommentPw(HttpServletRequest request, Model model) throws Exception {
		System.out.println("checkCommentPw");
				
		model.addAttribute("cId",request.getParameter("cId"));
		model.addAttribute("cPw",request.getParameter("cPw"));
		
		command = new CheckCommentPwCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		return result;
	}
	@ResponseBody  //댓글 수정하기
	@RequestMapping(value = "/modifyComment", method = RequestMethod.POST)
	public String modifyComment(HttpServletRequest request, Model model) throws Exception {
		System.out.println("modifyComment");
				
		model.addAttribute("cId",request.getParameter("cId"));
		model.addAttribute("cComment",request.getParameter("cComment"));
		
		command = new ModifyCommentCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		return result;
	}
	
	@Transactional
	@ResponseBody  //댓글 삭제하기
	@RequestMapping(value = "/deleteComment", method = RequestMethod.POST)
	public String deleteComment(HttpServletRequest request, Model model) throws RuntimeException {
		System.out.println("deleteComment");
		
		model.addAttribute("cId",request.getParameter("cId"));
		model.addAttribute("fbId",request.getParameter("fbId"));
		
		command = new DeleteCommentCommand(); //댓글, 딸린 대댓글 모두 삭제
		
		try {
			command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}  
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		return result;
	}
		
	
	
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
