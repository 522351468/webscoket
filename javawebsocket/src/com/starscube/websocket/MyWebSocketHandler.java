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
		System.out.println("�ر�");
	}

	/**
	 *  webscoket �����ɹ���
	 */
	@Override
	public void afterConnectionEstablished(WebSocketSession socketSession)
			throws Exception {
		System.out.println(socketSession.getAttributes().get(Constant.SESSION_USER));
		// UserDto u= (UserDto)socketSession.getAttributes().get(Constant.SESSION_USER);
		// System.out.println(u);
			String userId=socketSession.getAttributes().get(Constant.SESSION_USER)+"";
			// ��ȡ�û�id
			if(userSocketSessionMap.containsKey(userId)){
				userSocketSessionMap.remove(userId);
				userSocketSessionMap.put(userId, socketSession);
			}else{
				System.out.println("�Ұ��û�id Ϊ"+userId+"���û���Ϣ���� sockeetSession");
				userSocketSessionMap.put(userId, socketSession);
			}
	}

	/**
	 * ���ܿͻ��˷�������Ϣ
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
	 * ������ ���ߵ��û�����֪ͨ ֤���Լ�����
	 */
	public void allUserNotice(String userName){
		final TextMessage tm=new TextMessage(userName);
		final Thread thread=new Thread(new Runnable() {
			@Override
			public void run() {
				// �������û� ֪ͨ ��������
				if(userSocketSessionMap!=null){
					Set<String> userSet=userSocketSessionMap.keySet();
					for (String userString:userSet) {
						try {
							//Thread.sleep(10000);
							// ������ ����scoket ���û����� ��Ϣ
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
