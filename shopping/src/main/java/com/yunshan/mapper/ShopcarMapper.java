package com.yunshan.mapper;

import java.util.List;

import com.yunshan.pojo.Shopcar;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



public interface ShopcarMapper {
	
	//通过用户id和商品id查询数据库，判断购物车是否存在该商品
	@Select("select * from ec_shopcar where user_id = #{userId} and article_id=#{id}")
	Shopcar getShopCarByUserIdAndArticleId(@Param("userId") int userId, @Param("id") int id);
	
	//存在，增加数量
	@Update("update ec_shopcar set buynum = #{number} + buynum where user_id = #{userId} and article_id=#{id}")
	void updateShopCar(@Param("userId") int userId, @Param("id") int id, @Param("number") int number);
	
	//不存在，加入购物车
	@Insert("insert into ec_shopcar(buynum,user_id,article_id) values(#{number},#{userId},#{id})")
	void addShopCar(@Param("userId") int userId, @Param("id") int id, @Param("number") int number);

	//展示购物车详情
	List<Shopcar> getAllShopCarByUserId(int userId);
	
	//更新商品信息
	@Update("update ec_shopcar set buynum = #{number}  where user_id = #{userId} and article_id=#{id}")
	void updateShopcar(@Param("userId") int userId, @Param("id") int id, @Param("number") int number);
	
	//删除购物车中的商品
	@Delete("delete from ec_shopcar where user_id = #{userId} and article_id=#{id}")
	void deleteShopcar(@Param("userId") int userId, @Param("id") int id);








	


}