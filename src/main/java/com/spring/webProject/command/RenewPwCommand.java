package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IUserDao;

public class RenewPwCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String id = (String)map.get("id");
		String pw = (String)map.get("pw");
		
		dao.renewPw(id,pw);
		model.addAttribute("pwChange", "ok");
	}

}
