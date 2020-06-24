package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.FreeCommentDto;

public interface IFreeCommentDao {

	ArrayList<FreeCommentDto> FreeboardCommentsList(@Param("fbId") String fbId);

}
