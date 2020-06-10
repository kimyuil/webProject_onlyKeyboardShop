package com.spring.webProject.dao;

import org.apache.ibatis.annotations.Param;

public interface IPurchaseListDao {

	public void purchaseItems(@Param("uId") String uId,@Param("uName")  String uName,@Param("uAdress")  String uAdress,
			@Param("uPhone") String uPhone,@Param("deliverMessage") String deliverMessage,@Param("pId")  String pId,
			@Param("pName") String pName,@Param("pColor")  String pColor,@Param("pImage")  String pImage,
			@Param("pNumof")  String pNumof, @Param("state")String state);

}
