package com.ldu.ssm.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ldu.ssm.dao.UserDao;
import com.ldu.ssm.po.User;
import com.ldu.ssm.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	private UserDao userDao=new UserDao();
	public List<User> lookUsers(){
		return userDao.selectList();
	}
}
