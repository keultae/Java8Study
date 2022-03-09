package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class FilterMapExample {
	
	@Test
	void test() {
		System.out.println("* filter()");
		
		List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
		Stream<String> stream1 = list.stream();
		Stream<String> filtered = stream1.filter(s -> s.startsWith("c"));
		filtered.forEach(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* map()");
		
		/*
		 * Map은 각각의 item을 변경하여 새로운 컨텐츠를 생성하는 기능입니다. 
		 * 필터와 마찬가지로 map(함수)으로 어떻게 아이템을 변경시킬 지 함수로 정의를 합니다.
		 */
		List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
		Stream<String> stream2 = list.stream();
		stream2.map(s -> s.toUpperCase()).forEach(System.out::println);
	}
	
	@Test
	void test2() {
		System.out.println("* map() 2");
		
		/*
		 * Map은 각각의 item을 변경하여 새로운 컨텐츠를 생성하는 기능입니다. 
		 * 필터와 마찬가지로 map(함수)으로 어떻게 아이템을 변경시킬 지 함수로 정의를 합니다.
		 */
		List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c2", "c1", "c3");
		Stream<String> stream2 = list.stream();
		stream2.map(String::toUpperCase).forEach(System.out::println);
	}
	
	@Test
	void test3() {
		System.out.println("* flatMap()");
		
		/*
		 * FlatMap은 여러개의 스트림을 한개의 스트림으로 합쳐줍니다. 
		 * 복잡한 스트림을 간단한 스트림으로 변경되는데 사용할 수 있습니다. 
		 * 
		 * 아래 코드에서는 Stream<String[]>를 Stream<String> 형태로 변환하였습니다. 
		 */
		String[][] arrays = new String[][]{ {"a1", "a2"}, {"b1", "b2"}, {"c1", "c2", "c3"} };
		Stream<String[]> stream4 = Arrays.stream(arrays);
		Stream<String> stream5 = stream4.flatMap(s -> Arrays.stream(s));
		stream5.forEach(System.out::println);
	}
	
	@Test
	void test4() {
		System.out.println("* flatMap() + filter() + map()");
		
		/*
		 * 
		 */
		String[][] arrays = new String[][]{ {"a1", "a2"}, {"b1", "b2"}, {"c1", "c2", "c3"} };
		Stream<String[]> stream6 = Arrays.stream(arrays);
		Stream<String> stream7 = stream6.flatMap(s -> Arrays.stream(s));
		stream7.filter(s-> s.startsWith("a"))
		        .map(String::toUpperCase).forEach(System.out::println);
	}
}
