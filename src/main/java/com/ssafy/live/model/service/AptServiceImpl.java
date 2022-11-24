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
	public List<Apt> listDetailApt(Map<String, String> map) throws SQLException {
		log.debug(map.toString());
		return aptMapper.listDetailApt(map);
	}

	@Override
	public List<Apt> listApt(Map<String, String> map) throws SQLException {
		log.debug(map.toString());
		return aptMapper.listApt(map);
	}

	@Override
	public void registerInterest(String userId, String aptCode) throws SQLException {
		log.debug("관심 매물 등록 서비스");
		aptMapper.registerInterest(userId, aptCode);
	}

	@Override
	public List<Interest> viewInterest(String userId) throws SQLException {
		log.debug("관심 매물 조회 서비스");
		return aptMapper.viewInterest(userId);
	}

	@Override
	public void deleteInterest(String aptCode) throws SQLException {
		log.debug("관심 매물 삭제 서비스");
		aptMapper.deleteInterest(aptCode);
	}
	@Override
	public List<Apt> listInterestInfo(String userId) throws SQLException {
		return aptMapper.listInterestInfo(userId);
	}

	@Override
	public List<Map<String, Object>> selectPriceAvg(String aptCode) throws SQLException {
		return aptMapper.selectPriceAvg(aptCode);
	}

}
