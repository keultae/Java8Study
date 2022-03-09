package com.study;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class StringExample {

	@Test
	void test() {
		System.out.println(String.format("|%,20d|", 1234578));
		System.out.println(String.format("|%,-20d|", 1234578));
		
		System.out.printf("|%,20d|%n", 1234578);
		System.out.printf("|%,-20d|%n", 1234578);
		
		
		System.out.printf("%2.4s%n", "Hello World!");
	}

}
