package com.spring.webProject.command.membership;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.ReviewBoardDto;

public class UserReviewListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		
		Map<String, Object> map = model.asMap();
		
		ArrayList<ReviewBoardDto> reviews = new ArrayList<ReviewBoardDto>();
		reviews = dao.userListReview((String) map.get("uId"));
				
		model.addAttribute("reviews", reviews);
	}

}
