package com.study.api.mybatis.org;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrgServiceImpl implements OrgService {

	@Override
	public void select() {
		log.info("START");
	}

}
