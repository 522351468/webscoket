package com.starscube.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.starscube.datasource.DataSourceJdbc;
import com.starscube.dto.UserDto;

@Transactional
@Service("userManage")
public class UserManage extends DataSourceJdbc implements IUserManage {


	@Override
	public UserDto selectUserDto(final String userName, final String userPwd) {
		String sql="select user_id,user_name,user_pwd,name from user_info where user_name=? and user_pwd=?";
		@SuppressWarnings("unchecked")
		List<UserDto> listUserDtro=(List<UserDto>) getJdbcTemplate().query(sql, new  PreparedStatementSetter(){
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, userName);
				ps.setString(2, userPwd);
			}
		}, new ResultSetExtractor<Object>(){
			@Override
			public Object extractData(ResultSet rs) throws SQLException,
					DataAccessException {
				List<UserDto> list=new ArrayList<UserDto>();
				while(rs.next()){
					UserDto userDto=new UserDto();
					userDto.setUserName(rs.getString("user_name"));
					userDto.setUserPwd(rs.getString("user_pwd"));
					userDto.setUserId(rs.getInt("user_id"));
					userDto.setName(rs.getString("name"));
					list.add(userDto);
				}
				return list;
			}
		});
		if(listUserDtro!=null&&listUserDtro.size()>0){
			return listUserDtro.get(0);
		}
		return null;
	}

}
