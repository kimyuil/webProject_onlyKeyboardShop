package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.ReviewBoardDto;

public class GetUserReviewOneItemCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		
		Map<String, Object> map = model.asMap();
		String reId = (String) map.get("reId");
		
		ReviewBoardDto review = new ReviewBoardDto();
		review = dao.getUserReviewOneItem(reId);
		model.addAttribute("review", review);
	}

}
