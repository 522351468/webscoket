package com.starscube.action;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.starscube.comment.Constant;
import com.starscube.dao.IUserManage;
import com.starscube.dto.UserDto;
import com.starscube.util.InitVariable;
import com.starscube.websocket.MyWebSocketHandler;

@Controller
@RequestMapping("/userLogin.do")
public class LoginAction {

	
	@Autowired
	private IUserManage userManage;
	@Autowired
	private MyWebSocketHandler myWebSocketHandler;
	@Autowired
	private InitVariable initVariable;
	
	@ResponseBody
	@RequestMapping("/selectUser")
	public ModelAndView selectUserIsExtent(UserDto userDto,HttpServletRequest request){
		// 获取 用户登录信息
		UserDto dto=userManage.selectUserDto(userDto.getUserName(),userDto.getUserPwd());
		ModelAndView modelAndView = new ModelAndView();  
		if(dto!=null){
			request.getSession().setAttribute(Constant.SESSION_USER, dto);
			initVariable.allUserSession.put(dto.getUserId(), dto);
		  //  Map<String, WebSocketSession> userSessionMap=myWebSocketHandler.userSocketSessionMap;
			myWebSocketHandler.allUserNotice(dto.getName());
		    modelAndView.addObject("allUsers", initVariable.getAllUserSession());
			modelAndView.setViewName("index");
		}
	    return modelAndView;
	}
}
