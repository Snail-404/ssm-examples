package com.ldu.ssm.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import com.ldu.ssm.mapper.UserMapper;
import com.ldu.ssm.po.User;
import com.ldu.ssm.utils.DBFactory;

@Component("userDao")
public class UserDao{
	SqlSession session=DBFactory.getSqlSession();
	UserMapper mapper=session.getMapper(UserMapper.class);
	
	public List<User> selectList(){
		
		List<User> list = mapper.lookUsers();
		for(User u:list){
			System.out.println(u);
		}
		return list;
	}
}
