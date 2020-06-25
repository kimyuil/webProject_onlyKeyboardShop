package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.NoticeDto;

public interface INoticeBoardDao {

	ArrayList<NoticeDto> getNoticeBoardsList();

	int noticelistLength();

	int clickNoticeHit(@Param("nbId") String nbId);

	NoticeDto getNoticeContent(@Param("nbId") String nbId);

	int insertNoticeBoard(NoticeDto notice);

	int modifyNotice(@Param("nbId") String nbId, @Param("nbTitle") String nbTitle,@Param("nbContent") String nbContent);

	int deleteNotice(@Param("nbId") String nbId);
	
	

}
