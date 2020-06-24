package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dao.IQNADao;

public class ModifyFreeboardCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		
		Map<String, Object> map = model.asMap();
		String fbId = (String) map.get("fbId");
		String fbTitle = (String) map.get("fbTitle");
		String fbContent = (String) map.get("fbContent");
		
		int result = dao.modifyFreeBoard(fbId,fbTitle,fbContent);
		
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", "fail");
		}
	}

}
