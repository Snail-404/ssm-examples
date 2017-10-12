package com.ldu.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ldu.crud.bean.Department;
import com.ldu.crud.dao.DepartmentMapper;

@Service
public class DepartmentService {
	@Autowired
	private DepartmentMapper departmentMapper;
	
	/**
	 * 查询所有部门信息
	 */
	public List<Department> getDepts() {
		return departmentMapper.selectByExample(null);
	}

}
