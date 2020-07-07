package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IUserDao;
import com.spring.webProject.dto.UserDto;

//no use or Spring Security use
public class LoginCommand implements ICommand { 

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		String uPw = (String) map.get("uPw");
				
		String uName = dao.loginAction(uId,uPw);
		model.addAttribute("uName", uName);

	}

}
