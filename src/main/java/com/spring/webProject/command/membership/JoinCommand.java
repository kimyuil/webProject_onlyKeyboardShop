package com.spring.webProject.command.membership;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IUserDao;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.UserDto;

public class JoinCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		UserDto user = (UserDto) map.get("user");
		
		int result = dao.joinUser(user);
		model.addAttribute("result", result==1 ? "success" : null);
		
	}

}
