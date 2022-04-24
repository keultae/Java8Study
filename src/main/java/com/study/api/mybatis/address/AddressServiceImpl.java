package com.study.api.mybatis.address;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

	@Autowired
	private AddressMapper addressMapper;
	
	@Override
	public List<AddressDTO> select(String uuid) {
		
		return addressMapper.select(uuid);
	}

	@Override
	public int insert(AddressDTO addressDTO) {
		return addressMapper.insert(addressDTO);		
	}

	@Override
	public int update(AddressDTO addressDTO) {
		return addressMapper.update(addressDTO);		
	}
	
	@Override
	public int delete(String uuid) {
		return addressMapper.delete(uuid);		
	}

}
