package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class CollectExample {
	
	@Test
	void test() {
		System.out.println("* Stream<String> => Set<String>");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "banana", "kiwi", "peach", "cherry", "lemon", "banana");
		// 중복된 banana는 하나만 남음 
		Set<String> fruitSet = fruits.collect(Collectors.toSet());
		for (String s : fruitSet) {
			System.out.println(s);
		}
	}
	
	@Test
	void test1() {
		System.out.println("* Stream<String> => List<String>");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		List<String> fruitList = fruits.collect(Collectors.toList());
		for (String s : fruitList) {
			System.out.println(s);
		}
	}
	
	@Test
	void test2() {
		System.out.println("* Stream<String> => String");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		String result1 = fruits.collect(Collectors.joining());
		System.out.println(result1);
		
		fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		String result2 = fruits.collect(Collectors.joining(", "));
		System.out.println(result2);
	}

	@Test
	void test3() {
		System.out.println("* Stream<String> => 가장 큰 객체 1개만 리턴");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		Function<String, Integer> getLength = str -> str.length();
		Optional<String> result = fruits.collect(Collectors.maxBy(Comparator.comparing(getLength)));
		
		System.out.println("result: " + result.orElse("no item"));
	}

	@Test
	void test4() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Double result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
		System.out.println("Average: "+result);
	}
	
	@Test
	void test5() {
		System.out.println("* Custom 객체에 Collect 적용하기");
		class Fruit {
			public String id;
			public String name;
			
			Fruit(String id, String name) {
				this.id = id;
				this.name = name;
			}
			
			public String getId() {
				return id;
			}
			public String getName() {
				return name;
			}
		}
		
		Stream<Fruit> fruits2 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
				new Fruit("3", "mango"), new Fruit("4", "kiwi"),
				new Fruit("5", "peach"), new Fruit("6", "cherry"),
				new Fruit("7", "lemon"));
		Map<String, String> map = fruits2.collect(Collectors.toMap(Fruit::getId, Fruit::getName));
		for (String key : map.keySet()) {
			System.out.println("key: " + key + ", value: " + map.get(key));
		}
	}
	
	@Test
	void test6() {
		System.out.println("* toMap 수행시 동일한 Key에 대한 예외처리 ");
		class Fruit {
		    public String id;
		    public String name;

		    Fruit(String id, String name) {
		        this.id = id;
		        this.name = name;
		    }

		    public String getId() {
		        return id;
		    }
		    public String getName() {
		        return name;
		    }
		}
		
		Stream<Fruit> fruits2 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
		        new Fruit("3", "mango"), new Fruit("4", "kiwi"),
		        new Fruit("5", "peach"), new Fruit("6", "cherry"),
		        new Fruit("5", "lemon"));
		// key 5의 경우 peach, lemon이 있는데, peach가 먼저 등록되었기 때문에 peach가 map에 저장됩니다.
		Map<String, String> map = fruits2.collect(
				Collectors.toMap(item -> item.getId(), item -> item.getName(), (existingValue, newValue) -> existingValue));
		for (String key : map.keySet()) {
		    System.out.println("key: " + key + ", value: " + map.get(key));
		}
		
		 System.out.println("------------------------------");
		fruits2 = Stream.of(new Fruit("1", "banana"), new Fruit("2", "apple"),
		        new Fruit("3", "mango"), new Fruit("4", "kiwi"),
		        new Fruit("5", "peach"), new Fruit("6", "cherry"),
		        new Fruit("5", "lemon"));
		// 동일 key를 갖고 있는 데이터가 두개 있을때, 두개의 값을 합하여 저장합니다.
		map = fruits2.collect(
				Collectors.toMap(item -> item.getId(), item -> item.getName(), (existingValue, newValue) -> existingValue + ", " + newValue));
		for (String key : map.keySet()) {
		    System.out.println("key: " + key + ", value: " + map.get(key));
		}
		
	}
}
