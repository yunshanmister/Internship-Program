package com.yunshan.controller;

import javax.servlet.http.HttpSession;

import com.yunshan.pojo.User;
import com.yunshan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//登录请求
	@RequestMapping("userLogin")
	public String userLogin(User user, Model model, HttpSession session) {
		
		//根据用户输入的账号密码获取用户信息
		User u=userService.findUserByNameAndPass(user);
		
		//判断是否找到这个用户信息
		if (u==null) {
			//不存在，提示错误消息，页面不能发生跳转
			model.addAttribute("error_message", "您输入的账号或密码不正确");
			return "login";
			//未激活用户
		}else if (u.getDisabled().equals("0")) {
			model.addAttribute("error_message", "你尚未激活，请打开您的邮箱进行激活操作");
			return "login";
		}else {
			//当用户存在，跳转至首页。并将用户信息传到域中
			session.setAttribute("session_user", u);
			return "redirect:/article/index";
		}
	}
	
	//退出登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
			
		//将用户信息用session中移除
		session.removeAttribute("session_user");
		
		//重定向到首页
		return "redirect:/article/index";
	}
	
	//异步校验用户账户是否存在
	@ResponseBody
    @RequestMapping(value="/validLoginName",produces= {"allpication/text;charset=utf-8"})
	public String validLoginName(String loginName) {
		
		//校验账户是否存在
		String result=userService.validLoginName(loginName);
		
		return result;	
	}
	
	//用户注册
	@RequestMapping("/userRegister")
	public String userRegister(Model model,User user,String pa) {
		//注册用户
		try {

			userService.saveUser(user);
			model.addAttribute("message", "注册成功,请先到邮箱激活再进行登录");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "注册失败，请返回登录页面重新注册");
		}
		
		return "login";
	}
	
	//用户信息激活
	@RequestMapping("/active")
	public String active(Model model,String activeCode) {
		//用户激活，返回消息
		try {
			String message=userService.active(activeCode);
			model.addAttribute("message",!message.equals("")?message:"激活成功");
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("message", "激活失败");
		}
		
		return "login";
	}
}
