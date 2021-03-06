package com.spring.webProject.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.membership.OrderListCommand;
import com.spring.webProject.command.membership.PreventReWriteCheckCommand;
import com.spring.webProject.command.membership.UserCheckDeliveryCommand;
import com.spring.webProject.command.membership.WriteReivewCommand;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.ReviewBoardDto;


@Controller
public class ControllerMypageOrder {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
			
	//주문배송조회 들어가기
	@RequestMapping(value = "/member/lookupOrder", method = RequestMethod.GET)
	public String lookupOrder(HttpServletRequest request, Model model) throws Exception {
		System.out.println("lookupOrder");
			
		return "membership/mypage/lookupOrder";
	}
	//ajax 구매목록 가져오기
	@RequestMapping(value = "/getOrderList", method = RequestMethod.POST)
	public @ResponseBody Map<String, Object> reviewList(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("getOrderList");
		
		command = new OrderListCommand();
		model.addAttribute("uId", request.getParameter("uId"));
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
				
		ArrayList<PurchaseListDto> beforeList = (ArrayList<PurchaseListDto>)map.get("beforeCheckList");
		ArrayList<PurchaseListDto> afterList = (ArrayList<PurchaseListDto>)map.get("afterCheckList");
		
		Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		result.put("beforeList", beforeList);
		result.put("afterList", afterList);
		
		return result;
		
	}//	
	  //ajax 배송된것 받았다고 체크버튼
	@RequestMapping(value = "/checkDelivery", method = RequestMethod.POST)
	public @ResponseBody String checkDelivery(HttpServletRequest request, Model model)throws Exception {
		
		System.out.println("User check Delivery");
		
		command = new UserCheckDeliveryCommand();
		model.addAttribute("purId", request.getParameter("purId"));
		
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
						
		int result = (Integer)map.get("result");
		
		return result==1 ? "success" : null;

	}
	
	//리뷰작성버튼클릭
	@RequestMapping(value = "/writeReviewView", method = RequestMethod.GET)
	public String writeReviewView(HttpServletRequest request,Model model) throws Exception {
		System.out.println("writeReviewView");
		
		command = new PreventReWriteCheckCommand();
		model.addAttribute("purId",request.getParameter("purId"));
		command.execute(sqlSession, model); //리뷰 중복작성을 확인
		
		Map<String, Object> map = model.asMap();
		String resultState = (String)map.get("resultState");
		
		if (resultState.equals(PurchaseListDto.writeReview)) {
			model.addAttribute("submit","success");//창닫기용
			return "membership/mypage/writeReviewView";
		}
			
		model.addAttribute("pId",request.getParameter("pId"));
		model.addAttribute("uId",request.getParameter("uId"));
		model.addAttribute("pName",request.getParameter("pName"));
		model.addAttribute("pColor",request.getParameter("pColor"));
		model.addAttribute("uName",request.getParameter("uName"));
		return "membership/mypage/writeReviewView";
	}
	

	//리뷰 작성후 db로 내용 삽입
	@Transactional
	@RequestMapping(value = "/writeReview", method = RequestMethod.POST)
	public String writeReview(ReviewBoardDto review ,Model model) throws RuntimeException {
		System.out.println("do writeReview");
		
		model.addAttribute("review", review);
		
		command = new WriteReivewCommand();
		try {
			command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}
		
		
		Map<String, Object> map = model.asMap();
		
		String result = (String)map.get("result");
		
		model.addAttribute("submit", result=="success"? "success" : "error");
		
		return "membership/mypage/writeReviewView";
		
	}
		
}
