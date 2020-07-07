package com.spring.webProject.command.membership;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
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
		else { //반환된 아이디의 가운데 절반부분을 *로 표시해서 리턴해주는 과정.
			int length = resultId.length();
			int lastIndex = length-1;
			int changeAmount;
			
			if(length/2 == 0) 
				changeAmount = length/2;  
			else
				changeAmount = (int)(length/2) + 1;  
			
					
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
