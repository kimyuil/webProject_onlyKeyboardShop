package com.spring.webProject.dto;

import java.sql.Timestamp;

public class PurchaseListDto {
	
	public static String purchased = "purchased";
	public static String shipping = "shipping";
	public static String delivered = "delivered";
	// pImageRoute,
	//purTime, state, uName, uAdress, uPhone, purMessage, pNum
	
	int purId;
	String userId;
	int productId;
	String pName;
	String pColor;
	String pImageRoute;
	Timestamp purTime;//¾Æ¸¶?
	String state;//purchased shipping delivered
	String uName;
	String uAdress;
	String uPhone;
	String purMessage;
	int pNum;
	
	public int getPurId() {
		return purId;
	}
	public void setPurId(int purId) {
		this.purId = purId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
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
	public String getpImageRoute() {
		return pImageRoute;
	}
	public void setpImageRoute(String pImageRoute) {
		this.pImageRoute = pImageRoute;
	}
	public Timestamp getPurTime() {
		return purTime;
	}
	public void setPurTime(Timestamp purTime) {
		this.purTime = purTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getuName() {
		return uName;
	}
	public void setuName(String uName) {
		this.uName = uName;
	}
	public String getuAdress() {
		return uAdress;
	}
	public void setuAdress(String uAdress) {
		this.uAdress = uAdress;
	}
	public String getuPhone() {
		return uPhone;
	}
	public void setuPhone(String uPhone) {
		this.uPhone = uPhone;
	}
	public String getPurMessage() {
		return purMessage;
	}
	public void setPurMessage(String purMessage) {
		this.purMessage = purMessage;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	
	

}
