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
		String reId = (String) map.get("reId");
		
		int result = dao.deleteReivew(reId);
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", "fail");
			throw new RuntimeException("Delete error");
		}
	}

}
