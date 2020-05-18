package com.spring.webProject.command;

import org.apache.ibatis.session.SqlSession;

import com.spring.webProject.dao.IDao;

public class TestCommand {

	public void dodo(SqlSession sqlSession) {
		// TODO Auto-generated method stub
		IDao dao = sqlSession.getMapper(IDao.class);
		dao.modify();
	}

}
