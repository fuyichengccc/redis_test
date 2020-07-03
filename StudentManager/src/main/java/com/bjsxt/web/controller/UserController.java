package com.bjsxt.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bjsxt.pojo.Users;
import com.bjsxt.service.UserService;
///user/addUser
@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping("/addUser")
	public String addUser(Users	users) {
		this.userService.addUser(users);
		return "ok";
		
	}
	
	
	///user/findUser
	@RequestMapping("/findUser")
	public String findUserByID( Model model,int userid) {
		Users users = this.userService.findByUserid(userid);
		model.addAttribute("users",users);
		return "showUser";
		
	}
	
	///user/updateUser
	@RequestMapping("/updateUser")
	public String updateUser(Users users) {
		this.userService.updateUser(users);
		
		return "ok";
		
	}
}
