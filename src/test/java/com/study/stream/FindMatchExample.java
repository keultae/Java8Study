package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class FindMatchExample {
	
	@Test
	void test() {
		System.out.println("* findFirst(), findAny()");
		
		List<String> elements = Arrays.asList("a", "a1", "b", "b1", "c", "c1");
		Optional<String> firstElement = elements.stream()
				.filter(s -> s.startsWith("b")).findFirst();
		Optional<String> anyElement = elements.stream()
				.filter(s -> s.startsWith("b")).findAny();
		firstElement.ifPresent(System.out::println);
		anyElement.ifPresent(System.out::println);
	}
	
	@Test
	void test2() {
		System.out.println("* 병렬 findFirst(), findAny()");
		
		/*
		 * stream()에 .parallel()을 붙이면 스트림의 처리가 병렬로 동작됩니다. 
		 * 즉, 멀티쓰레드에서 탐색이 수행됩니다. 
		 * 따라서 스트림의 순서대로 탐색을 수행하지 않기 때문에 findAny는 b1 또는 b 중에 가장 먼저 찾은 것을 리턴합니다. 
		 * findFirst는 병렬로 처리해도 순서에 우선순위를 두어 결과를 리턴해줍니다
		 */
		List<String> elements = Arrays.asList("a", "a1", "b", "b1", "c", "c1");
		Optional<String> firstElement = elements.stream().parallel()
				.filter(s -> s.startsWith("b")).findFirst();
		Optional<String> anyElement = elements.stream().parallel()
				.filter(s -> s.startsWith("b")).findAny();
		
		firstElement.ifPresent(System.out::println);
		anyElement.ifPresent(System.out::println);
	}

	@Test
	void test3() {
		System.out.println("* anyMatn(), allMatch(), nonMatch()");
		
		/*
		 * anyMatch는 조건에 부합하는 객체가 1개라도 있으면 true 아니면 false를 리턴합니다. 
		 * allMatch는 모든 객체가 조건에 부합해야 true 아니면 false를 리턴합니다. 
		 * noneMatch는 반대로 조건에 부합하는 객체가 없어야 true 아니면 false를 리턴합니다.
		 */
		List<String> elements = Arrays.asList("a", "a1", "b", "b1", "c", "c1");

		boolean anyMatch = elements.stream().anyMatch(s -> s.startsWith("b"));
		System.out.println("anyMatch: " + (anyMatch ? "true" : "false"));

		boolean allMatch = elements.stream().allMatch(s -> s.startsWith("b"));
		System.out.println("allMatch: " + (allMatch ? "true" : "false"));

		boolean noneMatch = elements.stream().noneMatch(s -> s.startsWith("b"));
		System.out.println("noneMatch: " + (noneMatch ? "true" : "false"));
	}

}
