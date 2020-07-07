package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.INoticeBoardDao;

public class DeleteNoticeCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		INoticeBoardDao dao = sqlSession.getMapper(INoticeBoardDao.class);
		
		Map<String, Object> map = model.asMap();
		int result = dao.deleteNotice((String) map.get("nbId"));
		
		model.addAttribute("result", result==1 ? "success" : null );
	}

}
