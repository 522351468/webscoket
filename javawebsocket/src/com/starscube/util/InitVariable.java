package com.starscube.util;

import java.util.HashMap;
import java.util.Map;

import com.starscube.dto.UserDto;


public class InitVariable {

	public static  Map<Integer, UserDto>  allUserSession=new HashMap<Integer, UserDto>();

	public static Map<Integer, UserDto> getAllUserSession() {
		return allUserSession;
	}

	public static void setAllUserSession(Map<Integer, UserDto> allUserSession) {
		InitVariable.allUserSession = allUserSession;
	}
	
}
