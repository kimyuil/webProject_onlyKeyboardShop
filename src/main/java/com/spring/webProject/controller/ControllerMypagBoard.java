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

import com.spring.webProject.command.ChangePurchaseStateCommand;
import com.spring.webProject.command.DeleteUserReviewCommand;
import com.spring.webProject.command.FindIdCommand;
import com.spring.webProject.command.FindPwCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.GetUserReviewOneItemCommand;
import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.IdCheckCommand;
import com.spring.webProject.command.JoinCommand;
import com.spring.webProject.command.LoginCommand;
import com.spring.webProject.command.ModifyReivewCommand;
import com.spring.webProject.command.OrderListCommand;
import com.spring.webProject.command.PageCommand;
import com.spring.webProject.command.PreventReWriteCheckCommand;
import com.spring.webProject.command.ProductCommand;
import com.spring.webProject.command.ProductPageCommand;
import com.spring.webProject.command.RenewPwCommand;
import com.spring.webProject.command.ReviewListCommand;
import com.spring.webProject.command.TestCommand;
import com.spring.webProject.command.UserCheckCommand;
import com.spring.webProject.command.UserCheckDeliveryCommand;
import com.spring.webProject.command.UserReviewListCommand;
import com.spring.webProject.command.WriteReivewCommand;
import com.spring.webProject.command.InsertReviewDataCommand;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.ReviewBoardDto;
import com.spring.webProject.dto.UserDto;


@Controller
public class ControllerMypagBoard {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	
		
	//나의게시물 /userReviewList
	@RequestMapping(value = "/member/myboard", method = RequestMethod.GET)
	public String myboard(Model model) {
		System.out.println("myboard");	
		return "membership/mypage/myboard";
	}
	//나의 리뷰리스트 불러오기
	@ResponseBody
	@RequestMapping(value = "/userReviewList", method = RequestMethod.POST)
	public Map<String, Object> userReviewList(HttpServletRequest request, Model model) throws Exception{
		System.out.println("userReviewList");
		
		command = new UserReviewListCommand();
		
		String id = request.getParameter("uId");
		model.addAttribute("uId", id);
		command.execute(sqlSession, model);
		
		
		
		  Map<String, Object> map = model.asMap();

		  ArrayList<ReviewBoardDto> reviewList = (ArrayList<ReviewBoardDto>)map.get("reviews");
		  Map<String,Object> result = new HashMap<String, Object>();// 반환할 결과물
		  result.put("reviews", reviewList);
		  return result;
	}
	
	//리뷰수정버튼클릭 (리뷰수정창 팝업)
	@RequestMapping(value = "/modifyReviewView", method = RequestMethod.GET)
	public String modifyReviewView(HttpServletRequest request,Model model) throws Exception {
		System.out.println("modifyReviewView");
		
		command = new GetUserReviewOneItemCommand();
		String reId = request.getParameter("reId");
		model.addAttribute("reId",reId);
		command.execute(sqlSession, model); //이전에 작성한 리뷰정보 가져오기
		
		return "membership/mypage/modifyReviewView";
	}
	//리뷰 수정내용 db에 update
	@RequestMapping(value = "/modifyUserReview", method = RequestMethod.POST)
	public String modifyUserReview(HttpServletRequest request,Model model) throws Exception {
		
		System.out.println("modifyUserReview");
		
		model.addAttribute("reId", request.getParameter("reId"));
		model.addAttribute("reContent", request.getParameter("reContent"));
		model.addAttribute("reGrade", request.getParameter("reGrade"));
		
		command = new ModifyReivewCommand();
		command.execute(sqlSession, model);


		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		
		if(result=="success") {//success
			model.addAttribute("submit","success");//창닫기용
			return "membership/mypage/modifyReviewView";
		}
		else {
			model.addAttribute("submit","fail");
			return "membership/mypage/modifyReviewView";
		}
	}	
	
	//리뷰 삭제하기 & 구매에 reviewWrite 상태 바꾸기
	@Transactional
	@RequestMapping(value = "/deleteUserReview", method = RequestMethod.POST)
	public @ResponseBody String deleteUserReview(HttpServletRequest request, Model model) throws RuntimeException{
		System.out.println("deleteUserReview");
		
		command = new DeleteUserReviewCommand();
		
		String reId = request.getParameter("reId");
		String purId = request.getParameter("purId");
		model.addAttribute("reId", reId);
		model.addAttribute("purId", purId);
		
		try {
		command.execute(sqlSession, model);
		}
		catch(Exception e) {
			throw new RuntimeException(e.getMessage());			
		}
		
		Map<String, Object> map = model.asMap();
		String result = (String)map.get("result");
		System.out.println(result);
		if(result=="success") 
			return "success";
		else
			return null;		
		
	}
	
	
}
