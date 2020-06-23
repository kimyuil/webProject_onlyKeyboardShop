package com.spring.webProject.dto;

import java.sql.Timestamp;

public class FreeBoardDto {
	int fbId; 
	String fbTitle; 
	String fbContent; 
	Timestamp fbTime; 
	String uId; 
	String uName; 
	int fbHit; 
	int fbReplys; 
	int fbgood;
	
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public String getFbTitle() {
		return fbTitle;
	}
	public void setFbTitle(String fbTitle) {
		this.fbTitle = fbTitle;
	}
	public String getFbContent() {
		return fbContent;
	}
	public void setFbContent(String fbContent) {
		this.fbContent = fbContent;
	}
	public Timestamp getFbTime() {
		return fbTime;
	}
	public void setFbTime(Timestamp fbTime) {
		this.fbTime = fbTime;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public int getFbHit() {
		return fbHit;
	}
	public void setFbHit(int fbHit) {
		this.fbHit = fbHit;
	}
	public int getFbReplys() {
		return fbReplys;
	}
	public void setFbReplys(int fbReplys) {
		this.fbReplys = fbReplys;
	}
	public int getFbgood() {
		return fbgood;
	}
	public void setFbgood(int fbgood) {
		this.fbgood = fbgood;
	}
	
	
	
}
