package com.yunshan.mapper;

import com.yunshan.pojo.OrderItem;
import org.apache.ibatis.annotations.Insert;



public interface OrderItemMapper {

	//保存订单详情对象
	@Insert("insert into ec_order_item(order_id,article_id,order_num) values(#{orderId},#{articleId},#{orderNum}) ")
	void saveItem(OrderItem item);



}