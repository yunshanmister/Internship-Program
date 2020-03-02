package com.yunshan.service;

import com.yunshan.pojo.Shopcar;

import java.util.List;

import javax.servlet.http.HttpSession;



public interface ShopCarService {
	
	//将商品加入购物车
	void addArticleToShopCar(HttpSession session, int id, int number);

	//展示购物车详情
	List<Shopcar> getAllShopCarByUserId(HttpSession session);
	
	//更新商品信息
	void updateShopcar(HttpSession session, int id, int number);
	
	//删除购物车中的商品信息
	void deleteShopcar(HttpSession session, int id);

}
