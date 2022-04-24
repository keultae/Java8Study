package com.study.api;


import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.api.mybatis.address.AddressDTO;
import com.study.api.mybatis.address.AddressService;
import com.study.api.mybatis.emp.EmpDTO;
import com.study.api.mybatis.emp.EmpService;
import com.study.common.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/address")
public class AddressController {
	private static final String[] T = LogUtil.T;
	
	private final AddressService addressService;
	
	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
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
    
	@GetMapping("/select")
	public Object select(@RequestParam("uuid") String uuid  ) {
		log.info("{}START", T[0]);
		log.info("{}uuid={}", T[1], uuid);
		
		List<AddressDTO> addressDtoList = addressService.select(uuid);
		
		return addressDtoList;
	}
	
	@PostMapping("/insert")
	public String insert(@RequestBody AddressDTO addressDTO  ) {
		log.info("{}START", T[0]);
		log.info("{}addressDTO={}", T[1], addressDTO);
		
		int cnt = addressService.insert(addressDTO);
		log.info("{}cnt={}", T[1], cnt);
		String result = "ERR";
		if(cnt == 1) {
			result = "OK";
		}
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addressDTO);
			log.info("{}jsonString={}", T[1], jsonString);
			AddressDTO ad = mapper.readValue(jsonString, AddressDTO.class);
			log.info("{}ad={}", T[1], ad);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return result;
	}
	
	@PostMapping("/insert2")
	public String insert2(@RequestBody Map<String, Object> addressMap  ) {
		log.info("{}START", T[0]);
		log.info("{}addressDTO={}", T[1], addressMap);
		
//		int cnt = addressService.insert(addressDTO);
		int cnt = 0;
		log.info("{}cnt={}", T[1], cnt);
		String result = "ERR";
		if(cnt == 1) {
			result = "OK";
		}	
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			// 맵을 문자열로 변경하면 값이 모두 문자열로 변환됨
			String jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(addressMap);
			log.info("{}jsonString={}", T[1], jsonString);
			// DTO에 저장될때는 숫자는 숫자로 저장됨
			AddressDTO ad = mapper.readValue(jsonString, AddressDTO.class);
			log.info("{}ad={}", T[1], ad);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return result;
	}
	
	@PostMapping("/update")
	public String update(@RequestBody AddressDTO addressDTO  ) {
		log.info("{}START", T[0]);
		log.info("{}addressDTO={}", T[1], addressDTO);
		
		int cnt = addressService.update(addressDTO);
		log.info("{}cnt={}", T[1], cnt);
		String result = "ERR";
		if(cnt == 1) {
			result = "OK";
		}
		
		return result;
	}
	
	@PostMapping("/delete")
	public String delete(@RequestParam("uuid") String uuid  ) {
		log.info("{}START", T[0]);
		log.info("{}uuid={}", T[1], uuid);
		
		int cnt = addressService.delete(uuid);
		log.info("{}cnt={}", T[1], cnt);
		String result = "ERR";
		if(cnt == 1) {
			result = "OK";
		}
		return result;
	}
}
