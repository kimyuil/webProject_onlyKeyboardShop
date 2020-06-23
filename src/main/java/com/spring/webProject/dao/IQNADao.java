package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.QNABoardDto;

public interface IQNADao {

	ArrayList<QNABoardDto> listQna(@Param("pId") String pId);

	int listLength(@Param("pId") String pId);

	int insertQna(QNABoardDto qna);

	int modifyQnA(@Param("qnaId") String qnaId,@Param("qnaTitle") String qnaTitle,
			@Param("qnaContent") String qnaContent, @Param("isSecret") String isSecret);

	int deleteQNA(@Param("qnaId") String qnaId);

	ArrayList<QNABoardDto> userListQnA(@Param("uId") String uId);
	
	
	
}
