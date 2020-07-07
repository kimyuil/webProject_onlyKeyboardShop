package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeCommentDao;

public class DeleteCommentCommand implements ICommand {

	@Transactional
	@Override
	public void execute(SqlSession sqlSession, Model model) throws RuntimeException {
		// TODO Auto-generated method stub
		
		ICommand commentCommand = new DeleteCommentItemCommand();
		ICommand boardCommand = new DecreaseFreeboardReplysCommand();
		
		try {
			commentCommand.execute(sqlSession, model);
			boardCommand.execute(sqlSession, model);
			model.addAttribute("result", "success");
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}

}
