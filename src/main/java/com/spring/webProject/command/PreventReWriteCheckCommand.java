package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IPurchaseListDao;

public class PreventReWriteCheckCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String purId = (String) map.get("purId");
		
		String result = dao.getStateByPurId(purId);
		model.addAttribute("resultState", result);
	}

}