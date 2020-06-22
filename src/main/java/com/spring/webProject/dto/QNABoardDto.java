package com.spring.webProject.dto;

import java.util.Date;

public class QNABoardDto {
	
	int qnaId; 
	String uId; 
	String uName;
	int pId;
	String pName;
	String qnaTitle; 
	String qnaContent; 
	Date qnaDate; 
	int isSecret; //0은 비밀글아님 1은 비밀글 
	int isAnswered; //0은 답변안달림 1은 답변달림
	String qnaAnswer;
	
	public int getQnaId() {
		return qnaId;
	}
	public void setQnaId(int qnaId) {
		this.qnaId = qnaId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaContent() {
		return qnaContent;
	}
	public void setQnaContent(String qnaContent) {
		this.qnaContent = qnaContent;
	}
	public Date getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(Date qnaDate) {
		this.qnaDate = qnaDate;
	}
	public int getIsSecret() {
		return isSecret;
	}
	public void setIsSecret(int isSecret) {
		this.isSecret = isSecret;
	}
	public int getIsAnswered() {
		return isAnswered;
	}
	public void setIsAnswered(int isAnswered) {
		this.isAnswered = isAnswered;
	}
	public String getQnaAnswer() {
		return qnaAnswer;
	}
	public void setQnaAnswer(String qnaAnswer) {
		this.qnaAnswer = qnaAnswer;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	
	
	
	
}