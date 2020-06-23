package com.spring.webProject.command;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.QNABoardDto;
import com.spring.webProject.dto.ReviewBoardDto;

public class UserQnAListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		
		ArrayList<QNABoardDto> qnas = new ArrayList<QNABoardDto>();
		qnas = dao.userListQnA(uId);
		
		model.addAttribute("qnas", qnas);
	}

}
