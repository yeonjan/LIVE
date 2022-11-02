package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import com.ssafy.live.model.dto.DongCode;

public interface DongCodeService {
	// 시도 정보 가져오기
    List<DongCode> sido() throws SQLException;
    // 구군 정보 가져오기
    List<DongCode> gugun(String regcode) throws SQLException;
    // 동 가져오기
    List<DongCode> dong(String regcode) throws SQLException;
}
