package com.spring.webProject.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IDao;

public class TestCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modify();
	}

}
