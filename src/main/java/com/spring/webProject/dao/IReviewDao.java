package com.spring.webProject.dao;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.spring.webProject.dto.ReviewBoardDto;

public interface IReviewDao {
	public int writeReivew(@Param("pId") String pId,@Param("uId") String uId,
			@Param("pName") String pName,	@Param("pColor") String pColor,
			@Param("uName") String uName,@Param("reGrade") String reGrade,@Param("reContent") String reContent);
	public ArrayList<ReviewBoardDto> listReview(@Param("pId")String pid);
	public ArrayList<ReviewBoardDto> userListReview(@Param("uId")String uId);
	public int modifyReivew();
	public int deleteReivew();
	public int listLength(@Param("pid")String pid);
	
	
	
	 
}
