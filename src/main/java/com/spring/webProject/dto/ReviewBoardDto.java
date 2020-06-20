package com.spring.webProject.dto;

import java.sql.Timestamp;
import java.util.Date;

public class ReviewBoardDto {
//reId, pId, uId, pName, pColor, pDate, uName, reTitle, reContent, reGrade
	int reId;
	int purId;
	int pId;
	String uId;
	String pName;
	String pColor;
	String reDate; 
	String uName;
	String reContent; //200ÀÚ
	int reGrade; //ÆòÁ¡ 1~5
	
	
	public int getReId() {
		return reId;
	}
	public void setReId(int reId) {
		this.reId = reId;
	}
	
	public int getPurId() {
		return purId;
	}
	public void setPurId(int purId) {
		this.purId = purId;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getuId() {
		return uId;
	}
	public void setuId(String uId) {
		this.uId = uId;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getpColor() {
		return pColor;
	}
	public void setpColor(String pColor) {
		this.pColor = pColor;
	}
	public String getReDate() {
		return reDate;
	}
	public void setReDate(String reDate) {
		this.reDate = reDate;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public int getReGrade() {
		return reGrade;
	}
	public void setReGrade(int reGrade) {
		this.reGrade = reGrade;
	}
	
	
	
}
