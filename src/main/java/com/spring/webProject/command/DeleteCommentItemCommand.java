package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeCommentDao;

@Transactional
public class DeleteCommentItemCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
		
		Map<String, Object> map = model.asMap();
		String cId = (String) map.get("cId");
		
		int result = dao.deleteComment(cId);
		
		if(result==0) {
			model.addAttribute("result", null);
			throw new RuntimeException("command delete error");
		}
		else { //삭제는 1줄 이상 가능하다!
			model.addAttribute("result", "ok");
		}
	}

}
