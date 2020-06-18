package com.spring.webProject.command.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.webProject.dao.UserSecurityDao;
import com.spring.webProject.dto.UserDto;
import com.spring.webProject.dto.UserSecurityDto;

public class UserDetailsCommand implements UserDetailsService {
	
	@Autowired
    private UserSecurityDao UserSecurityDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("security command");
		// TODO Auto-generated method stub
		UserSecurityDto user;
		try {
			user = UserSecurityDao.getUserById(username);
			
			if(user==null) {
				
				throw new UsernameNotFoundException(username);
			}
			else {
				
				return user;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
		
	}

}

