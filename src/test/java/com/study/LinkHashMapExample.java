package com.study;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class LinkHashMapExample {
	
	@Test
	void test() {
		System.out.println("\n* HashMap()");
		
		Map<String, Integer> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			String key = "ID_" + i;
			Integer value = i;
			map.put(key, value);
		}
		
		map.forEach((key, value) -> {
			System.out.println("key: " + key + ", value: " + value);
		});
	}

	@Test
	void test1() {
		System.out.println("\n* LinkedHashMap()");
		
		Map<String, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < 10; i++) {
            String key = "ID_" + i;
            Integer value = i;
            map.put(key, value);
        }

        map.forEach((key, value) -> {
            System.out.println("key: " + key + ", value: " + value);
        });
	}
}
