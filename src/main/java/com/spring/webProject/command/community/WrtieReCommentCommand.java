package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeCommentDao;
import com.spring.webProject.dto.FreeCommentDto;

public class WrtieReCommentCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
		
		Map<String, Object> map = model.asMap();
		FreeCommentDto comment = (FreeCommentDto)map.get("comment");
		
		int result = dao.writeReComment(comment);
		
		model.addAttribute("result", result==1?"success":null);
		
	}

}
