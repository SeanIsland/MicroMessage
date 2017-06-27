package com.demo.service;

import com.demo.dao.MessageDao;

/*
 * 维护相关的业务功能
 */
public class MaintainService {
	/*
	 * 单条删除
	 */
	public void deleteOne(String id){
		if(id!=null && !"".equals(id.trim())){  //service层中完成对业务逻辑进行判断
			MessageDao messageDao=new MessageDao();
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
}
