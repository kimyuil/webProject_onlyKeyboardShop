package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.FreeBoardDto;

public interface IFreeBoardDao {

	ArrayList<FreeBoardDto> getBoardsList();

	int listLength();

	FreeBoardDto getBoardContent(@Param("fbId") String fbId);

}
