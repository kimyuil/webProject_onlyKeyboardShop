package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dao.IReviewDao;

public class ChangePurchaseStateCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String productId = (String) map.get("pId");//userId, productId
		String userId = (String) map.get("uId");
		
		int result;
		result = dao.changeUserState(productId,userId);
	}

}
