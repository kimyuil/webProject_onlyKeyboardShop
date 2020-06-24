package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeCommentDao;

public class InsertCommentItemCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
				
		Map<String, Object> map = model.asMap();
		String fbId = (String) map.get("fbId");
		String cName = (String) map.get("cName");
		String cPw = (String) map.get("cPw");
		String cComment = (String) map.get("cComment");
		
		int result = dao.writeComment(fbId,cName,cPw,cComment);
		if(result==1)
			model.addAttribute("result", "success");
		else {
			model.addAttribute("result", null);
			throw new RuntimeException("insert error");
		}

	}

}
