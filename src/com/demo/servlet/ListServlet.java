package com.demo.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.demo.bean.Message;
import com.demo.service.ListService;

/*
 * 列表页面初始化控制
 */
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		//设置编码
		req.setCharacterEncoding("UTF-8");  //更改编码方式，防止中文乱码		
		
		//接受页面的值
		String command=req.getParameter("command");
		String description=req.getParameter("description");
		
		//向页面传值
		req.setAttribute("command", command);
		req.setAttribute("description", description);
		
		//查询消息列表并传给页面
		ListService listService=new ListService();
		List<Message> messageList=listService.queryMessageList(command, description);
		req.setAttribute("messageList", messageList);
		RequestDispatcher rd=req.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp");
		rd.forward(req,resp);		
	}
	protected void doPost(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException{
		this.doGet(req, resp);
	}
	
}
