package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IUserDao;

public class AddBookmarkCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		//IUserDao dao = sqlSession.getMapper(IUserDao.class);
		IProductDao dao = sqlSession.getMapper(IProductDao.class);
		
		Map<String, Object> map = model.asMap();
		String uId = (String) map.get("uId");
		String pId = (String) map.get("pId");
		String pIdAdd = pId + ";";
		
		//중복검사
		String bookmark = dao.getBookmarkList(uId);
		System.out.println("북마크리스트받아온것 "+bookmark);
		
		if(bookmark != null) {
			String bookmarkList[] = bookmark.split(";");
		
			for(int i=0;i<bookmarkList.length;i++) { 
				if(pId.equals(bookmarkList[i])) {
					model.addAttribute("result", "alreadyAdded");
					return;
				}
			}
		
			pIdAdd+=bookmark;
		}
		
		try {
		dao.addBookmark(uId, pIdAdd);
		model.addAttribute("result", "success");
		}
		catch(Exception e) {
			System.out.println("관심목록 추가 예외발생");
			model.addAttribute("result", "fail. ask to manager");
		}
		
		
	}

}
