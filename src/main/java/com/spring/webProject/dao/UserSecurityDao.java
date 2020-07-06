package com.spring.webProject.dao;

import java.sql.SQLException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.spring.webProject.dto.UserDto;
import com.spring.webProject.dto.UserSecurityDto;

@Repository("UserSecurityDao")
public class UserSecurityDao {
	 
	@Autowired
	 private SqlSessionTemplate sqlSession;
	 
	public UserSecurityDto getUserById(String username) throws Exception {
		
		UserSecurityDto user = sqlSession.selectOne("user.selectUserById", username);
		
	    return user;
	}
}
