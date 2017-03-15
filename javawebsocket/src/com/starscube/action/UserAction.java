package com.starscube.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starscube.dao.IUserManage;
import com.starscube.util.InitVariable;
import com.starscube.websocket.MyWebSocketHandler;



@Controller
@RequestMapping("/userAction.do")
public class UserAction {

	@Autowired
	private IUserManage userManage;
	@Autowired
	private MyWebSocketHandler myWebSocketHandler;
	@Autowired
	private InitVariable initVariable;
	
	@RequestMapping("/index")
	public String selectUser(){
		System.out.println(userManage.selectUserDto("1", "1"));
		return "1";
	}
	
	
	@RequestMapping("/sendMessage")
	@ResponseBody
	public Object sendMessage(String userId,String message){
		myWebSocketHandler.sendMessage(userId, message);
		return null;
	}
	
	@RequestMapping("/playUser")
	@ResponseBody
	public Object playUser(String userId,String message){
		
		// �Ƴ�session
		initVariable.getAllUserSession().remove(userId);
		// ������Ϣ  ��������
		myWebSocketHandler.sendMessage(userId, "-1");
		return null;
	}
	
	
	
	/**
	 *  �û�����֪ͨ
	 */
	public void sendUserGoOnline(){
		
	}
	
}
