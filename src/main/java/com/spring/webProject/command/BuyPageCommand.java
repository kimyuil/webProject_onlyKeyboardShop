package com.spring.webProject.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

public class BuyPageCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		//user 정보필요 -> uId 받아와야함
		//product 정보 필요 -> pid 받아와야함
		//받아오면 그냥 일단 정보를 model에 넣어서 리턴하면 됨.
	}

}
