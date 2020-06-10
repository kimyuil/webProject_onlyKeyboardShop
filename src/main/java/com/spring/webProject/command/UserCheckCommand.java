package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IUserDao;
import com.spring.webProject.dto.ProductDto;

public class UserCheckCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String id = (String) map.get("id");
		String pw = (String) map.get("pw");
		String result = dao.userCheck(id,pw);
		System.out.println(result);
		
		if(result == null)
			model.addAttribute("result", 0);
		else
			model.addAttribute("result", 1);
		
	}

}
