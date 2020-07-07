package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.ReviewBoardDto;

@Transactional
public class ChangePurchaseStateCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		ReviewBoardDto review = (ReviewBoardDto)map.get("review");
				
		int result = dao.changeUserState(Integer.toString(review.getPurId()));
		
		if(result==1) return;
		else {
			model.addAttribute("result", "fail");
			throw new RuntimeException("stateChange error");
		}
					
	}

}
