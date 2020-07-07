package com.spring.webProject.command.community;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IFreeBoardDao;
@Transactional
public class DecreaseFreeboardReplysCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		Map<String, Object> map = model.asMap();
		String fbId = (String) map.get("fbId");
		int result = dao.decreaseReplys(fbId);
		
		if(result==1) return; //success
		else 
			throw new RuntimeException("freeboard decrease update error");

	}

}
