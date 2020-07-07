package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeCommentDao;

public class DeleteCommentByFreeboardCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws RuntimeException {
		// TODO Auto-generated method stub
		IFreeCommentDao dao = sqlSession.getMapper(IFreeCommentDao.class);
		
		Map<String, Object> map = model.asMap();
		dao.deleteCommentByFreeboard((String) map.get("fbId"));
		return;
		//게시판을 삭제하면서 삭제된 댓글 개수가 여러개 or 0개일 수 있음
	}

}
