package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;


/**
 * Seream을 배열로 변환 
 * 
 * Stream.toArray()
 * Stream.toArray(Type[]::new)는 Stream을 Type 배열로 변환 합니다.
 * 
 * @author chochongtae
 *
 */
class ConvertStreamToArray {

	@Test
	void test() {
		System.out.println("* List<String> => String[]");
		List<String> list = Stream.of("a", "b", "c").collect(Collectors.toList());
		System.out.println(list);
		
		String[] result = list.stream().map(String::toUpperCase).toArray(String[]::new);
		for(String s: result) {
			System.out.println(s);
			
		}
	}
	
	@Test
	void test2() {
		System.out.println("* List<Integer> => Integer[]");
		List<Integer> list = Stream.of(1, 2, 3).collect(Collectors.toList());
		System.out.println(list);
		
		Integer[] result = list.stream().map(x -> x* x).toArray(Integer[]::new);
		for(Integer n: result) {
			System.out.println(n);
			
		}
	}
	
	@Test
	void test3() {
		System.out.println("* int[] => Integer[]");
		int[] num = {1, 2, 3};
		
		IntStream intStream = Arrays.stream(num);
		
		Integer[] result = intStream
				.map(x -> x*x)
				.boxed()	// int를 Integer로 변환
				.toArray(Integer[]::new); 	
		
		for(Integer n: result) {
			System.out.println(n);
			
		}
	}

	@Test
	void test4() {
		System.out.println("* int[] => int[]");
		int[] num = {1, 2, 3};
		
		IntStream intStream = Arrays.stream(num);
		
		int[] result = intStream
				.map(x -> x*x)
				.toArray();
		
		for(Integer n: result) {
			System.out.println(n);
			
		}
	}
}