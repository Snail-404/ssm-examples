package com.ldu.ssm.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class HelloController {
	@RequestMapping("/hello")
	public void hello(HttpServletRequest req,HttpServletResponse res) throws IOException{
		//通过HttpServletResponse输出
		//res.getWriter().println("result spring mvc api");
		//通过HttpServletResponse实现重定�?
		//res.sendRedirect("index.jsp");
		
		//通过HttpServletRequest实现转发
		try {
			req.setAttribute("msg", "by setAttribute");
			req.getRequestDispatcher("index.jsp").forward(req, res);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//通过spring mvc实现转发和重定向--无视图解析器
	@RequestMapping("/hello1")
	public String hello1(){
		//转发1
		//return "index.jsp";
		//转发2
		//return "forward:index.jsp";
		//重定�?
		return "redirect:index.jsp";
	}
	
	//通过spring mvc实现转发和重定向--有视图解析器
	@RequestMapping("/hello2")
	public String hello2(){
		//转发
		return "hello";
	}
	
}
