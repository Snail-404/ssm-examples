package com.ldu.crud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ldu.crud.bean.Employee;
import com.ldu.crud.bean.Msg;
import com.ldu.crud.service.EmployeeService;

/**
 * 处理关于员工信息的crud
 * @author Administrator
 * 
 */

@Controller
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	
	
	@RequestMapping(value="/emp/{ids}",method=RequestMethod.DELETE)
	@ResponseBody
	public Msg  delEmp(@PathVariable("ids")String ids){
		if (ids.contains("-")) {
			List<Integer> del_ids=new ArrayList<Integer>();
			String []str_ids=ids.split("-");
			for (String str : str_ids) {
				del_ids.add(Integer.parseInt(str));
			}
			employeeService.delEmps(del_ids);
		}else{
			Integer id=Integer.parseInt(ids);
			employeeService.delEmp(id);
		}
		
		return Msg.success();
	}
	
	
	/**
	 * 修改员工信息，
	 * @param employee
	 * @return
	 * 
	 */
	
	@ResponseBody
	@RequestMapping(value="/emp/{empId}",method=RequestMethod.PUT)
	public Msg updateEmp(Employee employee){
		System.out.println(employee);
		employeeService.updateEmp(employee);
		return Msg.success();
	}
	
	/**
	 * 查询出要修改的员工的信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	@ResponseBody
	public Msg getEmp(@PathVariable("id")Integer id){
		//System.out.println("------------"+name);
		Employee employee=employeeService.getEmp(id);
		return Msg.success().add("emp", employee);
	}
	
	
	/**
	 * 校验用户名是否可用
	 * @param empName:被校验的用户名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/checkUserName")
	public Msg checkUserName(String empName){
		//System.out.println(empName);
		boolean b=employeeService.checkName(empName);
		if (b) {
			return Msg.success();
		}else {
			return Msg.fail();
		}
	}
	
	
	/**
	 * 向数据库中存储员工信息
	 * @param employee
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public Msg saveEmp(Employee employee){
		System.out.println(employee);
		employeeService.addEmp(employee);
		return Msg.success();
	}

	/**
	 * 查询员工信息（分页查询）
	 * 
	 * @param pn
	 *            页码
	 * @param model
	 * @return
	 */
	
	@RequestMapping("/emps")
	@ResponseBody
	public Msg getEmpsWithJson(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn) {
		// 查询之前调用 （页码，页的大小）
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的查询为分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装(查询结果,连续显示的页数)，只需将pageInfo交给页面即可
		// 封装了详细的分页信息，包括查询出来的数据
		PageInfo page = new PageInfo(emps, 5);
		/*List<Employee> lis=page.getList();
		for (Employee employee : lis) {
			System.out.println(employee);
		}*/
		return Msg.success().add("pageInfo", page);
	}

	//@RequestMapping("/emps")
	public String getEmps(
			@RequestParam(value = "pn", defaultValue = "1") Integer pn,
			Model model) {
		// 查询之前调用 （页码，页的大小）
		PageHelper.startPage(pn, 5);
		// startPage后面紧跟的查询为分页查询
		List<Employee> emps = employeeService.getAll();
		// 使用pageInfo包装(查询结果,连续显示的页数)，只需将pageInfo交给页面即可
		// 封装了详细的分页信息，包括查询出来的数据
		PageInfo page = new PageInfo(emps, 5);
		model.addAttribute("pageInfo", page);
		return "list";
	}
}
