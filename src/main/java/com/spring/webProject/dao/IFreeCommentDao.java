package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.FreeCommentDto;

public interface IFreeCommentDao {

	ArrayList<FreeCommentDto> FreeboardCommentsList(@Param("fbId") String fbId);

	int writeComment(@Param("fbId") String fbId,@Param("cName") String cName,@Param("cPw") String cPw,@Param("cComment") String cComment);
	
	int writeReComment(@Param("fbId") String fbId,@Param("cParentId") String cParentId,@Param("cName") String cName,@Param("cPw") String cPw,@Param("cComment") String cComment);

	String getCommentPwById(@Param("cId") String cId);

	int modifyComment(@Param("cId") String cId,@Param("cComment")  String cComment);

	int deleteComment(@Param("cId") String cId);

	int deleteCommentByFreeboard(@Param("fbId") String fbId);
	

}
