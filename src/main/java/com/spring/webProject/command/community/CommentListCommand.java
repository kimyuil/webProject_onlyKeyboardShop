package com.spring.webProject.command.community;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeCommentDao;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.FreeCommentDto;

public class CommentListCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
		
		ArrayList<FreeCommentDto> temp = new ArrayList<FreeCommentDto>(),
				comments = new ArrayList<FreeCommentDto>(),
				recomments = new ArrayList<FreeCommentDto>();
		
		Map<String, Object> map = model.asMap();
		String fbId = (String)map.get("fbId");
		temp = dao.FreeboardCommentsList(fbId);
		
		for(int i=0;i<temp.size();i++) {
			if(temp.get(i).getIsReplyComment()==0)
				comments.add(temp.get(i));
			else if(temp.get(i).getIsReplyComment()==1)
				recomments.add(temp.get(i));
		}
		
		model.addAttribute("comments", comments);
		model.addAttribute("recomments", recomments);
		
	}

}
