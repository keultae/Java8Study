package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ConcatExample {
	
	@Test
	void test() {
		System.out.println("* concat()");
		
		List<String> numbers = Arrays.asList("1", "2", "3", "4", "5");
		List<String> chars = Arrays.asList("a", "b", "c", "d", "e");
		Stream<String> stream1 = numbers.stream();
		Stream<String> stream2 = chars.stream();
		Stream<String> stream3 = Stream.concat(stream1, stream2);
		stream3.forEach(System.out::println);
	}
}
