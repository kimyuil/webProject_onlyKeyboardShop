package com.spring.webProject.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import com.spring.webProject.dto.PurchaseListDto;
import com.spring.webProject.dto.QNABoardDto;

public interface IPurchaseListDao {

	public int purchaseItems(@Param("uId") String uId,@Param("uName")  String uName,@Param("uAdress")  String uAdress,
			@Param("uPhone") String uPhone,@Param("deliverMessage") String deliverMessage,@Param("pId")  String pId,
			@Param("pName") String pName,@Param("pColor")  String pColor,@Param("pImage")  String pImage,
			@Param("pNumof")  String pNumof, @Param("state")String state);

	public ArrayList<PurchaseListDto> getUserPurchaseList(@Param("uId") String uId);
	public int userCheckDelivery(@Param("purId") String purId);

	public int changeUserState(@Param("purId") String purId);
	public String getStateByPurId(@Param("purId") String purId);

	public int reChangeUserState(@Param("purId") String purId); //when delete

	public ArrayList<PurchaseListDto> getPurchaseListAdmin();

	public int adminSendItem(@Param("purId") String purId);

	public int adminShippedItem(@Param("purId") String purId);


}
