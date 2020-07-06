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
		String nbId = (String) map.get("nbId");
		int result = dao.deleteNotice(nbId);
		
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", null);			
		}
	}

}
