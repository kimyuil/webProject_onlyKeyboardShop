package com.spring.webProject.dto;

import java.sql.Timestamp;

public class NoticeDto {

	int nbId;
	String nbTitle;
	String nbContent;
	Timestamp nbTime;
	int nbHit;
	
	public int getNbId() {
		return nbId;
	}
	public void setNbId(int nbId) {
		this.nbId = nbId;
	}
	public String getNbTitle() {
		return nbTitle;
	}
	public void setNbTitle(String nbTitle) {
		this.nbTitle = nbTitle;
	}
	public String getNbContent() {
		return nbContent;
	}
	public void setNbContent(String nbContent) {
		this.nbContent = nbContent;
	}
	public Timestamp getNbTime() {
		return nbTime;
	}
	public void setNbTime(Timestamp nbTime) {
		this.nbTime = nbTime;
	}
	public int getNbHit() {
		return nbHit;
	}
	public void setNbHit(int nbHit) {
		this.nbHit = nbHit;
	}
	
	
}
