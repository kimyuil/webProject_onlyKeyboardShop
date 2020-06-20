package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IPurchaseListDao;
@Transactional
public class ReChangePurchaseStateCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String purId = (String) map.get("purId");//userId, productId
		
		int result;
		result = dao.reChangeUserState(purId);
		
		if(result==1) {
			model.addAttribute("result", "success");
		}else {
			model.addAttribute("result", "fail");
			throw new RuntimeException("state Rechange error");
		}

	}

}
