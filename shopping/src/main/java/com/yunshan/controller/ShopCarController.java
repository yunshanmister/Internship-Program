package com.yunshan.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.yunshan.pojo.Shopcar;
import com.yunshan.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/shopCar")
public class ShopCarController {
	
	@Autowired
	private ShopCarService shopCarService;
	
	/**
	 * 添加商品至购物车
	 * @param session获取用户信息
	 * @param id 商品id
	 * @param number 需要新增多少数量
	 * @return
	 */
	@RequestMapping("/addToCar.action")
	public String addShopCar(HttpSession session,int id,int number) {
		//根据用户信息，商品id，已存在数量来添加商品至购物车
		shopCarService.addArticleToShopCar(session,id,number);
		
		//商品加入购物车成功之后，展示购物车中的商品信息。跳转到下面的方法
		return "redirect:/shopCar/showShopCar.action";	
	}

	//展示购物车详情
	@RequestMapping("/showShopCar.action")
	public String showShopCars(HttpSession session,Model model) {
		
		//根据已登录用户的Id获取该用户的购物车详情
		List<Shopcar> shopcars=shopCarService.getAllShopCarByUserId(session);
		model.addAttribute("shopCars", shopcars);
		
		//计算商品总金额
		double totalPrice=0.0;
		//遍历购物车商品，计算总金额
		for (Shopcar shopcar:shopcars) {
			totalPrice+=shopcar.getArticle().getDiscountPrice()*shopcar.getBuynum();
		}
		model.addAttribute("totalPrice", totalPrice);
		
		return "shopCar";
	}
	
	
	/**
	 * 更新购物车中商品信息，实现加减法
	 * @param session
	 * @param id
	 * @param number购物车中现有的商品数量
	 * @return
	 */
	@RequestMapping("/updateShopcar.action")
	public String updateShopcar(HttpSession session,int id,int number) {
		//更新购物车中商品数量
		shopCarService.updateShopcar(session,id,number);
		
		//商品加入购物车成功之后，展示购物车中的商品信息
		return "redirect:/shopCar/showShopCar.action";	
	}
	
	//删除购物车中商品的信息
	@RequestMapping("/deleteShopCar.action")
	public String deleteShopCar(HttpSession session,int id) {
		//删除购物车中的商品
		shopCarService.deleteShopcar(session,id);
		
		return "redirect:/shopCar/showShopCar.action";	
	}
	
}
