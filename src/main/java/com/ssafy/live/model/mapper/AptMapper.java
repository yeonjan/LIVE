package com.ssafy.live.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.live.model.dto.Apt;
import com.ssafy.live.model.dto.Interest;

@Mapper
public interface AptMapper {
	List<Apt> apt(String regCode, int year, int month) throws SQLException;
	// 관심매물 등록
	int register_interest(String no, String id) throws SQLException;
	// 관심매물 확인
	List<Interest> view_interest(String id) throws SQLException;
	// 관심매물 삭제
	int deleteInterest(Long aptNo) throws SQLException; 
}
