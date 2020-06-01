package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IUserDao;

public class FindPwCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String email = (String)map.get("email");
		String id = (String)map.get("id");
		System.out.println(email+", "+id);
		String result = dao.findPw(email,id);
		System.out.println(result+"pwcommand결과");		
		if(result == null)
			model.addAttribute("resultPw", "false");
		else
			model.addAttribute("resultPw", "true"); //새롭게 renew Pw
	}

}
