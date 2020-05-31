package com.spring.webProject.dao;

import com.spring.webProject.dto.UserDto;

public interface IUserDao {

	void joinUser(UserDto user);
	
	String idCheck(String id);
	
	String loginAction(String uId, String uPw);
}
