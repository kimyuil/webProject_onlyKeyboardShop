package com.spring.webProject.command.product;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.PurchaseListDto;

@Transactional
public class InsertPurchaseCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		String uName = (String) map.get("uName");
		String uAdress = (String) map.get("uAdress");
		String uPhone = (String) map.get("uPhone");
		String deliverMessage = (String) map.get("deliverMessage");
		String[] pId = ((String)map.get("pId")).split(",");
		String[] pName = ((String) map.get("pName")).split(",");
		String[] pColor = ((String) map.get("pColor")).split(",");
		String[] pImage = ((String) map.get("pImage")).split(",");
		String[] pNumof = ((String) map.get("pNumof")).split(",");
		String state = PurchaseListDto.purchased;
		
		for(int i = 0 ; i < pId.length; i++) 
			doPurchase(dao,uId, uName, uAdress, uPhone, deliverMessage, pId[i], pName[i], pColor[i], pImage[i], pNumof[i],state);
		
	}
	
	synchronized void doPurchase(IPurchaseListDao dao,String uId,String uName,String uAdress,String uPhone,String deliverMessage,String pId,String pName,String pColor,String pImage,String pNumof,String state) {
		int result = dao.purchaseItems(uId, uName, uAdress, uPhone, deliverMessage, pId, pName, pColor, pImage, pNumof,state);
		if(result!=1)
			throw new RuntimeException("insert error");
	}

}
