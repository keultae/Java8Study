package com.study.api.mybatis.emp;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpDTO {
	String id;
	String password;
	String name;
	String email;
	String useYn;
	Date created;
	int orgId;
}
