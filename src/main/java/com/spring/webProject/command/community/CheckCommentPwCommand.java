package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
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
		
		model.addAttribute("result", RealPw.equals(inputcPw) ? "ok" : null);
		
	}

}
