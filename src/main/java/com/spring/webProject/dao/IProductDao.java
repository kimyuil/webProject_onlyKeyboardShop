package com.spring.webProject.dao;

import java.util.ArrayList;

import com.spring.webProject.dto.ProductDto;

public interface IProductDao {
	public ArrayList<ProductDto> listProduct(String category);
} 
