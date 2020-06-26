package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;
import com.spring.webProject.dao.IUserDao;


public class ModifyUserInfoCommand implements ICommand {

	
	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		String uPw = (String) map.get("uPw");
		String uEmail = (String) map.get("uEmail");
		String uAdress = (String) map.get("uAdress");
		String uPhone = (String) map.get("uPhone");
		
		int result = dao.modifyUserInfo(uId,uPw,uEmail,uAdress,uPhone);
		
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", "fail");
		}
		
	}

}

