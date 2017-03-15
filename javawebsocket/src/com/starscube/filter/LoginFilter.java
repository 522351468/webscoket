package com.starscube.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.starscube.comment.Constant;
import com.starscube.dto.UserDto;
import com.starscube.util.InitVariable;

public class LoginFilter implements HandlerInterceptor{

	@Autowired
	private InitVariable initVariable;
	
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object obj) throws Exception {
		
		// ÅÐ¶Ïsession
		 UserDto userDto=(UserDto)request.getSession().getAttribute(Constant.SESSION_USER);
		 if(userDto!=null){
			 if(initVariable.getAllUserSession().containsKey(userDto.getUserId())){
					return true; 
			 }else{
				 response.sendRedirect("/loginOut.do/loginOut");
				 return false;
			 }
		 }else{
			 response.sendRedirect("/loginOut.do/loginOut");
			 return false;
		 }
	}

}
