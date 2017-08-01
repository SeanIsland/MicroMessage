package com.demo.dao;

import java.util.List;
import java.util.Map;

import com.demo.bean.Command;

/**
 * 与Message配置文件相对应的接口,MyBatis接口式编程
 */
public interface ICommand {
	/**
	 * 根据查询条件查询消息列表
	 */
	public List<Command> queryCommandList(Map<String,Object> parameter);
	
	/**
	 * 根据查询条件查询消息列表的条数
	 */
	public int count(Command command);
	
	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Command> queryCommandListByPage(Map<String,Object> parameter);
	
	/**
	 * 单条删除
	 */
	public void deleteOne(int id);
	
	/**
	 * 多条删除
	 */
	public void deleteBatch(List<Integer> ids);
}
