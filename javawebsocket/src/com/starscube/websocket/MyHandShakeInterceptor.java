package com.starscube.websocket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.starscube.comment.Constant;
import com.starscube.dto.UserDto;

/**
 * scoket ��������(����) �ͶϿ�
 * @author Administrator
 *
 */
public class MyHandShakeInterceptor implements HandshakeInterceptor{
	/**
	 * ���ֺ�
	 * 
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler arg2, Exception arg3) {
		System.out.println("�������ֺ�");
	}
	/**
	 * ����ǰ
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		//����ǰ ���û�session ����
    	if (request instanceof ServletServerHttpRequest) {
    		try {
    			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    Integer userId = ((UserDto) session.getAttribute(Constant.SESSION_USER)).getUserId();
                    if(userId==null||userId.equals("")){
                    	throw new Exception("�û�IDΪ��");
                    }
                    attributes.put(Constant.SESSION_USER,userId);
                }else{
                	throw new Exception("sessionΪ��");
                }
			} catch (Exception e) {
				attributes.put("123","test-user");
			}
        }
        return true;
	}

}
