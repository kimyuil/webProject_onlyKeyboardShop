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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.webProject.command.ChangePurchaseStateCommand;
import com.spring.webProject.command.DeleteUserInfoCommand;
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
import com.spring.webProject.command.ModifyUserInfoCommand;
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
import com.spring.webProject.command.UserReviewListCommand;
import com.spring.webProject.command.WriteReivewCommand;
import com.spring.webProject.command.InsertReviewDataCommand;
import com.spring.webProject.dto.PageDto;
import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.ReviewBoardDto;
import com.spring.webProject.dto.UserDto;


@Controller
public class ControllerMypageOthers {

	ICommand command;
	
	@Autowired
	private SqlSession sqlSession;
	

	//장바구니
	@RequestMapping(value = "/basket", method = RequestMethod.GET)
	public String basketShow(Model model) {
		System.out.println("basketShow");
		
		return "membership/basketPage";
	}
	

	//회원정보화면
	@RequestMapping(value = "/member/modifyInfo", method = RequestMethod.GET)
	public String modifyInfo(Model model) {
		System.out.println("modifyInfo");
		return "membership/mypage/modifyInfoView";
	}
	//회원정보 수정 화면
	@RequestMapping(value = "/member/modifyUserView", method = RequestMethod.GET)
	public String modifyUserView(Model model) {
		System.out.println("modifyUserView");
		return "membership/mypage/modifyUserView";
	}
	
	//회원정보 수정 화면 db작업!
	@RequestMapping(value = "/doModifyUserInfo", method = RequestMethod.POST)
	public String doModifyUserInfo(HttpServletRequest request, Model model) throws Exception {
		System.out.println("doModifyUserInfo");
		
		model.addAttribute("uPhone", request.getParameter("uPhone"));
		model.addAttribute("uEmail", request.getParameter("uEmail"));
		model.addAttribute("uAdress", request.getParameter("uAdress"));
		model.addAttribute("uId", request.getParameter("uId"));
		model.addAttribute("uPw", request.getParameter("uPw"));
		
		command = new ModifyUserInfoCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String) map.get("result");
		
		if(result=="success") {
			SecurityContextHolder.clearContext();
			return "membership/mypage/modifyInfoComplete";
		}
		else
			return "membership/mypage/modifyInfoComplete";
		
	}
	
	//회원탈퇴화면
	@RequestMapping(value = "/member/deleteInfo", method = RequestMethod.GET)
	public String deleteInfo(Model model) {
		System.out.println("deleteInfo");
		
		return "membership/mypage/deleteInfo";
	}
	//회원탈퇴 DB작업
	@RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
	public String deleteUser(HttpServletRequest request, Model model) throws Exception {
		System.out.println("deleteUser");
		
		model.addAttribute("uId",request.getParameter("uId"));
		model.addAttribute("uPw",request.getParameter("uPw"));
		command = new DeleteUserInfoCommand();
		command.execute(sqlSession, model);
		
		Map<String, Object> map = model.asMap();
		String result = (String) map.get("result");
		
		if(result=="true") {
			SecurityContextHolder.clearContext();
			return "membership/mypage/deleteInfoComplete";
		}
		else
			return "membership/mypage/deleteInfo";
		
		
	}
}
