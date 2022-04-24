package com.study.api.mybatis.address;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AddressDTO {
	String uuid;
	
	String g1uuid;
	String g2uuid;
	String g3uuid;
	
	String id;
	String name;
	String email;
	Date created;
}
