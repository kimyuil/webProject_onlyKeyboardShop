package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IReviewDao;

public class DeleteUserReviewCommand implements ICommand {

	@Transactional
	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		
		
		ICommand reviewCommand = new DeleteOneReviewDataCommand();
		ICommand purchaseListCommand = new ReChangePurchaseStateCommand();
		
		try {
			reviewCommand.execute(sqlSession, model);//재고줄이고 판매량늘리기
			purchaseListCommand.execute(sqlSession, model);//구매목록 db에 insert
			model.addAttribute("result", "success");
		}
		catch(Exception e){
			model.addAttribute("result", null);
			throw new RuntimeException(e.getMessage());
		}
		

	}

}
