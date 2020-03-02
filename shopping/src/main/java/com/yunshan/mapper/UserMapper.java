package com.yunshan.mapper;

import com.yunshan.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;



public interface UserMapper {
	//根据用户名密码获取用户信息
	@Select("select * from ec_user where login_name = #{loginName} and password = #{password}")
	User findUserByNameAndPass(User user);

	//异步校验账户是否存在
	@Select("select * from ec_user where login_name = #{loginName}")
	User validLoginName(String loginName);
	
	//注册
	@Insert("insert into ec_user(login_name,password,name,sex,email,phone,address,create_date,active) values(#{loginName},#{password},#{name},#{sex},#{email},#{phone},#{address},#{createDate},#{active})")
	void saveUser(User user);
	
	//根据激活码查找用户
	@Select("select * from ec_user where active = #{activeCode}")
	User getUserByActive(String activeCode);

	//激活用户,将disable字段设置为1，且清空激活码
	@Update("update ec_user set disabled = 1 ,active = '' where active = #{activeCode}")
	void active(String activeCode);

}