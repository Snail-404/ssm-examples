package com.ldu.crud.test;

import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ldu.crud.bean.Department;
import com.ldu.crud.bean.Employee;
import com.ldu.crud.dao.DepartmentMapper;
import com.ldu.crud.dao.EmployeeMapper;

/**
 * 测试dao层的工作
 * @author Administrator
 *推荐Spring的项目就可以使用Spring的单元测试，可以自动注入我们需要的组件
 *1、导入SpringTest模块
 *2、@ContextConfiguration指定Spring配置文件的位置
 *3、直接autowired要使用的组件即可
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:beans.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	SqlSession sqlSession;
	
	@Test
	public void testCRUD(){
		System.out.println(departmentMapper);
		
		//1. 插入部门信息管理
		//departmentMapper.insertSelective(new Department(null,"开发部"));
		//departmentMapper.insertSelective(new Department(65,"测试部"));
		
		//2. 插入员工信息
		//employeeMapper.insertSelective(new Employee(1,"Tom","m","Tomwork@163.com",1));
		//employeeMapper.insertSelective(new Employee(2,"Jerry","f","Tomwork@163.com",2));
		//3. 批量插入员工信息
		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for (int i = 0; i < 30; i++) {
			String uid = UUID.randomUUID().toString().substring(0, 5)+i; 
			mapper.insertSelective(new Employee(null,uid,"m",uid+"work@163.com",1));
		}
		
		//4. 查询员工信息
		/*List<Employee> eList=employeeMapper.selectByExample(null);
		
		for (int i = 0; i < eList.size(); i++) {
			System.out.println(eList.get(i).getEmpName());
		}*/
		/*for (Employee employee : eList) {
			System.out.println(employee.getEmail());
		}*/
		
		/*Iterator<Employee> it=eList.iterator();
		while(it.hasNext()){
			System.out.println(it.next().getdId());
		}*/
		
		//5. 查询员工加部门信息
		/*List<Employee> list=employeeMapper.selectByExampleWithDept(null);
		for (Employee employee : list) {
			System.out.println(employee);
		}*/
		
		//6. 按id查询
		/*Employee emp=employeeMapper.selectByPrimaryKeyWithDept(33);
		System.out.println(emp);*/
		
		//7. 删除员工
		/*int c=employeeMapper.deleteByPrimaryKey(2);
		System.out.println(c);*/
		
		//8. 修改员工信息
		/*int count = employeeMapper.updateByPrimaryKeySelective(new Employee(3, "kkk", "m", null, 1));
		System.out.println(count);*/
		
		//9. 删除所有
		/*int cc=employeeMapper.deleteByExample(null);
		System.out.println(cc);*/
	}
}
