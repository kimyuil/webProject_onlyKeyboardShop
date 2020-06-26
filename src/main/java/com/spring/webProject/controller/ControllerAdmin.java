package com.spring.webProject.controller;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Enumeration;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.AdminOrderListCommand;
import com.spring.webProject.command.AdminQnAListCommand;
import com.spring.webProject.command.ChangePurchaseStateCommand;
import com.spring.webProject.command.DeleteQnaCommand;
import com.spring.webProject.command.DeleteUserReviewCommand;
import com.spring.webProject.command.FindIdCommand;
import com.spring.webProject.command.FindPwCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.JoinCommand;
import com.spring.webProject.command.LoginCommand;
import com.spring.webProject.command.ModifyAdminAnswerQnaCommand;
import com.spring.webProject.command.ModifyQnaCommand;
import com.spring.webProject.command.ModifyReivewCommand;
import com.spring.webProject.command.OrderListCommand;
import com.spring.webProject.command.ReviewPageCommand;
import com.spring.webProject.command.PreventReWriteCheckCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.RenewPwCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.command.UserCheckCommand;
import com.spring.webProject.command.UserCheckDeliveryCommand;
import com.spring.webProject.command.UserFreeboardListCommand;
import com.spring.webProject.command.UserQnAListCommand;
import com.spring.webProject.command.UserReviewListCommand;
import com.spring.webProject.command.WriteReivewCommand;
import com.spring.webProject.command.adminSendItemModifyCommand;
import com.spring.webProject.command.adminShippedItemModifyCommand;
import com.spring.webProject.command.InsertReviewDataCommand;
import com.spring.webProject.dto.FreeBoardDto;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.QNABoardDto;
import com.spring.webProject.dto.ReviewBoardDto;
import com.spring.webProject.dto.UserDto;


@Controller
public class ControllerAdmin {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
		
	//admin page
	@RequestMapping(value = "/admin/adminpage", method = RequestMethod.GET)
	public String myboard(Model model) {
		System.out.println("adminpage");	
		return "membership/admin/adminpage";
	}
	
	//check Order
	@RequestMapping(value = "/admin/checkOrder", method = RequestMethod.GET)
	public String checkOrder(Model model) {
		System.out.println("checkOrder");	
		return "membership/admin/checkOrder";
	}
	//check Order
	@RequestMapping(value = "/admin/checkQnA", method = RequestMethod.GET)
	public String checkQnA(Model model) {
		System.out.println("checkQnA");	
		return "membership/admin/checkQnA";
	}
	
	 //ajax 구매목록 가져오기
	@RequestMapping(value = "/getTotalOrderList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> getTotalOrderList(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("getTotalOrderList");
		
		command = new AdminOrderListCommand();
		String uId = request.getParameter("uId");
		System.out.println(uId);
		model.addAttribute("uId", uId);
	
		command.execute(sqlSession, model);
			
		Map<String, Object> map = model.asMap();
				
		ArrayList<PurchaseListDto> beforeList = (ArrayList<PurchaseListDto>)map.get("beforeCheckList");
		ArrayList<PurchaseListDto> afterList = (ArrayList<PurchaseListDto>)map.get("afterCheckList");
		
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("beforeList", beforeList);
		result.put("afterList", afterList);
		
		return result;
		
	}
	
	@RequestMapping(value = "/adminSendItemModify", method = RequestMethod.POST)
	public @ResponseBody String adminSendItemModify(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("adminSendItemModify");
		
		command = new adminSendItemModifyCommand();
		String purId = request.getParameter("purId");
		System.out.println(purId);
		model.addAttribute("purId", purId);
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
						
		int result = (Integer)map.get("result");
		System.out.println("result : "+result);
		if(result==1) {
			System.out.println("success");
			return "success";
		}
		else
			return null;
	}
	@RequestMapping(value = "/adminShippedItemModify", method = RequestMethod.POST)
	public @ResponseBody String adminShippedItemModify(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("adminShippedItemModify");
		
		command = new adminShippedItemModifyCommand();
		String purId = request.getParameter("purId");
		System.out.println(purId);
		model.addAttribute("purId", purId);
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
						
		int result = (Integer)map.get("result");
		System.out.println("result : "+result);
		if(result==1) {
			System.out.println("success");
			return "success";
		}
		else
			return null;
	}

	// Q&A 리스트 불러오기
	@ResponseBody
	@RequestMapping(value = "/adminQnaList", method = RequestMethod.POST)
	public Map<String, Object> adminQnaList(HttpServletRequest request, Model model) throws Exception{
		System.out.println("adminQnaList");
		
		command = new AdminQnAListCommand();
		
		String id = request.getParameter("uId");
		model.addAttribute("uId", id);
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();

		ArrayList<QNABoardDto> qnaList = (ArrayList<QNABoardDto>)map.get("qnas");
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("qnas", qnaList);
		return result;
	}
	
	//adminQnaAnswer

	//Q&A DB 테이블로 update
	@ResponseBody
	@RequestMapping(value = "/adminQnaAnswer", method = RequestMethod.POST)
	public String adminQnaAnswer(HttpServletRequest request, Model model) throws Exception {
		System.out.println("Do  adminQnaAnswer");
				
		model.addAttribute("qnaId", request.getParameter("qnaId"));
		model.addAttribute("qnaAnswer", request.getParameter("qnaAnswer"));
				
		command = new ModifyAdminAnswerQnaCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String) map.get("result");
		if(result=="success")
			return "success";
		else
			return null;
		
	}
//	//qna수정버튼클릭 
//	@RequestMapping(value = "/member/userModifyQnaView", method = RequestMethod.POST)
//	public String userModifyQnaView(HttpServletRequest request,Model model) throws Exception {
//		System.out.println("userModifyQnaView");
//		
//		//command = new GetUserReviewOneItemCommand();
//		model.addAttribute("qnaId", request.getParameter("qnaId"));
//		model.addAttribute("pId", request.getParameter("pId"));
//		model.addAttribute("pName", request.getParameter("pName"));
//		model.addAttribute("qnaTitle", request.getParameter("qnaTitle"));
//		model.addAttribute("qnaContent", request.getParameter("qnaContent"));
//		model.addAttribute("isSecret", request.getParameter("isSecret"));
//		model.addAttribute("isFromMypage", "true");
//		//update from qna set title=~,content=~ isSecret=~ where qnaId = !
//		
//		return "product/QnaModifyView";
//		
//		//command.execute(sqlSession, model); //이전에 작성한 리뷰정보 가져오기
//	}
//	

//	
//	//userDeleteQna
//	@RequestMapping(value = "/userDeleteQna", method = RequestMethod.POST)
//	public String userDeleteQna(HttpServletRequest request, Model model) throws Exception {
//		System.out.println("Do userDeleteQna");
//				
//		model.addAttribute("qnaId", request.getParameter("qnaId"));
//		
//		command = new DeleteQnaCommand();
//		command.execute(sqlSession, model);
//		
//		return "redirect:member/myboard";
//		
//	}
//	
//	//userFreeboard
//	@ResponseBody
//	@RequestMapping(value = "/userFreeboardList", method = RequestMethod.POST)
//	public Map<String, Object> userFreeboardList(HttpServletRequest request, Model model) throws Exception{
//		System.out.println("userFreeboardList");
//		
//		command = new UserFreeboardListCommand();
//		
//		String id = request.getParameter("uId");
//		model.addAttribute("uId", id);
//		command.execute(sqlSession, model);
//		
//		Map<String, Object> map = model.asMap();
//
//		ArrayList<FreeBoardDto> boardsList = (ArrayList<FreeBoardDto>)map.get("boards");
//		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
//		result.put("boards", boardsList);
//		return result;
//	}
}
