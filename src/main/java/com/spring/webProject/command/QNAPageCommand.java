package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IQNADao;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.PageDto;

public class QNAPageCommand implements ICommand {
	
	int page; 
	
	public QNAPageCommand(int p) {
		// TODO Auto-generated constructor stub
		page = p;
	}

	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IQNADao dao = sqlSession.getMapper(IQNADao.class);
		
		Map<String, Object> map = model.asMap();
		String pId = (String) map.get("pId");
	
		int totalNum = dao.listLength(pId);
		
		
		PageDto pageInfo=new PageDto(5,3); //후기게시판 5개씩 3블록으로 표시
		int pageStartNum = (page-1)*PageDto.pageCount;
		int pageLastNum = page*PageDto.pageCount -1;
		
		int blockStartNum = ((page-1)/PageDto.blockSize)*PageDto.blockSize+1;
		int blockLastNum = blockStartNum+PageDto.blockSize-1;
		
		int realLastBlockNum = totalNum/PageDto.pageCount +1;//마지막block숫자
		if(realLastBlockNum<=blockLastNum) {
			pageInfo.setBlockLastNum(realLastBlockNum);
		}
		else {
			pageInfo.setBlockLastNum(blockLastNum); 
		}
		
		if(pageLastNum<totalNum) { //일반적인 상황.
			pageInfo.setCurrentPageLastNum(pageLastNum);
		}
		else {//맨 끝 페이지
			pageInfo.setCurrentPageLastNum(totalNum);
		}
		
		
		pageInfo.setCurrentPage(page);
		pageInfo.setCurrentPageFirstNum(pageStartNum);
		pageInfo.setBlockStartNum(blockStartNum);
		pageInfo.setLastPageNum(totalNum);
		pageInfo.setRealLastBlockNum(realLastBlockNum);//이게 더 쓸모있는.
		
		model.addAttribute("pageInfo", pageInfo);

	}

}
