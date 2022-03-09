package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class LimitSkipExample {
	
	@Test
	void test() {
		System.out.println("* limit()");
		
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10" );
		Stream<String> stream1 = list.stream();
		Stream<String> stream2 = stream1.limit(5);
		stream2.forEach(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* limit() 2");
		
		Stream<Double> randoms = Stream.generate(Math::random).limit(5);
		randoms.forEach(System.out::println);
	}

	@Test
	void test2() {
		System.out.println("* skip()");
		
		List<String> list = Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10" );
		Stream<String> stream3 = list.stream();
		Stream<String> stream4 = stream3.skip(5);
		stream4.forEach(System.out::println);
	}
}
