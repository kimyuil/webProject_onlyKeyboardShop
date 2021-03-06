package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IReviewDao;

public class ModifyReivewCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IReviewDao dao = sqlSession.getMapper(IReviewDao.class);
		
		Map<String, Object> map = model.asMap();
		String reId = (String) map.get("reId");
		String reContent = (String) map.get("reContent");
		String reGrade = (String) map.get("reGrade");
		
		int result = dao.modifyReivew(reId,reContent,reGrade);
		model.addAttribute("result", result==1 ? "success" : null );
	}

}
