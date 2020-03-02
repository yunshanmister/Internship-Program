package com.yunshan.service;

import com.yunshan.pojo.Order;

import java.util.List;

import javax.servlet.http.HttpSession;


public interface OrderService {
	
	//提交订单
	void orderSubmit(String orderInfo, HttpSession session);
	
	//展示订单页面
	List<Order> getOrdersByUseId(HttpSession session);

}
