package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class CreateStream {
	
	@Test
	void test() {
		System.out.println("* Stream.of()");
		Stream<String> stream1 = Stream.of("code", "chacha", "blog", "example");
		stream1.forEach(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* Stream.empty()");
		Stream<String> stream1 = Stream.empty();
		stream1.forEach(System.out::println);
	}
	
	@Test
	void test2() {
		System.out.println("* Stream.generate()");
		Stream<String> stream1 = Stream.generate(() -> "Echo").limit(5);
		stream1.forEach(System.out::println);
	}
	
	@Test
	void test3() {
		// Stream.generate()에 Math::random을 인자로 넘겨주면 랜덤 숫자로 이어진 스트림이 생성됩니다.
		
		System.out.println("* Stream.generate(Math::random)");
		Stream<Double> stream1 = Stream.generate(Math::random).limit(5);
		stream1.forEach(System.out::println);
	}
	
	@Test
	void test4() {
		// Stream.iterate()는 인자를 두개 받습니다. 첫번쨰는 초기값이고, 두번째는 함수입니다.
		// 초기값, 초기값을 함수 인자로 대입한 결과를 다시 함수의 인자로 대입하여 무한 스트림이 생성됩니다.
		// 0, 2, 4, 6, 8...,
		
		System.out.println("* Stream.iterate()");
		Stream<Integer> stream1 = Stream.iterate(0,  n -> n + 2).limit(5);
		stream1.forEach(System.out::println);
	}
	
	@Test
	void test5() {
		System.out.println("* List<String> => Stream<String>");
		List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2");
		
		Stream<String> stream = list.stream();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void test6() {
		System.out.println("* List<Integer> => Stream<Integer>");
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
		
		Stream<Integer> stream = list.stream();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void test61() {
		System.out.println("* List<Double> => Stream<Double>");
		List<Double> list = Arrays.asList(1.1, 2.1, 3.1, 4.1, 5.1);
		
		Stream<Double> stream = list.stream();
		
		list.forEach(System.out::println);
	}
	
	@Test
	void test7() {
		System.out.println("* String[] => Stream<String");
//		String[] array = new String[] { "a1", "a2", "b1", "b2", "c1", "c2" };
		String[] array = { "a1", "a2", "b1", "b2", "c1", "c2" };
		
		Stream<String> stream = Arrays.stream(array);
		
		stream.forEach(System.out::println);
	}

	@Test
	void test8() {
		System.out.println("* String[] => List<String>");
		String[] array = { "a1", "a2", "b1", "b2", "c1", "c2" };
		List<String> list = Arrays.stream(array).collect(Collectors.toList());
		
	}

}
