package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;

public class DeleteFreeboardCommand implements ICommand {

	@Transactional
	@Override
	public void execute(SqlSession sqlSession, Model model) throws RuntimeException {
		// TODO Auto-generated method stub
		
		ICommand boardCommand = new DeleteFreeboardItemCommand();
		ICommand commentCommand = new DeleteCommentByFreeboardCommand();

		try {
			boardCommand.execute(sqlSession, model);
			commentCommand.execute(sqlSession, model);
			model.addAttribute("result", "success");
			
		}catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
		
	}

}
