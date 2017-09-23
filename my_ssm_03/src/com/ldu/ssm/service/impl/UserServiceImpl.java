package com.ldu.ssm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ldu.ssm.dao.UserDao;
import com.ldu.ssm.po.User;
import com.ldu.ssm.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	public UserServiceImpl(){
		System.out.println("UserServiceImpl..");
	}
	@Resource(name="userDao")
	private UserDao userDao;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> lookUsers(){
		return userDao.getUsers();
	}
}
