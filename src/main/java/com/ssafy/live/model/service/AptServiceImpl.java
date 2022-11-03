package com.ssafy.live.model.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.live.model.dto.Apt;
import com.ssafy.live.model.dto.Interest;
import com.ssafy.live.model.mapper.AptMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AptServiceImpl implements AptService {

	@Autowired
	private AptMapper aptMapper;
	
	@Override
	public List<Apt> listApt(Map<String, String> map) throws SQLException {
		log.debug(map.toString());
		return aptMapper.listApt(map);
	}

	@Override
	public void registerInterest(String no, String id) throws SQLException {
		aptMapper.registerInterest(no, id);
	}

	@Override
	public List<Interest> viewInterest(String id) throws SQLException {
		return null;
	}

	@Override
	public void deleteInterest(Long aptNo) throws SQLException {
	}

}
