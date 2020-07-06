package com.spring.webProject.command.admin;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IPurchaseListDao;

public class adminSendItemModifyCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String purId = (String) map.get("purId");
		
		int result = dao.adminSendItem(purId); //success:1 fail:0
		model.addAttribute("result", result);
	}

}
