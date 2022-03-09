package com.study;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;

class OptionalExample {
	/*
	 * Optional은 어떤 객체를 wrapping하는 객체입니다. 
	 * 즉, 어떤 객체를 포함할 수 있고 또는 null 객체를 포함할 수 있습니다. 
	 * 자바의 null 처리를 유연하게 하고자 도입된 것 같습니다.
	 */
	
	
	@Test
	void test() {
		System.out.println("\n* Optional.get()");
		
		/*
		 * 아래 코드는 Optional<String> 객체를 생성하고 내부의 String 객체를 출력하는 예제입니다. 
		 * Optional 객체는 단순히 내부에 String 객체를 감싸는 wrapper 객체입니다. 
		 * 내부의 객체를 사용하려면 opString.get()처럼 get()이 내부 객체를 리턴합니다. 
		 * Optional은 Optional.of()에 객체를 넣어 생성할 수 있습니다
		 */
		String string = "a string in optional";
		Optional<String> opString = Optional.of(string);
		System.out.println(opString.get());
	}
	
	@Test
	void test2() {
		System.out.println("\n* Optional.get()이 null");
		
		/*
		 * 만약 null을 wrapping하는 Optional 객체를 만드려면 어떻게 해야 할까요? 
		 * Optional.of(null)은 허용되지 않습니다. 
		 * Optional.of()는 null이 아닌 객체만 사용할 수 있습니다. 
		 * 반면에 Optional.ofNullable()은 객체 생성 시 null을 허용합니다. 
		 * 이를 이용하면 null을 포함하는 Optional 객체를 만들 수 있습니다.
		 * 
		 * 아래 코드를 실행해보면 nullOpString.get()는 null이기 때문에 NoSuchElementException 예외가 발생됩니다.
		 */
		String nullString = null;
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		try {
			System.out.println(nullOpString.get());
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
		}
	}
	
	@Test
	void test3() {
		System.out.println("\n* Optional.empty()");
		
		/*
		 * Optional.empty()는 null을 포함하는 Optional 객체를 생성합니다. 
		 * 
		 * 역시 emptyOptional.get()는 null이기 때문에 NoSuchElementException 예외가 발생합니다.
		 */
		Optional<String> emptyOptional = Optional.empty();
		try {
			System.out.println(emptyOptional.get());
		} catch (NoSuchElementException e) {
			System.out.println("No such element");
		}
	}
	
	@Test
	void test4() {
		System.out.println("\n* isPresent()");
		
		String nullString = null;
		Optional<String> opString = Optional.of("a string in optional");
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		
		if (opString.isPresent()) {
			System.out.println("opString: " + opString.get());
		}
		if (nullOpString.isPresent()) {
			System.out.println("nullOpString: " + nullOpString.get());
		}
	}
	
	@Test
	void test5() {
		System.out.println("\n* ifPresent()");
		
		/*
		 * void ifPresent(Consumer)는 객체가 null이 아니면 param으로 넘겨준 Consumer 함수를 호출합니다. null인 경우 아무것도 하지 않습니다.
		 * 
		 * 아래 코드에서 nullOpString의 내부 객체는 null이기 때문에 아무것도 출력하지 않습니다. 
		 * 반대로 opString는 null이 아니기 때문에 param을 넘겨준 함수가 호출되었습니다.
		 */
		String nullString = null;
		Optional<String> opString = Optional.of("a string in optional");
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		
		opString.ifPresent(s -> System.out.println("opString: " + s));
		nullOpString.ifPresent(s -> System.out.println("nullOpString: " + s));
	}
	
	@Test
	void test6() {
		System.out.println("\n* orElse()");
		
		/*
		 * 만약 null일 때 다른 값을 갖도록 설정할 수 없을까요? 
		 * orElse(object)가 이런 일을 처리할 수 있습니다. 
		 * 만약 Optional이 null인 경우 orElse()의 param이 리턴됩니다.
		 * 
		 * 아래 코드에서 nullOpString은 null이기 때문에 str2는 "new string from orElse"으로 설정됩니다.
		 */
		String nullString = null;
		Optional<String> opString = Optional.of("a string in optional");
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		
		String str = opString.orElse("new string from orElse");
		System.out.println(str);
		
		String str2 = nullOpString.orElse("new string from orElse");
		System.out.println(str2);
	}
	
	@Test
	void test7() {
		System.out.println("\n* orElseGet()");
		
		/*
		 * 만약 Optional이 null인 경우 어떤 함수를 실행하고 그 실행결과를 대입하고 싶을 수 있습니다. 
		 * 이런 일은 orElseGet(Supplier)가 할 수 있습니다. Supplier는 함수로, 람다 표현식을 사용하면 됩니다.
		 * 
		 * 아래 코드에서 nullOpString는 null을 포함하기 때문에 str4는 "new string from orElseGet"로 설정됩니다.
		 */
		String nullString = null;
		Optional<String> opString = Optional.of("a string in optional");
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		
		String str3 = opString.orElseGet(() -> "new string from orElseGet");
		System.out.println(str3);
		
		String str4 = nullOpString.orElseGet(() -> "new string from orElseGet");
		System.out.println(str4);
	}
	
	@Test
	void test8() {
		System.out.println("\n* orElseThrow()");
		
		/*
		 * Optional이 null인 경우 예외를 던지고 싶을 수 있습니다. 
		 * 이런 일은 orElseThrow()가 할 수 있습니다. Optional이 참조하는 객체가 null인 경우 예외를 던집니다.
		 * 
		 * 아래 코드에서 nullOpString는 null이기 때문에 NullPointerException를 던집니다.
		 */
		String nullString = null;
		Optional<String> opString = Optional.of("a string in optional");
		Optional<String> nullOpString = Optional.ofNullable(nullString);
		
		try {
			String str5 = opString.orElseThrow(NullPointerException::new);
			System.out.println(str5);
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		}
		
		try {
			String str6 = nullOpString.orElseThrow(NullPointerException::new);
			System.out.println(str6);
		} catch (NullPointerException e) {
			System.out.println("NullPointerException");
		}
	}
	
	@Test
	void test9() {
		System.out.println("\n* filter()");
		
		/*
		 * 이번에는 Optional.filter(Predicate)를 알아보겠습니다. 
		 * Optional은 내부에 1개의 아이템만 갖고 있어 filter라는 것이 의미상 어색할 수 있습니다. 
		 * Optional 객체에 filter를 사용하면, Optional의 내부 객체가 Predicate 조건에 부합하면 이 객체를 포함한 Optional 객체가 리턴됩니다. 
		 * 그렇지 않으면 Empty optional 객체가 리턴됩니다.
		 * 
		 * 아래 코드를 보시면, opStr2는 first를 포함하고 있지 않기 때문에 Empty optional 객체가 리턴됩니다. 
		 * 따라서 filtered2는 Empty이므로 아무것도 출력이 되지 않습니다.
		 */
		Optional<String> opStr1 = Optional.of("first string");
		Optional<String> opStr2 = Optional.of("second string");
		Optional<String> filtered1 = opStr1.filter(s -> s.contains("first"));
		Optional<String> filtered2 = opStr2.filter(s -> s.contains("first"));
		filtered1.ifPresent(System.out::println);
		filtered2.ifPresent(System.out::println);
	}

}
