package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dto.QNABoardDto;

public class WriteQnaCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
		QNABoardDto qna = (QNABoardDto) map.get("qna");
		
		int result = dao.insertQna(qna);
		
//		model.addAttribute("pName", request.getParameter("pName"));
//		model.addAttribute("pId", request.getParameter("pId"));
//		model.addAttribute("uName", request.getParameter("uName"));
//		model.addAttribute("uId", request.getParameter("uId"));
//		model.addAttribute("isSecret", request.getParameter("isSecret"));//on or null
//		model.addAttribute("qnaTitle", request.getParameter("qnaTitle"));
//		model.addAttribute("qnaContent", request.getParameter("qnaContent"));
	}

}
