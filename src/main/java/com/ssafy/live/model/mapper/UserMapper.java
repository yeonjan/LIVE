package com.ssafy.live.model.mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.User;

@Mapper
public interface UserMapper {

	/* 일반 유저  */
	int idCheck(String userId) throws SQLException;
	void joinUser(User user) throws SQLException;
	User loginUser(Map<String, String> map) throws SQLException;
	
	/* 관리자 */
	List<User> listUser(Map<String, Object> map) throws SQLException;
	User getUser(String userId) throws SQLException;
	void updateUser(User user) throws SQLException;
	void deleteUser(String userId) throws SQLException;
	
}