package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IUserDao;

public class FindPwCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String email = (String)map.get("email");
		String id = (String)map.get("id");
		
		String result = dao.findPw(email,id); //id와 이메일이 맞으면 해당 id값을 다시리턴함. 
		
		//없다면 이메일이나 아이디중 하나가 잘못된 것.
		model.addAttribute("resultPw", result == null ? "false" : "ok" );
		 
	}

}
