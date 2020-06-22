package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.QNABoardDto;

public interface IQNADao {

	ArrayList<QNABoardDto> listQna(@Param("pId") String pId);

	int listLength(@Param("pId") String pId);
	
	
}
