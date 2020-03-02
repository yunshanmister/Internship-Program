package com.yunshan.mapper;

import com.yunshan.pojo.Order;

import java.util.List;


public interface OrderMapper {

	//保存订单对象
	void saveOrder(Order order);
	//展示订单页面
	List<Order> getOrdersByUseId(int userId);


}