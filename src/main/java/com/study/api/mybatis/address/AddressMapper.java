package com.study.api.mybatis.address;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressMapper {
	public List<AddressDTO> select(String uuid);
	
	public int insert(AddressDTO addressDTO);
	
	public int update(AddressDTO addressDTO);
	
	public int delete(String uuid);
}
