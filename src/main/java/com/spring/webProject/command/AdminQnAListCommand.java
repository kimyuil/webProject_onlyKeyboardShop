package com.spring.webProject.command;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dto.QNABoardDto;

public class AdminQnAListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		ArrayList<QNABoardDto> qnas = new ArrayList<QNABoardDto>();
		qnas = dao.adminListQnA();
		
		model.addAttribute("qnas", qnas);
	}

}
