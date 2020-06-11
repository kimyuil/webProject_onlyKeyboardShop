package com.spring.webProject.command;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.PurchaseListDto;


public class PurchaseItemsCommand implements ICommand {

	@Transactional
	@Override
	public void execute(SqlSession sqlSession, Model model) throws RuntimeException {
		// TODO Auto-generated method stub
		//대충 이렇게 될텐데..
		ICommand purchaseCommand = new InsertPurchaseCommand();
		ICommand productCommand = new ChangeProductStockCommand();
		try {
		productCommand.execute(sqlSession, model);//재고줄이고 판매량늘리기
		purchaseCommand.execute(sqlSession, model);//구매목록 db에 insert
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage());
		}
		
	}
	

}


