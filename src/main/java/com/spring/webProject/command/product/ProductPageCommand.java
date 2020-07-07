package com.spring.webProject.command.product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.command.ICommand;
import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dto.ProductDto;

public class ProductPageCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		IProductDao dao = sqlSession.getMapper(IProductDao.class);
		
		Map<String, Object> map = model.asMap();
				
		ProductDto product = dao.getProduct((String) map.get("pId"));
		String[] colors = product.getpColors().split(",");
		
		model.addAttribute("colorsArray", colors);
		model.addAttribute("product", product);
	}

}
