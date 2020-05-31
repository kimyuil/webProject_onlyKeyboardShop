package com.spring.webProject.dto;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSecurityDto implements UserDetails {
	
	String uId;
	String uPw;
	String uName;
	String uPhone;//- 들어와도 빼야함
	String uBirth;//YYYY-MM-DDs string
	String uGender; //남자 or 여자 
	String uEmail;
	String uAdress;
	int uIsPerchased; //구입여부
	int uIsReceived; //받음여부
	String uDelivery; //배송조회
	String uInterestedList; //관심목록 ;로 구분해서 추가 및 제거 manager 필요
	//int uisAdmin; //0은 일반user 1은 admin
	int enabled;
	String authority;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
	        auth.add(new SimpleGrantedAuthority(authority));
	        return auth;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return uPw;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return uId;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		if(enabled==1) 
			return true;
		else
			return false;
	}
	
	
	
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuPw() {
		return uPw;
	}
	public void setuPw(String uPw) {
		this.uPw = uPw;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getuBirth() {
		return uBirth;
	}
	public void setuBirth(String uBirth) {
		this.uBirth = uBirth;
	}
	public String getuGender() {
		return uGender;
	}
	public void setuGender(String uGender) {
		this.uGender = uGender;
	}
	public String getuEmail() {
		return uEmail;
	}
	public void setuEmail(String uEmail) {
		this.uEmail = uEmail;
	}
	public String getuAdress() {
		return uAdress;
	}
	public void setuAdress(String uAdress) {
		this.uAdress = uAdress;
	}
	public int getuIsPerchased() {
		return uIsPerchased;
	}
	public void setuIsPerchased(int uIsPerchased) {
		this.uIsPerchased = uIsPerchased;
	}
	public int getuIsReceived() {
		return uIsReceived;
	}
	public void setuIsReceived(int uIsReceived) {
		this.uIsReceived = uIsReceived;
	}
	public String getuDelivery() {
		return uDelivery;
	}
	public void setuDelivery(String uDelivery) {
		this.uDelivery = uDelivery;
	}
	public String getuInterestedList() {
		return uInterestedList;
	}
	public void setuInterestedList(String uInterestedList) {
		this.uInterestedList = uInterestedList;
	}
//	public int getEnabled() { isEnabled 메소드와 겹치니 익셉션을 던지더라.. ㅜㅜ
//		return enabled;
//	}
//	public void setEnabled(int enabled) {
//		this.enabled = enabled;
//	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	

}
