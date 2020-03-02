package com.yunshan.service;


import com.yunshan.pojo.User;

public interface UserService {
	
	//根据账户密码查询用户
	User findUserByNameAndPass(User user);
	
	//异步校验账户是否存在
	String validLoginName(String loginName);
	
	//用户注册
	void saveUser(User user);
	
	//用户激活
	String active(String activeCode);

}
