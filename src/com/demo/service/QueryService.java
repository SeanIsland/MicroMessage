package com.demo.service;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;

import com.demo.bean.Command;
import com.demo.bean.CommandContent;
import com.demo.dao.CommandDao;
import com.demo.entity.Page;
import com.demo.util.Iconst;

/**
 * 查询相关的业务功能
 */
public class QueryService {

	/**
	 * 通过指令查询自动回复的内容
	 * @param command 指令
	 * @return 自动回复的内容
	 */
	public String queryByCommand(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		if(Iconst.HELP_COMMAND.equals(command)) {
			commandList = commandDao.queryCommandList(null, null);
			StringBuilder result = new StringBuilder();
			for(int i = 0; i < commandList.size(); i++) {
				if(i != 0) {
					result.append("<br/>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		commandList = commandDao.queryCommandList(command, null);
		if(commandList.size() > 0) {
			List<CommandContent> contentList=commandList.get(0).getContentList();
			int i=new Random().nextInt(contentList.size());
			return contentList.get(i).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
	
	/**
	 * 根据查询条件分页查询消息列表
	 */
	public List<Command> queryMessageListByPage(String command,String description,Page page){
		Map<String,Object> parameter=new HashMap<String,Object>();
		//阻止消息对象
		Command commandItem=new Command();
		commandItem.setName(command);
		commandItem.setDescription(description);
		parameter.put("commandItem", commandItem);
		parameter.put("page", page);
		CommandDao commandDao=new CommandDao();
		//分页查询并返回结果
		return commandDao.queryCommandListByPage(parameter);
	}
}
