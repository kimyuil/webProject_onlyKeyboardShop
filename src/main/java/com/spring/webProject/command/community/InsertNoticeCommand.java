package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.INoticeBoardDao;
import com.spring.webProject.dto.NoticeDto;

public class InsertNoticeCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		INoticeBoardDao dao = sqlSession.getMapper(INoticeBoardDao.class);
		Map<String, Object> map = model.asMap();
		NoticeDto notice = (NoticeDto)map.get("notice");
		
		int result = dao.insertNoticeBoard(notice);
	}

}
