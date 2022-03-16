package com.study.api.model;

import org.springframework.stereotype.Component;

@Component("myCom")
public class TestComponent implements Script {
	public String kind = "CHANGE";
	
	public void run() {
		
	}

	@Override
	public void inspect() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getType() {
		return "silence";
	}
}
