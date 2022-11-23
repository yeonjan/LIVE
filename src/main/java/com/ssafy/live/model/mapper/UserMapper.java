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
	User loginUser(User user) throws SQLException;
	String searchPwd(User user) throws SQLException;
	
	/* 관리자 */
	List<User> listUser(Map<String, Object> map) throws SQLException;
	User getUser(String userId) throws SQLException;
	void updateUser(User user) throws SQLException;
	void deleteUser(String userId) throws SQLException;
	
	/*토큰 */
	void saveRefreshToken(String userId, String token);
	void deleteRefreshToken(String userId);
	String getRefreshToken(String userId);
	
	/*회원 정보 수정*/
	void updateEmail(User user) throws SQLException;
	
	
}
