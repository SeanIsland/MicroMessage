package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import com.demo.dao.CommandDao;

/*
 * 维护相关的业务功能
 */
public class MaintainService {
	/*
	 * 单条删除
	 */
	public void deleteOne(String id){
		if(id!=null && !"".equals(id.trim())){  //service层中完成对业务逻辑进行判断
			CommandDao commandDao=new CommandDao();
			commandDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/*
	 * 批量删除
	 */
	public void deleteBatch(String[] ids){
		if(ids!=null){
			CommandDao commandDao=new CommandDao();
			List<Integer> idList=new ArrayList<Integer>();		
			for(String id:ids)
				idList.add(Integer.valueOf(id));
				commandDao.deleteBatch(idList);
		}
	}	
}
