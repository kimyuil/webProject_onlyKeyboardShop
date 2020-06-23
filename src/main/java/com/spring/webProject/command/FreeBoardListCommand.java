package com.spring.webProject.command;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dto.FreeBoardDto;

public class FreeBoardListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		
		ArrayList<FreeBoardDto> boards = new ArrayList<FreeBoardDto>();
		boards = dao.getBoardsList();
		
		model.addAttribute("boards", boards);
	}

}
