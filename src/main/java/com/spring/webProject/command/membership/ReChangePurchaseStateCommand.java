package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IPurchaseListDao;
@Transactional
public class ReChangePurchaseStateCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		
		int result = dao.reChangeUserState((String) map.get("purId"));
		
		if(result==1) return ;
		else {
			model.addAttribute("result", null);
			throw new RuntimeException("purchaseList state Rechange error transaction");
		}

	}

}
