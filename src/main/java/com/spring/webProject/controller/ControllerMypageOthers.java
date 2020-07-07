package com.spring.webProject.controller;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.command.membership.DeleteUserInfoCommand;
import com.spring.webProject.command.membership.ModifyUserInfoCommand;


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
		
		return result=="success" ? "membership/mypage/deleteInfoComplete" 
				: "membership/mypage/deleteInfo";  
		
		
		
	}
}
