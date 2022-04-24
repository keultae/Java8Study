package com.study.api.mybatis.emp;

import java.util.List;
import java.util.Map;

public interface EmpService {
	public List<EmpDTO> selectDto(String id);
	
	public List<Map<String, Object>> selectMap(String id);
}
