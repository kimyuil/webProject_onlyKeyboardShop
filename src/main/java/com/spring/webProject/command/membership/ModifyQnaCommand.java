package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dao.IReviewDao;

public class ModifyQnaCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
		String qnaId = (String) map.get("qnaId");
		String qnaTitle = (String) map.get("qnaTitle");
		String qnaContent = (String) map.get("qnaContent");
		String isSecret = (String) map.get("isSecret");
		
		int result = dao.modifyQnA(qnaId,qnaTitle,qnaContent,isSecret);
		
		model.addAttribute("result", result==1 ? "success" : null );  
		
	}

}
