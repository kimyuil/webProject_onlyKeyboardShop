package com.spring.webProject.command;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dto.ProductDto;

public class ProductCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IProductDao dao = sqlSession.getMapper(IProductDao.class);
		
		Map<String, Object> map = model.asMap();
		String category = (String) map.get("category");
		category.toUpperCase();
		
		ArrayList<ProductDto> products = dao.listProduct(category);
		
		model.addAttribute("products", products);
	}

}
