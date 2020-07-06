package com.spring.webProject.command.admin;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IQNADao;

public class ModifyAdminAnswerQnaCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
		String qnaId = (String) map.get("qnaId");
		String qnaAnswer = (String) map.get("qnaAnswer");
		
		int result = dao.adminAnswerQnA(qnaId,qnaAnswer);
		
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", "fail");
		}
	}

}
