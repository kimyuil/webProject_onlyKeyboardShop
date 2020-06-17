package com.spring.webProject.dto;

public class PageDto {
	public static int pageCount; //한페이지에 보일 게시물수..
	public static int blockSize; //123 -> 456 -> 이런식으로 3개.. 블럭.
    private int blockStartNum = 0; //블럭의 첫숫자
    private int blockLastNum = 0; // 블럭의 마지막숫자.
    private int lastPageNum = 0; // 전체 통틀어 마지막 페이지숫자..(게시물수)
    private int realLastBlockNum=0;
    
    private int currentPage = 1; // 현재페이지
    private int currentPageFirstNum = 0; // 현재페이지 첫 게시글 인덱스
    private int currentPageLastNum = 4; // 현재페이지 마지막 게시글 인덱스
    
    public PageDto(int pageCount, int blockSize){ //이걸 final 안해도 될까???
    	this.pageCount=pageCount;
    	this.blockSize=blockSize;
    }

    
	public static int getPageCount() {
		return pageCount;
	}

	public static void setPageCount(int pageCount) {
		PageDto.pageCount = pageCount;
	}

	public static int getBlockSize() {
		return blockSize;
	}

	public static void setBlockSize(int blockSize) {
		PageDto.blockSize = blockSize;
	}

	public int getBlockStartNum() {
		return blockStartNum;
	}

	public void setBlockStartNum(int blockStartNum) {
		this.blockStartNum = blockStartNum;
	}

	public int getBlockLastNum() {
		return blockLastNum;
	}

	public void setBlockLastNum(int blockLastNum) {
		this.blockLastNum = blockLastNum;
	}

	public int getLastPageNum() {
		return lastPageNum;
	}

	public void setLastPageNum(int lastPageNum) {
		this.lastPageNum = lastPageNum;
	}

	public int getRealLastBlockNum() {
		return realLastBlockNum;
	}

	public void setRealLastBlockNum(int realLastBlockNum) {
		this.realLastBlockNum = realLastBlockNum;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getCurrentPageFirstNum() {
		return currentPageFirstNum;
	}

	public void setCurrentPageFirstNum(int currentPageFirstNum) {
		this.currentPageFirstNum = currentPageFirstNum;
	}

	public int getCurrentPageLastNum() {
		return currentPageLastNum;
	}

	public void setCurrentPageLastNum(int currentPageLastNum) {
		this.currentPageLastNum = currentPageLastNum;
	}
    

}
