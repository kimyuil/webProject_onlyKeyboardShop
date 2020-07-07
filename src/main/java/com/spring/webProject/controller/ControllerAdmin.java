package com.spring.webProject.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.admin.AdminOrderListCommand;
import com.spring.webProject.command.admin.AdminQnAListCommand;
import com.spring.webProject.command.admin.ModifyAdminAnswerQnaCommand;
import com.spring.webProject.command.admin.adminSendItemModifyCommand;
import com.spring.webProject.command.admin.adminShippedItemModifyCommand;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.QNABoardDto;


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
	
	//check Order page
	@RequestMapping(value = "/admin/checkOrder", method = RequestMethod.GET)
	public String checkOrder(Model model) {
		System.out.println("checkOrder");	
		return "membership/admin/checkOrder";
	}
	//check Q&A page
	@RequestMapping(value = "/admin/checkQnA", method = RequestMethod.GET)
	public String checkQnA(Model model) {
		System.out.println("checkQnA");	
		return "membership/admin/checkQnA";
	}
	
	 //ajax 모든 사용자 구매요청 불러오기
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
	
	// admin 발송버튼
	@RequestMapping(value = "/adminSendItemModify", method = RequestMethod.POST)
	public @ResponseBody String adminSendItemModify(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("adminSendItemModify");
		
		command = new adminSendItemModifyCommand();
		model.addAttribute("purId", request.getParameter("purId"));
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
						
		int result = (Integer)map.get("result");
		
		return result==1 ?  "success" : null;
		
	}
	// admin 발송완료버튼
	@RequestMapping(value = "/adminShippedItemModify", method = RequestMethod.POST)
	public @ResponseBody String adminShippedItemModify(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("adminShippedItemModify");
		
		command = new adminShippedItemModifyCommand();
		model.addAttribute("purId", request.getParameter("purId"));
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
						
		int result = (Integer)map.get("result");
		
		return result==1 ?  "success" : null;
	}

	// Q&A 리스트 불러오기
	@ResponseBody
	@RequestMapping(value = "/adminQnaList", method = RequestMethod.POST)
	public Map<String, Object> adminQnaList(HttpServletRequest request, Model model) throws Exception{
		System.out.println("adminQnaList");
		
		command = new AdminQnAListCommand();
		
		model.addAttribute("uId", request.getParameter("uId"));
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();

		ArrayList<QNABoardDto> qnaList = (ArrayList<QNABoardDto>)map.get("qnas");
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("qnas", qnaList);
		return result;
	}
	
	
	//Q&A DB 테이블로 update 답변하기 기능 ajax
	@ResponseBody
	@RequestMapping(value = "/adminQnaAnswer", method = RequestMethod.POST)
	public String adminQnaAnswer(HttpServletRequest request, Model model) throws Exception {
		System.out.println("Do  adminQnaAnswer");
				
		model.addAttribute("qnaId", request.getParameter("qnaId"));
		model.addAttribute("qnaAnswer", request.getParameter("qnaAnswer"));
				
		command = new ModifyAdminAnswerQnaCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		int result = (Integer) map.get("result");
		return result==1 ?  "success" : null;
		
	}

}
