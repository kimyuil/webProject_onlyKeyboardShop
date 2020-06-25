package com.spring.webProject.dao;

import java.util.ArrayList;

import com.spring.webProject.dto.NoticeDto;

public interface INoticeBoardDao {

	ArrayList<NoticeDto> getNoticeBoardsList();

	int noticelistLength();
	

}
