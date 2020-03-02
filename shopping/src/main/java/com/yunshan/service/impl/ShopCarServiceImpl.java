package com.yunshan.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.yunshan.mapper.ShopcarMapper;
import com.yunshan.pojo.Shopcar;
import com.yunshan.pojo.User;
import com.yunshan.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@Transactional
public class ShopCarServiceImpl implements ShopCarService {
	
	@Autowired
	private ShopcarMapper shopcarMapper;

	//将商品加入购物车
	@Override
	public void addArticleToShopCar(HttpSession session, int id, int number) {
		
		//从session中获取用户信息
		User user=(User) session.getAttribute("session_user");
		
		//获取用户的id
		int userId=user.getId();
		
		//根据用户id和以及商品id搜索数据库，判断该用户的购物车中是否已经存在该商品
		Shopcar shopcar=shopcarMapper.getShopCarByUserIdAndArticleId(userId,id);
		
		if (shopcar!=null) {
			//不为空，证明已经存在于购物车，故只应增加数量
			shopcarMapper.updateShopCar(userId,id,number);
		}else {
			//该商品不存在于购物车中，应当添加该商品
			shopcarMapper.addShopCar(userId,id,number);
		}
		
	}
	
	//展示购物车详情
	@Override
	public List<Shopcar> getAllShopCarByUserId(HttpSession session) {
		//获取已登录的用户id
		User user=(User) session.getAttribute("session_user");
		int userId=user.getId();
		//根据Id 获取用户的购物车详情
		List<Shopcar> shopcars=shopcarMapper.getAllShopCarByUserId(userId);
		return shopcars;
	}
	
	//更新商品信息
	@Override
	public void updateShopcar(HttpSession session, int id, int number) {
		//获取已登录的用户id
		User user=(User) session.getAttribute("session_user");
		int userId=user.getId();
		
		shopcarMapper.updateShopcar(userId,id,number);
		
	}

	@Override
	public void deleteShopcar(HttpSession session, int id) {
		//获取已登录的用户id
		User user=(User) session.getAttribute("session_user");
		int userId=user.getId();
		
		shopcarMapper.deleteShopcar(userId,id);
		
	}

}
