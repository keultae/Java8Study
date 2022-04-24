package com.study.api.mybatis.emp;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpMapper {
	public List<EmpDTO> selectDto(String id);
	public List<Map<String, Object>> selectMap(String id);
}
