package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.live.model.dto.Apt;
import com.ssafy.live.model.dto.Interest;
import com.ssafy.live.model.mapper.AptMapper;

@Service
public class AptServiceImpl implements AptService {

	private AptMapper aptMapper;
	
	@Override
	public List<Apt> apt(String regCode, int year, int month) throws SQLException {
		return aptMapper.apt(regCode, year, month);
	}

	@Override
	public int register_interest(String no, String id) throws SQLException {
		return 0;
	}

	@Override
	public List<Interest> view_interest(String id) throws SQLException {
		return null;
	}

	@Override
	public int deleteInterest(Long aptNo) throws SQLException {
		return 0;
	}

}
