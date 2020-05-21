package com.spring.webProject.dto;

public class ProductDto {
	String pName;
	int pId;
	String pBreifComment;
	String pImageRoute;
	int pPrice;
	int pDeliveryPrice;
	String pExplainImageRoute;
	int pSellingNum;
	String category;
	int pIsSoldOut;//0 is flase 1 is true
	String pInformation;
	
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getpBreifComment() {
		return pBreifComment;
	}
	public void setpBreifComment(String pBreifComment) {
		this.pBreifComment = pBreifComment;
	}
	public String getpImageRoute() {
		return pImageRoute;
	}
	public void setpImageRoute(String pImageRoute) {
		this.pImageRoute = pImageRoute;
	}
	public int getpPrice() {
		return pPrice;
	}
	public void setpPrice(int pPrice) {
		this.pPrice = pPrice;
	}
	public int getpDeliveryPrice() {
		return pDeliveryPrice;
	}
	public void setpDeliveryPrice(int pDeliveryPrice) {
		this.pDeliveryPrice = pDeliveryPrice;
	}
	public String getpExplainImageRoute() {
		return pExplainImageRoute;
	}
	public void setpExplainImageRoute(String pExplainImageRoute) {
		this.pExplainImageRoute = pExplainImageRoute;
	}
	public int getpSellingNum() {
		return pSellingNum;
	}
	public void setpSellingNum(int pSellingNum) {
		this.pSellingNum = pSellingNum;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int ispIsSoldOut() {
		return pIsSoldOut;
	}
	public void setpIsSoldOut(int pIsSoldOut) {
		this.pIsSoldOut = pIsSoldOut;
	}
	public String getpInformation() {
		return pInformation;
	}
	public void setpInformation(String pInformation) {
		this.pInformation = pInformation;
	}
	
	
	
	
}
