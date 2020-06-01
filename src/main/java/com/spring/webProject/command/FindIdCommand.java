package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IUserDao;

public class FindIdCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		
		IUserDao dao = sqlSession.getMapper(IUserDao.class);
		
		Map<String, Object> map = model.asMap();
		String email = (String)map.get("email");
		String name = (String)map.get("name");
		String resultId = dao.findId(email,name);
		
		if(resultId == null)
			model.addAttribute("resultId", "false");
		else {
			int length = resultId.length();
			int lastIndex = length-1;
			int changeAmount;
			
			if(length/2 == 0) //짝수는
				changeAmount = length/2; //그대로 
			else
				changeAmount = (int)(length/2) + 1; //홀수는 몫+1 
			
			//4이면 4/2한2를  1~2  6이면
			
			StringBuilder returnId = new StringBuilder(resultId);
						
			int startIndex= (int)(lastIndex/2)-(int)(length/4)+1;
			int change = startIndex+changeAmount-1;
						 
			for(int i=startIndex ; i<change ; i++ ) {
				returnId.setCharAt(i,'*');
			}
			
			
			model.addAttribute("resultId", returnId);
		}
	
	}

}
