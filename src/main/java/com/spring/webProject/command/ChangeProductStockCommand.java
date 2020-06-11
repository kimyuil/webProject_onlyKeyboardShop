package com.spring.webProject.command;


import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IPurchaseListDao;

import org.springframework.transaction.annotation.Propagation;


@Transactional
public class ChangeProductStockCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IProductDao dao =sqlSession.getMapper(IProductDao.class);
		Map<String, Object> map = model.asMap();
		String[] pId = ((String)map.get("pId")).split(",");
		String[] pNumof = ((String) map.get("pNumof")).split(",");
		
		for(int i = 0 ; i < pId.length; i++) {
			doStock(dao,pId[i], pNumof[i]);
			
		}
		
	}
	
	synchronized void doStock(IProductDao dao,String pId,String pnum) {
		int result = dao.checkStockAddSelling(pId,pnum);
		if(result!=1)
			throw new RuntimeException("No Stock");
	}
	
	
}
