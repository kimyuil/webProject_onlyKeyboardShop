package com.spring.webProject.command.product;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dao.IReviewDao;

@Transactional
public class ChangePurchaseStateCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String purId = (String) map.get("purId");//userId, productId
		//System.out.println(purId);
		
		int result;
		result = dao.changeUserState(purId);
		
		if(result!=1) {
			model.addAttribute("result", "fail");
			throw new RuntimeException("stateChange error");
		}
		else
			model.addAttribute("result", "success");
			
		
			
	}

}
