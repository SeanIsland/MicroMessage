package com.demo.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.service.MaintainService;

/*
 * 批量删除控制层
 */
@SuppressWarnings("serial")
public class DeleteBatchServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		//设置编码
		req.setCharacterEncoding("UTF-8");  //更改编码方式，防止中文乱码		
		
		//接受页面的值
		String[] ids=req.getParameterValues("id");  //获取多个来自页面的值
		MaintainService maintainService=new MaintainService();
		maintainService.deleteBatch(ids);
		
		//向页面跳转
		RequestDispatcher rd=req.getRequestDispatcher("/List.action");
		rd.forward(req,resp);		
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		this.doGet(req, resp);
	}
}
