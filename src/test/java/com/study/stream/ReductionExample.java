package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ReductionExample {
	
	@Test
	void test() {
		System.out.println("* 1부터 10까지 더하기");
		Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 첫번째: 1, 2
		// 두번째: (1+2), 3
		// 세번째: (1+2+3), 4 ...,
		Optional<Integer> sum = numbers.reduce((total, n) -> total + n);
		sum.ifPresent(s -> System.out.println("sum: " + s));
		assertEquals(55, sum.get());
	}

	@Test
	void test1() {
		System.out.println("* 초기값 10 + 1부터 10까지 더하기");
		Stream<Integer> numbers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		// 첫번째: 10, 1
		// 두번째: (10+1), 2
		// 세번째: (10+1+2), 3 ...,
		Integer sum = numbers.reduce(10, (total, n) -> {
			System.out.println("total=" + total + ", n=" + n);
			return total + n;
		});
		System.out.println("sum: " + sum);
		assertEquals(65, sum);
	}

}
