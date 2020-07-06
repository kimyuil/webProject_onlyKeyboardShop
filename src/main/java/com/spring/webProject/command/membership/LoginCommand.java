package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IUserDao;
import com.spring.webProject.dto.UserDto;

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

		//로그인 이후는? 페이지에 이름띄어주기해야하는데 그냥 name값 세션에 넣으면 끝
		//uId만 있으면 구매도 장바구니도 게시판글쓰기도 모두 가능함.
		//name과 uId만 받아오면 되고.. null이면 로그인실패인것..		
	}

}
