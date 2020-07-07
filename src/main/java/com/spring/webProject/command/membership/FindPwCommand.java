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
		
		String result = dao.findPw(email,id); //id�� �̸����� ������ �ش� id���� �ٽø�����. 
		
		//���ٸ� �̸����̳� ���̵��� �ϳ��� �߸��� ��.
		model.addAttribute("resultPw", result == null ? "false" : "ok" );
		 
	}

}