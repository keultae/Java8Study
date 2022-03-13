package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

class CollectExample {
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
	
	class Product {
		private String name;
		private int amount;
		
		public Product(String name, int amount) {
			this.name = name;
			this.amount = amount;
		}
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public int getAmount() {
			return amount;
		}
		public void setAmount(int amount) {
			this.amount = amount;
		}

		@Override
		public String toString() {
			return "Product [name=" + name + ", amount=" + amount + "]";
		}
	}
	
	
	@Test
	void test00() {
		System.out.println("\n* Stream<String> => Set<String>");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "banana", "kiwi", "peach", "cherry", "lemon", "banana");
		// 중복된 banana는 하나만 남음 
		Set<String> fruitSet = fruits.collect(Collectors.toSet());
		for (String s : fruitSet) {
			System.out.println(s);
		}
	}
	
	@Test
	void test01() {
		System.out.println("\n* Stream<String> => List<String>");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		List<String> fruitList = fruits.collect(Collectors.toList());
		for (String s : fruitList) {
			System.out.println(s);
		}
	}
	
	@Test
	void test02() {
		System.out.println("\n* Stream<String> => String");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		String result1 = fruits.collect(Collectors.joining());
		System.out.println(result1);
		
		fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		String result2 = fruits.collect(Collectors.joining(", "));
		System.out.println(result2);
	}

	@Test
	void test03() {
		System.out.println("\n* Stream<String> => 가장 큰 객체 1개만 리턴");
		Stream<String> fruits = Stream.of("banana", "apple", "mango", "kiwi", "peach", "cherry", "lemon");
		Function<String, Integer> getLength = str -> str.length();
		Optional<String> result = fruits.collect(Collectors.maxBy(Comparator.comparing(getLength)));
		
		System.out.println("result: " + result.orElse("no item"));
	}

	@Test
	void test04() {
		System.out.println("\n* averagingInt");
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		Double result = list.stream().collect(Collectors.averagingInt(v -> v * 2));
		System.out.println("Average: "+result);
	}
	
	@Test
	void test05() {
		System.out.println("\n* Custom 객체에 Collect 적용하기");
		
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
	void test06() {
		System.out.println("\n* toMap 수행시 동일한 Key에 대한 예외처리 ");
		
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
	
	@Test
	void test07() {
		System.out.println("\n* Collectors.summarizingInt() ");
		
		// IntStream을 어디에 써야할지 모르겠음
		IntStream intStream = IntStream.rangeClosed(1, 10);
		IntSummaryStatistics stst1 = intStream.boxed().collect(Collectors.summarizingInt(s -> s));
		System.out.println(stst1);
		
		List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		IntSummaryStatistics stst2 = list.stream().collect(Collectors.summarizingInt(s -> s));
		System.out.println(stst2);
	}
	
	@Test
	void test08() {
		System.out.println("\n* Collectors.summarizingInt() custom");
		
		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
				);
		// getName이 static도 아닌데, 이렇게 써도 되는 이유를 정확히 모르겠음
//		List<String> nameList = productList.stream().map(Product::getName).collect(Collectors.toList());
		List<String> nameList = productList.stream().map(s -> s.getName()).collect(Collectors.toList());
		System.out.printf("\tnameList=%s%n", nameList);
		
		String nameStr = productList.stream().map(Product::getName).collect(Collectors.joining());
		System.out.printf("\tnameStr=%s%n", nameStr);
		
		String nameStr2 = productList.stream().map(Product::getName).collect(Collectors.joining(", ", "<", ">"));
		System.out.printf("\tnameStr2=%s%n", nameStr2);
		
		Double averageAmount = productList.stream().collect(Collectors.averagingInt(Product::getAmount));
		System.out.printf("\taverageAmount=%f%n", averageAmount);
		
		Integer summingAmount = productList.stream().collect(Collectors.summingInt(Product::getAmount));
		System.out.printf("\tsummingAmount=%d%n", summingAmount);
		// IntStream 으로 바꿔주는 mapToInt 메소드를 사용해서 좀 더 간단하게 표현할 수 있습니다.
		summingAmount = productList.stream().mapToInt(Product::getAmount).sum();
		System.out.printf("\tsummingAmount=%d%n", summingAmount);
		
		IntSummaryStatistics statistics = productList.stream().collect(Collectors.summarizingInt(Product::getAmount));
		System.out.printf("\tstatistics=%s%n", statistics);
		System.out.printf("\t\tstatistics.getCount()=%d%n", statistics.getCount());
		System.out.printf("\t\tstatistics.getSum()=%d%n", statistics.getSum());
	}
	
	@Test
	void test09() {
		System.out.println("\n* Collectors.groupingBy()");
		
		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
				);
		
//		Map<Integer, List<Product>> amountMap = productList.stream().collect(Collectors.groupingBy(Product::getAmount));
		Map<Integer, List<Product>> amountMap = productList.stream().collect(Collectors.groupingBy(a -> a.getAmount()));
		System.out.printf("\t%s%n", amountMap);
	}
	
	@Test
	void test10() {
		System.out.println("\n* Collectors.partitioningBy()");
		
		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
				);
		
		Map<Boolean, List<Product>> booleanMap = productList.stream().collect(Collectors.partitioningBy(el -> el.getAmount() > 15));
		System.out.printf("\t%s%n", booleanMap);
	}
	
	@Test
	void test11() {
		System.out.println("\n* Collector.of()");
		
		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
				);
		
		Collector<Product, ?, LinkedList<Product>> toLinkedList = 
				Collector.of(LinkedList::new, 
						LinkedList::add, 
						(first, second) -> {
							first.addAll(second);
							return first;
						});
		LinkedList<Product> linkedListOfPersons = 
				productList.stream()
				.collect(toLinkedList);
		System.out.println("\tlinkedListOfPersons=" + linkedListOfPersons);
	}
	
	@Test
	void test12() {
		System.out.println("\n* Collector.of() custom 단일 처리 stream()");
		
		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
				);
		
		Map<Integer, ArrayList<Product>> map = null;
		
		Collector<Product, ArrayList<Product>, ArrayList<Product>> toLinkedList = 
				Collector.of(() -> {
					ArrayList<Product> list = new ArrayList<Product>();
					System.out.printf("\t%s%n\t%-14s, %-50s, %s%n", "----------------------------------------------------------------------------------------------------", 
							"Supplier", Thread.currentThread(), list);
					return list;
				}, 
						(list, product) -> {
							System.out.printf("\t%s%n\t%-14s, %-50s, %s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"BiConsumer", Thread.currentThread(), list, product);
							list.add(product); 
						},
						(list1, list2) -> {
							// 병렬 처리시 실행됨
							// productList.parallelStream()
							System.out.printf("\t%s%n\t%-14s, %-50s, %s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"BinaryOperator", Thread.currentThread(), list1, list2);
							list1.addAll(list2);
							return list1;
						});
		ArrayList<Product> linkedListOfPersons = 
				productList.stream()
//				productList.parallelStream()
				.collect(toLinkedList);
		System.out.println("\tlinkedListOfPersons=" + linkedListOfPersons);
	}
	
	@Test
	void test13() {
		System.out.println("\n* Collector.of() custom 병렬 처리 parallelStream()");

		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
			);
		
		Map<Integer, ArrayList<Product>> map = null;
		
		Collector<Product, ArrayList<Product>, ArrayList<Product>> toLinkedList = 
				Collector.of(() -> {
							ArrayList<Product> list = new ArrayList<Product>();
							System.out.printf("\t%s%n\t%-14s, %-50s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"Supplier", Thread.currentThread(), list);
							return list;
						}, 
						(list, product) -> {
							System.out.printf("\t%s%n\t%-14s, %-50s, %s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"BiConsumer", Thread.currentThread(), list, product);
							list.add(product); 
						},
						(list1, list2) -> {
							// 병렬 처리시 실행됨
							// productList.parallelStream()
							System.out.printf("\t%s%n\t%-14s, %-50s, %s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"BinaryOperator", Thread.currentThread(), list1, list2);
							list1.addAll(list2);
							return list1;
						});
		ArrayList<Product> linkedListOfPersons = 
//				productList.stream()
				productList.parallelStream()
				.collect(toLinkedList);
		System.out.println("\tlinkedListOfPersons=" + linkedListOfPersons);
	}
	
	@Test
	void test14() {
		System.out.println("\n* Collector.of() List => Map");

		List<Product> productList = Arrays.asList(
				new Product("potatoes", 23),
				new Product("orange", 14),
				new Product("lemon", 13),
				new Product("bread", 23),
				new Product("sugar", 13)
			);
		
		// 아래처럼 Map을 사용하면 오류가 발생하여 HashMap을 사용
//		Collector<Product, Map<Integer, ArrayList<Product>>, Map<Integer, ArrayList<Product>> > toLinkedList2 = 
		
		Collector<Product, HashMap<Integer, ArrayList<Product>>, HashMap<Integer, ArrayList<Product>> > toLinkedList2 = 
				Collector.of(() -> new HashMap<Integer, ArrayList<Product>>(), 
						(map, product) -> {
							System.out.printf("\t%s%n\t%-14s, %-50s, %s, %s%n", "----------------------------------------------------------------------------------------------------", 
									"BiConsumer", Thread.currentThread(), map, product);
							ArrayList<Product> list1 = map.get(product.getAmount());
							if(list1 == null) {
								ArrayList<Product> list2 = new ArrayList<Product>();
								list2.add(product);
								map.put(product.getAmount(), list2);
							} else {
								list1.add(product);
							}
						},
						(map1, map2) -> {
							throw new UnsupportedOperationException("Parallel collection is not supported");
						});
		HashMap<Integer, ArrayList<Product>> linkedListOfPersons2 = productList.stream().collect(toLinkedList2);
		System.out.println("\tlinkedListOfPersons=" + linkedListOfPersons2);
	}
}
