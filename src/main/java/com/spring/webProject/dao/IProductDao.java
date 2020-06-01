package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.ProductDto;

public interface IProductDao {
	public ArrayList<ProductDto> listProduct(String category);
	public ProductDto getProduct(String pId);
	public void addBookmark(@Param("uId") String uId, @Param("pIdAdd") String pIdAdd);
	public String getBookmarkList(@Param("uId") String uId);
	

} 
