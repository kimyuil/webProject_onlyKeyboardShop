package com.spring.webProject.dto;

import java.sql.Timestamp;

public class FreeCommentDto {
	int cId; 
	int cParentId; 
	int fbId; 
	String cName; 
	String cPw; 
	String cComment;
	
	Timestamp cTime; 
	int IsReplyComment;
	
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public int getcParentId() {
		return cParentId;
	}
	public void setcParentId(int cParentId) {
		this.cParentId = cParentId;
	}
	public int getFbId() {
		return fbId;
	}
	public void setFbId(int fbId) {
		this.fbId = fbId;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcPw() {
		return cPw;
	}
	public void setcPw(String cPw) {
		this.cPw = cPw;
	}
	public String getcComment() {
		return cComment;
	}
	public void setcComment(String cComment) {
		this.cComment = cComment;
	}
	
	public Timestamp getcTime() {
		return cTime;
	}
	public void setcTime(Timestamp cTime) {
		this.cTime = cTime;
	}
	public int getIsReplyComment() {
		return IsReplyComment;
	}
	public void setIsReplyComment(int isReplyComment) {
		IsReplyComment = isReplyComment;
	}
	
	
}
