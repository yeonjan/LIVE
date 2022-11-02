package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.live.model.dto.DongCode;
import com.ssafy.live.model.mapper.DongCodeMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DongCodeServiceImpl implements DongCodeService {

	private DongCodeMapper dongCodeMapper;
	
	@Autowired
	private DongCodeServiceImpl(DongCodeMapper dongCodeMapper) {
		this.dongCodeMapper = dongCodeMapper;
	}
	
	public List<DongCode> sido() throws SQLException {
		return dongCodeMapper.sido();
	}

	@Override
	public List<DongCode> gugun(String regcode) throws SQLException {
		log.debug(regcode);
		return dongCodeMapper.gugun(regcode);
	}

	@Override
	public List<DongCode> dong(String regcode) throws SQLException {
		return dongCodeMapper.dong(regcode);
	}

}
