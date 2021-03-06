package com.spring.webProject.command.community;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dto.FreeBoardDto;

public class FreeBoardContentViewCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		
		Map<String, Object> map = model.asMap();
		String fbId = (String) map.get("fbId");
		
		dao.clickBoardHit(fbId); //조회수 up
		
		FreeBoardDto board = new FreeBoardDto();
		board = dao.getBoardContent(fbId);
		
		model.addAttribute("board", board); //내용 불러오기

	}

}
