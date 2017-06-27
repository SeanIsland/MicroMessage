package com.demo.dao;

import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;

import com.demo.bean.Message;
import com.demo.db.DBAccess;

/**
 *和message数据表相关的数据操作 
 */
public class MessageDao {
	/*
	 * 根据查询条件查询消息列表
	 */
	public List<Message> queryMessageList(String command,String description){
		DBAccess dbAccess=new DBAccess();
		List<Message> messageList=new ArrayList<Message>();
		SqlSession sqlSession=null;
		try{
			sqlSession=dbAccess.getSqlSession();
			Message message=new Message();
			message.setCommand(command);
			message.setDescription(description);
			//通过sqlSession执行SQL语句
			messageList=sqlSession.selectList("Message.queryMessageList",message);
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null)
				sqlSession.close();
		}
		return messageList;
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
			sqlSession.delete("Message.deleteOne",id);
			sqlSession.commit();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(sqlSession!=null)
				sqlSession.close();
		}
	}
}
