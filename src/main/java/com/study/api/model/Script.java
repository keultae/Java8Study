package com.study.api.model;

public interface Script {
	default String getType() {
		return "normal";
	}
	
	void inspect();
	
}
