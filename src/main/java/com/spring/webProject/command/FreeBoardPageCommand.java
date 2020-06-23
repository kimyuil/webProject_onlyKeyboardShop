package com.spring.webProject.command;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IFreeBoardDao;
import com.spring.webProject.dao.IReviewDao;
import com.spring.webProject.dto.PageDto;

public class FreeBoardPageCommand implements ICommand {
	
	int page;
	
	public FreeBoardPageCommand(int p) {
		// TODO Auto-generated constructor stub
		page = p;
	}
	
	@Override
	public void execute(SqlSession sqlSession, Model model) throws Exception {
		// TODO Auto-generated method stub
		IFreeBoardDao dao = sqlSession.getMapper(IFreeBoardDao.class);
		
		int totalNum = dao.listLength();
		
		PageDto pageInfo=new PageDto(8,3); //자유게시판 8개씩 3블록으로 표시
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
