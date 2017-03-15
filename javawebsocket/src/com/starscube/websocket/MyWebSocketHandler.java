package com.starscube.websocket;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.starscube.comment.Constant;
import com.starscube.dto.UserDto;

@Component
public class MyWebSocketHandler implements  WebSocketHandler{

	
	public static final Map<String, WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new HashMap<String, WebSocketSession>();
	}
	@Override
	public void afterConnectionClosed(WebSocketSession arg0, CloseStatus arg1)
			throws Exception {
		System.out.println("关闭");
	}

	/**
	 *  webscoket 建立成功后
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession socketSession)
			throws Exception {
		System.out.println(socketSession.getAttributes().get(Constant.SESSION_USER));
		// UserDto u= (UserDto)socketSession.getAttributes().get(Constant.SESSION_USER);
		// System.out.println(u);
			String userId=socketSession.getAttributes().get(Constant.SESSION_USER)+"";
			// 获取用户id
			if(userSocketSessionMap.containsKey(userId)){
				userSocketSessionMap.remove(userId);
				userSocketSessionMap.put(userId, socketSession);
			}else{
				System.out.println("我把用户id 为"+userId+"的用户信息存入 sockeetSession");
				userSocketSessionMap.put(userId, socketSession);
			}
	}

	/**
	 * 接受客户端发出的信息
	 */
	@Override
	public void handleMessage(WebSocketSession arg0, WebSocketMessage<?> message)
			throws Exception {
		System.out.println(message);
	}

	@Override
	public void handleTransportError(WebSocketSession arg0, Throwable arg1)
			throws Exception {
		
	}

	@Override
	public boolean supportsPartialMessages() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public void sendMessage(String userId,String message){
		TextMessage tm=new TextMessage(message);
		WebSocketSession session=userSocketSessionMap.get(userId);
		try {
			if(session!=null){
				session.sendMessage(tm);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 向所有 上线的用户发布通知 证明自己上线
	 */
	public void allUserNotice(String userName){
		final TextMessage tm=new TextMessage(userName);
		final Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				// 向所有用户 通知 本人上线
				if(userSocketSessionMap!=null){
					Set<String> userSet=userSocketSessionMap.keySet();
					for (String userString:userSet) {
						try {
							//Thread.sleep(10000);
							// 向所有 建立scoket 的用户发布 信息
							userSocketSessionMap.get(userString).sendMessage(tm);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		});
		thread.start();
	}
	

}
