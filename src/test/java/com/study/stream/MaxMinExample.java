package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class MaxMinExample {
	
	@Test
	void test() {
		System.out.println("* max()");
		
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
		
		final Comparator<String> comp1 = (s1, s2) -> s1.compareToIgnoreCase(s2);
		Optional<String> lastOne = langs.stream().max(comp1);
		
		if (lastOne.isPresent()) {
			System.out.println("last one: " + lastOne.get());
		}
		
//		if( lastOne.get() != null ) {
//			System.out.println("last one: " + lastOne.get());
//		}
		
		Optional<String> lastOne2 = langs.stream().max(String::compareToIgnoreCase);
		lastOne2.ifPresent(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* min()");
		
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
		Optional<String> firstOne = langs.stream().min(String::compareToIgnoreCase);
		firstOne.ifPresent(System.out::println);
	}

	@Test
	void test2() {
		System.out.println("* custom max(), min()");
		
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "javascript");
		final Comparator<String> comp2 = (p1, p2) -> Integer.compare( p1.length(), p2.length());
		Optional<String> largest = langs.stream().max(comp2);
		largest.ifPresent(s -> System.out.println("largest: " + s));

		Optional<String> shortest = langs.stream().min(comp2);
		shortest.ifPresent(s -> System.out.println("shortest: " + s));
	}

}
