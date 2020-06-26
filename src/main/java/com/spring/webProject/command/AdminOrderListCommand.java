package com.spring.webProject.command;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.PurchaseListDto;

public class AdminOrderListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IPurchaseListDao dao = sqlSession.getMapper(IPurchaseListDao.class);
		
		ArrayList<PurchaseListDto> totalList = new ArrayList<PurchaseListDto>(); 
		ArrayList<PurchaseListDto> beforeCheckList = new ArrayList<PurchaseListDto>();
		ArrayList<PurchaseListDto> afterCheckList = new ArrayList<PurchaseListDto>();
		
		totalList = dao.getPurchaseListAdmin();
		for(int i = 0 ; i < totalList.size(); i++) { //사용자 구매확정누른후(후기를 쓸수 있음)
			if(totalList.get(i).getState().equals(PurchaseListDto.checkedDelivery)||
					totalList.get(i).getState().equals(PurchaseListDto.writeReview)) {
				afterCheckList.add(totalList.get(i));
			}
			else { //사용자 구매확정버튼 누르기전
				beforeCheckList.add(totalList.get(i));
			}
		}
		model.addAttribute("beforeCheckList", beforeCheckList);
		model.addAttribute("afterCheckList", afterCheckList);
	}

}
