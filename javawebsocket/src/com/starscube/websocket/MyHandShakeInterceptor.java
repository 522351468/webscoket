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
 * scoket 建立连接(握手) 和断开
 * @author Administrator
 *
 */
public class MyHandShakeInterceptor implements HandshakeInterceptor{
	/**
	 * 握手后
	 * 
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
			WebSocketHandler arg2, Exception arg3) {
		System.out.println("我们握手后");
	}
	/**
	 * 握手前
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request,
			ServerHttpResponse arg1, WebSocketHandler arg2,
			Map<String, Object> attributes) throws Exception {
		//握手前 把用户session 存入
    	if (request instanceof ServletServerHttpRequest) {
    		try {
    			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
                HttpSession session = servletRequest.getServletRequest().getSession(false);
                if (session != null) {
                    Integer userId = ((UserDto) session.getAttribute(Constant.SESSION_USER)).getUserId();
                    if(userId==null||userId.equals("")){
                    	throw new Exception("用户ID为空");
                    }
                    attributes.put(Constant.SESSION_USER,userId);
                }else{
                	throw new Exception("session为空");
                }
			} catch (Exception e) {
				attributes.put("123","test-user");
			}
        }
        return true;
	}

}
