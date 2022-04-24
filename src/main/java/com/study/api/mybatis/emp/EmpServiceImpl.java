package com.study.api.mybatis.emp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	private EmpMapper empMapper;
	
	@Override
	public List<EmpDTO> selectDto(String id) {
		List<EmpDTO> empDtoList = empMapper.selectDto(id);
		return empDtoList;
	}
	
	@Override
	public List<Map<String, Object>> selectMap(String id) {
		List<Map<String, Object>> mapList = empMapper.selectMap(id);
		return mapList;
	}

}
