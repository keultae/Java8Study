package com.study.Set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;

import com.study.common.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SetExample {
	private static final String[] T = LogUtil.T;
	
	/**
	 * 교집합
	 */
	@Test
	void intersection() {
		log.debug("{}START", T[0]);
		List<String> list1 = new ArrayList<String>();
		list1.add("B");
		list1.add("C");
		list1.add("E");
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.retainAll(list2);
		
		log.debug("{}교집합 {}", T[1], list1.toString());
		
		assertEquals("[B, C, E]", list1.toString());
	}

	/**
	 * 부분집합
	 */
	@Test
	void subset() {
		log.debug("{}START", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		List<String> list3 = Stream.of("A", "B", "G").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		log.debug("{}부분집합: {}", T[1], list1.containsAll(list2));
		log.debug("{}부분집합: {}", T[1], list1.containsAll(list3));
		
		assertTrue(list1.containsAll(list2));
		assertFalse(list1.containsAll(list3));
	}

	/**
	 * 차집합
	 */
	@Test
	void differenceOfSets() {
		log.debug("{}START", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.removeAll(list2);
		log.debug("{}차집합: {}", T[1], list1.toString());
		
		assertEquals("[D, E, F]", list1.toString());
	}
	
	/**
	 * 합집합
	 */
	@Test
	void union() {
		log.debug("{}START", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C", "g", "h").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.addAll(list2);
		log.debug("{}합집합: {}", T[1], list1.toString());
		assertEquals("[A, B, C, D, E, F, A, B, C, g, h]", list1.toString());
		
		List<String> list3 = list1.stream().distinct().collect(Collectors.toList());
		log.debug("{}중복 제거한 합집합: {}", T[1], list3.toString());
		assertEquals("[A, B, C, D, E, F, g, h]", list3.toString());
	}	
}
