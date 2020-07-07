package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dao.IReviewDao;

public class DeleteQnaCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
			
		int result = dao.deleteQNA((String) map.get("qnaId"));
		
		model.addAttribute("result", result==1 ? "success" : null);
		
	}

}
