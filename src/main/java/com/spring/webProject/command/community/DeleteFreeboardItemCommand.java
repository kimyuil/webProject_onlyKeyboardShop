package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;

@Transactional
public class DeleteFreeboardItemCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws RuntimeException {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		Map<String, Object> map = model.asMap();
		int result = dao.deleteFreeBoard((String) map.get("fbId"));
		
		if(result==1) return; 
		else {
			model.addAttribute("result", null);
			throw new RuntimeException("freeboard delete error");
		}
	}

}
