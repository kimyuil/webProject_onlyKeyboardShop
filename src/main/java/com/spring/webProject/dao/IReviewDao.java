package com.spring.webProject.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.spring.webProject.dto.ReviewBoardDto;

public interface IReviewDao {
	public int writeReivew();
	public ArrayList<ReviewBoardDto> listReview(@Param("pId")String pid);
	public int modifyReivew();
	public int deleteReivew();
	public int listLength(@Param("pid")String pid);
	
	 
}
