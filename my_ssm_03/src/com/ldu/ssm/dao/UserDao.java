package com.ldu.ssm.dao;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ldu.ssm.mapper.UserMapper;
import com.ldu.ssm.po.User;

@Component("userDao")
public class UserDao extends SqlSessionDaoSupport {
	public UserDao(){
		System.out.println("UserDao..");
	}
	
	@Resource
	@Override
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}
	
	private UserMapper mapper=null;
	private SqlSession sqlSession=null;
	public void before(){
		sqlSession=getSqlSession();
		mapper=sqlSession.getMapper(UserMapper.class);
		
	}
	public List<User> getUsers(){
		before();
		List<User> list = mapper.lookUsers();
		for(User u:list){
			System.out.println(u);
		}
		return list;
	}
	
	
	
}
