package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IReviewDao;

@Transactional
public class DeleteOneReviewDataCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		Map<String, Object> map = model.asMap();
				
		int result = dao.deleteReivew((String) map.get("reId"));
		if(result==1) return;
		else {
			model.addAttribute("result", null);
			throw new RuntimeException("review Delete error transaction");
		}
	}

}
