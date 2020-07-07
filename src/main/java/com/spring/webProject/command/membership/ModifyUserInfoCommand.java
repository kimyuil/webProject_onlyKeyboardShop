package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IUserDao;


public class ModifyUserInfoCommand implements ICommand {

	
	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		String uPw = (String) map.get("uPw");
		String uEmail = (String) map.get("uEmail");
		String uAdress = (String) map.get("uAdress");
		String uPhone = (String) map.get("uPhone");
		
		int result = dao.modifyUserInfo(uId,uPw,uEmail,uAdress,uPhone);
		
		model.addAttribute("result", result==1 ? "success" : "error" );
		
		if(result == 1) //강제 로그아웃 시키기
			SecurityContextHolder.clearContext();
		
	}

}

