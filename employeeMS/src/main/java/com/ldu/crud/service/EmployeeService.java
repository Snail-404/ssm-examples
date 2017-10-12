package com.ldu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldu.crud.bean.Employee;
import com.ldu.crud.bean.EmployeeExample;
import com.ldu.crud.bean.EmployeeExample.Criteria;
import com.ldu.crud.dao.EmployeeMapper;

@Service
public class EmployeeService {
	
	@Autowired
	EmployeeMapper employeeMapper;
	
	/**
	 * 查询所有员工信息（包括部门信息）
	 * @return
	 */
	public List<Employee> getAll(){
		return employeeMapper.selectByExampleWithDept(null);
	}

	/**
	 * 将员工信息存入数据库
	 * @param employee
	 */
	public void addEmp(Employee employee) {
		employeeMapper.insertSelective(employee);
	}

	//查找是否有相同的用户名
	public boolean checkName(String empName) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpNameEqualTo(empName);
		long count=employeeMapper.countByExample(example);
		//System.out.println(count);
		return count==0;
	}

	/**
	 * 查询出要修改的员工的信息
	 * @param id
	 * @return
	 */
	public Employee getEmp(Integer id) {
		Employee employee=employeeMapper.selectByPrimaryKey(id);
		return employee;
	}

	//修改员工信息
	public void updateEmp(Employee employee) {
		employeeMapper.updateByPrimaryKeySelective(employee);
	}

	//删除单个员工信息
	public void delEmp(Integer id) {
		employeeMapper.deleteByPrimaryKey(id);
	}

	//批量删除员工信息
	public void delEmps(List<Integer> del_ids) {
		EmployeeExample example=new EmployeeExample();
		Criteria criteria=example.createCriteria();
		criteria.andEmpIdIn(del_ids);
		employeeMapper.deleteByExample(example);
	}
}
