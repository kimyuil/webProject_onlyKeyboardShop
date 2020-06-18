package com.spring.webProject.command;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.ReviewBoardDto;

public class WriteReviewCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		
		Map<String, Object> map = model.asMap();
		String pId = (String) map.get("pId");
		String uId = (String) map.get("uId");
		String pName = (String) map.get("pName");
		String pColor = (String) map.get("pColor");
		String uName = (String) map.get("uName");
		String reGrade = (String) map.get("reGrade");
		String reContent = (String) map.get("reContent");
		
		int result;
		result = dao.writeReivew(pId,uId,pName,pColor,uName,reGrade,reContent);
		
		model.addAttribute("result", result);
	}

}
