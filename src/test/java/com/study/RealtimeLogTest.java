package com.study;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import com.study.RealtimeLog;

@Slf4j
class RealtimeLogTest {
	
	@Test
	void test() {
		RealtimeLog.info(null, "info log 1");
	}

	@Test
	void test2() {
		StringBuffer sb = new StringBuffer();
		
		RealtimeLog.info(sb, "info log 2");
		
		RealtimeLog.debug(sb, "info log 3");
		
		log.info(sb.toString());
		String[] logArray = sb.toString().split("\\n");
		for(String str: logArray ) {
			log.info(str.trim());
		}
	}

}
