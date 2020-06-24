package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.FreeBoardDto;

public interface IFreeBoardDao {

	ArrayList<FreeBoardDto> getBoardsList();

	int listLength();

	FreeBoardDto getBoardContent(@Param("fbId") String fbId);

	int insertFreeboard(FreeBoardDto board);

	int clickBoardHit(@Param("fbId") String fbId);

	int likePlus(@Param("fbId")String fbId);
	
	int likeMinus(@Param("fbId")String fbId);

	int modifyFreeBoard(@Param("fbId") String fbId,@Param("fbTitle") String fbTitle, 
			@Param("fbContent") String fbContent);

	int deleteFreeBoard(@Param("fbId") String fbId);
	
	

}
