package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IUserDao;
import com.spring.webProject.dto.ProductDto;

public class IdCheckCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String result = dao.idCheck((String) map.get("id")); //id값이 있으면 그 id 값을 반환
		
		// 중복되지 않으면 (result==null) 1, 아니면 0
		model.addAttribute("result", result == null ? 1 : 0 );
		
	}

}
