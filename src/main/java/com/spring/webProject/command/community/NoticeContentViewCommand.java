package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dao.INoticeBoardDao;
import com.spring.webProject.dto.NoticeDto;

public class NoticeContentViewCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		INoticeBoardDao dao = sqlSession.getMapper(INoticeBoardDao.class);
		
		Map<String, Object> map = model.asMap();
		String nbId = (String) map.get("nbId");
		
		int result = dao.clickNoticeHit(nbId);
		NoticeDto notice = new NoticeDto();
		notice = dao.getNoticeContent(nbId);
		
		model.addAttribute("notice", notice);
	}

}
