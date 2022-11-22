package com.ssafy.live.model.mapper;

import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ssafy.live.model.dto.DongCode;

@Mapper
public interface DongCodeMapper {
	// 시도 정보 가져오기
    List<DongCode> sido() throws SQLException;
    // 구군 정보 가져오기
    List<DongCode> gugun(@Param("regcode") String regcode) throws SQLException;
    // 동 가져오기
    List<DongCode> dong(@Param("regcode") String regcode) throws SQLException;
}
