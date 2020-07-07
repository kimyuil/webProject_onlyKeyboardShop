package com.spring.webProject.command.membership;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dto.FreeBoardDto;

public class UserFreeboardListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		
		Map<String, Object> map = model.asMap();
		ArrayList<FreeBoardDto> boards = new ArrayList<FreeBoardDto>();
		boards = dao.userListFreeboard((String) map.get("uId"));
		model.addAttribute("boards", boards);
	}

}
