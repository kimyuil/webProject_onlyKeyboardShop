package com.spring.webProject.command;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.PurchaseListDto;

public class PurchaseItemsCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		//대충 이렇게 될텐데..
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
		
		
		try {
			for(int i = 0 ; i < pId.length; i++) {
				System.out.println(i+"번째 실행");
				doPurchase(dao,uId, uName, uAdress, uPhone, deliverMessage, pId[i], pName[i], pColor[i], pImage[i], pNumof[i],state);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			model.addAttribute("error", true);
		}
	}
	
	synchronized void doPurchase(IPurchaseListDao dao,String uId,String uName,String uAdress,String uPhone,String deliverMessage,String pId,String pName,String pColor,String pImage,String pNumof,String state) {
		dao.purchaseItems(uId, uName, uAdress, uPhone, deliverMessage, pId, pName, pColor, pImage, pNumof,state);
	}

}


