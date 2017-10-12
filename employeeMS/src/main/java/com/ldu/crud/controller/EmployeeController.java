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
	 * 如果直接发送ajax=PUT形式的请求
	 * 封装的数据
	 * Employee 
	 * [empId=1014, empName=null, gender=null, email=null, dId=null]
	 * 
	 * 问题：
	 * 请求体中有数据；
	 * 但是Employee对象封装不上；
	 * update tbl_emp  where emp_id = 1014;
	 * 
	 * 原因：
	 * Tomcat：
	 * 		1、将请求体中的数据，封装一个map。
	 * 		2、request.getParameter("empName")就会从这个map中取值。
	 * 		3、SpringMVC封装POJO对象的时候。
	 * 				会把POJO中每个属性的值，request.getParamter("email");
	 * AJAX发送PUT请求引发的血案：
	 * 		PUT请求，请求体中的数据，request.getParameter("empName")拿不到
	 * 		Tomcat一看是PUT不会封装请求体中的数据为map，只有POST形式的请求才封装请求体为map
	 * org.apache.catalina.connector.Request--parseParameters() (3111);
	 * 
	 * protected String parseBodyMethods = "POST";
	 * if( !getConnector().isParseBodyMethod(getMethod()) ) {
                success = true;
                return;
            }
	 * 
	 * 
	 * 解决方案；
	 * 我们要能支持直接发送PUT之类的请求还要封装请求体中的数据
	 * 1、配置上HttpPutFormContentFilter；
	 * 2、他的作用；将请求体中的数据解析包装成一个map。
	 * 3、request被重新包装，request.getParameter()被重写，就会从自己封装的map中取数据
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
