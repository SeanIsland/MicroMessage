package com.demo.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.demo.bean.Command;
import com.demo.db.DBAccess;

/**
 * 与指令表对应的数据库操作类
 */
public class CommandDao {
	/**
	 * 根据查询条件查询指令列表
	 */
	public List<Command> queryCommandList(String name,String description) {
		DBAccess dbAccess = new DBAccess();
		List<Command> commandList = new ArrayList<Command>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			Command command = new Command();
			command.setName(name);
			command.setDescription(description);
			// 通过sqlSession执行SQL语句
			commandList = sqlSession.selectList("Command.queryCommandList", command);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
	
	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Command> queryCommandListByPage(Map<String,Object> parameter){
		DBAccess dbAccess=new DBAccess();
		List<Command> commandList=new ArrayList<Command>();
		SqlSession sqlSession=null;
		try{
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			ICommand icommand=sqlSession.getMapper(ICommand.class);  //利用MyBatis的接口式编程,取代名空间.方法名的写法，尽量避免不必要的错误
			commandList=icommand.queryCommandListByPage(parameter);
		}catch(Exception e){
			e.printStackTrace();;
		}finally{
			if(sqlSession!=null){
				sqlSession.close();
			}
		}
		return commandList;
	}	
	
	/*
	 * 单条删除
	 */
	public void deleteOne(int id){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		try{
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			ICommand icommand=sqlSession.getMapper(ICommand.class);
			icommand.deleteOne(id);
			sqlSession.commit();  //一定要提交事务
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null)
				sqlSession.close();
		}
	}	
	
	/*
	 * 多条删除
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess dbAccess=new DBAccess();
		SqlSession sqlSession=null;
		try{
			sqlSession=dbAccess.getSqlSession();
			//通过sqlSession执行SQL语句
			ICommand icommand=sqlSession.getMapper(ICommand.class);
			icommand.deleteBatch(ids);  //接口式编程
			sqlSession.commit();  //一定要提交事务
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null)
				sqlSession.close();
		}
	}		
}
