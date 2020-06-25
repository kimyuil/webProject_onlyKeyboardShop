package com.spring.webProject.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.UserDto;

public interface IUserDao {

	void joinUser(UserDto user);
	
	String idCheck(String id);
	
	String loginAction(String uId, String uPw);

	String findId(String email, String name);

	String findPw(String email, String id);
	
	void renewPw(@Param("id") String id,@Param("pw") String pw);

	String userCheck(@Param("id") String id,@Param("pw") String pw);

	int deleteUser(@Param("id") String uId);

	int modifyUserInfo(@Param("uId") String uId,@Param("uPw") String uPw,@Param("uEmail") String uEmail,
			@Param("uAdress") String uAdress,@Param("uPhone") String uPhone);
}
