package com.study.api;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.api.mybatis.emp.EmpDTO;
import com.study.api.mybatis.emp.EmpService;
import com.study.common.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DbController {
	private static final String[] T = LogUtil.T;
	
	private final EmpService empService;
	
	@Autowired
	public DbController(EmpService empService) {
		this.empService = empService;
	}
	
	// 빈 생성자 호출 이후 자동으로 호출될 메서드
    @PostConstruct
    public void init() {
    	log.info("init 메서드 호출");
    }
    
    //빈 객체가 소멸될 때 자동으로 호출되는 메서드를 등록
    @PreDestroy
    public void destroy() {
    	log.info("destroy 메서드 호출");
    }
    
	@GetMapping("/db/selectDto")
	public Object selectDto() {
		log.info("{}START", T[0]);

		// "nlp_ai7_daeli1"
		List<EmpDTO> empDtoList = empService.selectDto(null);
		
		return empDtoList;
	}
	
	@GetMapping("/db/selectMap")
	public Object selectMap() {
		log.info("{}START", T[0]);
		
		// "nlp_ai7_daeli1"
		List<Map<String, Object>> mapList = empService.selectMap("nlp_ai7_daeli1");
		
		return mapList;
	}

}
