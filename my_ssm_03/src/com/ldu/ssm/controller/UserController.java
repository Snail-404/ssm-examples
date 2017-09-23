package com.ldu.ssm.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ldu.ssm.service.UserService;

@Controller
public class UserController {
	public UserController(){
		System.out.println("UserController..");
	}
	@Resource
	private UserService userService;
	
	@RequestMapping("/look")
	public String look(ModelMap model){
		model.addAttribute("list", userService.lookUsers());
		return "list.jsp";
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
