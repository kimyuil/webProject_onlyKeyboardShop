package com.spring.webProject.command.community;

import java.util.Map;


import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeCommentDao;
import com.spring.webProject.dto.FreeCommentDto;

@Transactional
public class InsertCommentItemCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
				
		Map<String, Object> map = model.asMap();
		FreeCommentDto comment = (FreeCommentDto)map.get("comment");
		
		int result = dao.writeComment(comment);
		
		if(result==1) return;
		else 
			throw new RuntimeException("insert error");
		

	}

}
