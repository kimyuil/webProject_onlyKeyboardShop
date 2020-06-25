package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeCommentDao;

public class CheckCommentPwCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
		
		Map<String, Object> map = model.asMap();
		String cId = (String) map.get("cId");
		String inputcPw = (String) map.get("cPw");
		
		String RealPw = dao.getCommentPwById(cId);
		
		if(RealPw.equals(inputcPw))
			model.addAttribute("result", "ok");
		else
			model.addAttribute("result", null);
	}

}
