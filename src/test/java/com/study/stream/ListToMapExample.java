package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ListToMapExample {
	@Test
	void test1() {
		System.out.println("\n* 일반적인 방식으로 List 값을 키로 한 Map으로 변환");
		
		List<String> list = Stream.of("A", "B", "C", "A", "B", "C", "A").collect(Collectors.toList());
		System.out.println("\tlist=" + list);
		
		Map<String, List<String>> map = new LinkedHashMap<String, List<String>>();
		for(String key: list) {
			List<String> keyList = map.get(key);
			if(keyList == null) {
				keyList = new ArrayList<String>();
				keyList.add(key);
				map.put(key, keyList);
			} else {
				keyList.add(key);
			}
		}
		
		System.out.println("\tmap=" + map);
	}
	
	@Test
	void test2() {
		System.out.println("\n* Lambda");
		
		List<String> list = Stream.of("A", "B", "C", "A", "B", "C", "A").collect(Collectors.toList());
		System.out.println("\tlist=" + list);
		
		Map<String, List<String>> map = list.stream().collect(Collectors.toMap(
				s -> {
					System.out.printf("\t맵 키 생성 IN=%s, RETURN=%s%n", s, s);
					return s;
				}, s -> {
					List<String> list1 = Arrays.asList(s);
					System.out.printf("\t맵 값 생성 B IN=%s, RETURN=%s%n", s, list1 );
					return list1;
				}, ( a, b ) -> {
					System.out.printf("\t맵 키 중복 a=%s, b=%s%n", a, b);
//					System.out.println("a=" + a.getClass().getName() + ", b=" + b.getClass().getName());
					
					List<String> plus = new ArrayList<String>();				
					plus.addAll(a);
					plus.addAll(b);
					
					return plus;
				}));
		System.out.println("\tmap=" + map);
	}
	
	@Test
	void test3() {
		System.out.println("\n* BiFunction");
		
		List<String> list = Stream.of("A", "B", "C", "A", "B", "C", "A").collect(Collectors.toList());
		System.out.println("\tlist=" + list);
		
		BiFunction< List<String>, List<String>, List<String> > dup = (a, b) -> {
			System.out.println("\t키 중복 a=" + a + ", b=" + b);
			
			List<String> plus = new ArrayList<>();
			plus.addAll(a);
			plus.addAll(b);
			return plus;
		};		
		Map<String, List<String>> map = list.stream().collect(Collectors.toMap(
				s -> s, s -> Arrays.asList(s), dup::apply));
		
		System.out.println("\tmap=" + map);
	}
	
	@Test
	void test4() {
		System.out.println("\n* BiFunction 2");
		
		List<String> list = Stream.of("A", "B", "C", "A", "B", "C", "A").collect(Collectors.toList());
		System.out.println("\tlist=" + list);
		
		BiFunction< List<String>, List<String>, List<String> > dup = (a, b) -> {
			System.out.println("\t키 중복 a=" + a + ", b=" + b);
//			System.out.println("a=" + a.getClass().getName() + ", b=" + b.getClass().getName());
			
			// UnsupportedOperationException 발생
			a.addAll(b);
			return a;
		};		
		Map<String, List<String>> map = list.stream().collect(Collectors.toMap(
				s -> s, s -> Arrays.asList(s), dup::apply));
		
		System.out.println("\tmap=" + map);
	}
	
	
	@Test
	void test5() {
		System.out.println("\n* Lambda counting");
		
		List<String> list = Stream.of("A", "B", "C", "A", "B", "C", "A", "D").collect(Collectors.toList());
		System.out.println("\tlist=" + list);
		
		Map<String, Integer> map = list.stream().collect(Collectors.toMap(
				s -> s,	 		// 스트림에서 맵 키 리턴 
				s -> 1, 		// 스트림에서 맵 값 리
				( a, b ) -> {	// 맵 키 중복이면 2개의 값을 더함 
					System.out.printf("\t키 중복 a=%s, b=%s%n", a, b);
					return a + b;
				}));
		System.out.println("\tmap=" + map);
	}
}
