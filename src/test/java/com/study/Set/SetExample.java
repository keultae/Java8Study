package com.study.Set;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
		log.debug("{}교집합", T[0]);
		List<String> list1 = new ArrayList<String>();
		list1.add("B");
		list1.add("C");
		list1.add("E");
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.retainAll(list2);
		
		log.debug("{}교집합 {}", T[1], list1.toString());
		
		// [주의] list1에 결과가 저장됨
		assertEquals("[B, C, E]", list1.toString());
	}

	/**
	 * 부분집합
	 */
	@Test
	void subset() {
		log.debug("{}부분집합", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		List<String> list3 = Stream.of("A", "B", "G").collect(Collectors.toList());
		log.debug("{}{}", T[1], list3.toString());
		
		log.debug("{}부분집합: {}", T[1], list1.containsAll(list2));
		log.debug("{}부분집합: {}", T[1], list1.containsAll(list3));
		
		// containsAll() 메소드는 인자로 사용한 값을 변경하지 않음
		log.debug("{}list1={}", T[1], list1.toString());
		log.debug("{}list2={}", T[1], list2.toString());
		log.debug("{}list3={}", T[1], list3.toString());
		assertTrue(list1.containsAll(list2));
		assertFalse(list1.containsAll(list3));
	}

	/**
	 * 차집합
	 */
	@Test
	void differenceOfSets() {
		log.debug("{}차집합", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.removeAll(list2);
		log.debug("{}차집합: {}", T[1], list1.toString());
		
		// [주의] list1에 결과가 저장됨
		assertEquals("[D, E, F]", list1.toString());
	}
	
	/**
	 * 합집합
	 */
	@Test
	void union() {
		log.debug("{}합집합", T[0]);
		List<String> list1 = Stream.of("A", "B", "C", "D", "E", "F").collect(Collectors.toList());
		log.debug("{}{}", T[1], list1.toString());
		
		List<String> list2 = Stream.of("A", "B", "C", "g", "h").collect(Collectors.toList());
		log.debug("{}{}", T[1], list2.toString());
		
		list1.addAll(list2);
		log.debug("{}합집합: {}", T[1], list1.toString());
		// [주의] list1에 결과가 저장됨
		assertEquals("[A, B, C, D, E, F, A, B, C, g, h]", list1.toString());
		
		List<String> list3 = list1.stream().distinct().collect(Collectors.toList());
		log.debug("{}중복 제거한 합집합: {}", T[1], list3.toString());
		assertEquals("[A, B, C, D, E, F, g, h]", list3.toString());
	}
	
	
	@Test
	void equals() {
		log.debug("{}List 값이 순서와 상관 없이 같은지 비교", T[0]);
		List<Integer> listA1 = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> listA2 = Arrays.asList(1, 2, 3);
		
		boolean resultA = listA1.containsAll(listA2) && listA2.containsAll(listA1);
		
		assertEquals(false, resultA);
		assertEquals(false, listA1.equals(listA2));
		
		
		List<Integer> listB1 = Arrays.asList(1, 2, 3);
		List<Integer> listB2 = Arrays.asList(2, 1, 3);
		
		boolean resultB = listB1.containsAll(listB2) && listB2.containsAll(listB1);
		
		assertEquals(true, resultB);
		// [주의] List.equals()는 순서가 다르면 안됨
		assertEquals(false, listB1.equals(listB2) );
	}

}