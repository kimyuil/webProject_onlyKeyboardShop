package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.ReviewBoardDto;

public class GetUserReviewOneItemCommand implements ICommand { //¸®ºä¼öÁ¤ÇÒ¶§ ¾²ÀÓ ÆË¾÷

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		
		Map<String, Object> map = model.asMap();
				
		ReviewBoardDto review = new ReviewBoardDto();
		review = dao.getUserReviewOneItem((String) map.get("reId"));
		model.addAttribute("review", review);
	}

}
