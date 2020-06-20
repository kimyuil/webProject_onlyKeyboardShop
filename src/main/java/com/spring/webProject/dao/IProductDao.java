package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.ProductDto;

public interface IProductDao {
	public ArrayList<ProductDto> listProduct(String category);
	public ProductDto getProduct(String pId);
	public int checkStockAddSelling(@Param("pId") String pid,@Param("pNum") String pnum);

} 
