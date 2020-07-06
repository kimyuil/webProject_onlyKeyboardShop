package com.spring.webProject.command.community;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dao.INoticeBoardDao;
import com.spring.webProject.dto.FreeBoardDto;
import com.spring.webProject.dto.NoticeDto;

public class NoticeBoardListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		INoticeBoardDao dao = sqlSession.getMapper(INoticeBoardDao.class);
		
		ArrayList<NoticeDto> notices = new ArrayList<NoticeDto>();
		notices = dao.getNoticeBoardsList();//여기서문제!?
		
		model.addAttribute("notices", notices);
	}

}
