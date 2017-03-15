package com.starscube.dao;

import org.springframework.stereotype.Repository;

import com.starscube.dto.UserDto;
@Repository
public interface IUserManage {

	public UserDto selectUserDto(String userName,String userPwd);
}
