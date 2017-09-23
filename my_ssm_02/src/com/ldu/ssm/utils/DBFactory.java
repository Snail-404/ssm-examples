package com.ldu.ssm.utils;

import java.io.IOException;
import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBFactory {
	private static  SqlSessionFactory sqlSessionFactory=null;
	
	static{
		try {
			String resource="mybatis.config.xml";
			InputStream inputStream=Resources.getResourceAsStream(resource);
			sqlSessionFactory=new SqlSessionFactoryBuilder().build(inputStream,"mysql");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static SqlSessionFactory getSqlSessionFactory(){
		
		
		return sqlSessionFactory;
	}
	
	public static SqlSession getSqlSession(){
		return sqlSessionFactory.openSession();
	}
}
