package com.study;

import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.jupiter.api.Test;

import com.study.FunctionalInterfaceSummary.FunctionInterface;

class FunctionalInterfaceSummary {
	/**
	 * 함수형 인터페이스는 1개의 추상 메소드를 갖고 있는 인터페이스를 말합니다.
	 * Single Abstrace Method(SAM)라고 불리기도 합니다.
	 * 
	 * @author chochongtae
	 *
	 */
	public interface FunctionInterface {
		public abstract void doSomeghing(String text);
	}
	
	
	@Test
	void test() {
		System.out.println("* 람다식과 익명 클래스");
		
		/**
		 * 함수형 인터페이스를 사용하는 이유는 자바 람다식은 함수형 인터페이스로만 접근이 되기 때문입니다.
		 * 
		 * 아래 코드에서 변수 func는 람다식으로 생성한 객체를 가리키고 있습니다.
		 * doSomething()에 인자로 문자열을 전달하면 람다식에서 정의된 것처럼 로그를 출력합니다.
		 */
		FunctionInterface func = text -> System.out.println(text);
		
		func.doSomeghing("do something");
		
		/**
		 * 아래 코드는 익명 클래스를 사용하여 리팩터링한 코드입니다.
		 * 
		 * 함수형 인터페이스와 람다식으로 익명 클래스를 간단하게 표현했다고 생각할 수 있습니다. 
		 */
		func = new FunctionInterface() {
			@Override
			public void doSomeghing(String text) {
				System.out.println(text);
			}
		};
		func.doSomeghing("do something");
	}

	
	/**
	 * 람다식을 사용할 때마다 함수형 인터페이스를 매번 정의하기에는 불편하기 때문에 자바에서 라이브러로 제공한느 것들이 있습니다.
	 * 
	 * 자바에서 기본적으로 제공하는 함수형 인터페이스는 다음과 같은 것들이 있습니다.
	 * 	Runnable
	 * 	Supplier
	 * 	Consumer
	 * 	Function<T, R>
	 * 	Prdeicate
	 * 추가적인 것은 https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html 참조 
	 */
	@Test
	void test1() {
		System.out.println("* 기본 함수형 인터페이스 Runnable");
		Runnable runnable = () -> System.out.println("run anything!");
		runnable.run();
	}
	
	@Test
	void test2() {
		System.out.println("* 기본 함수형 인터페이스 Supplier");
		Supplier<String> getString = () -> "Happy new year!";
		String str = getString.get();
		System.out.println(str);
	}
	
	@Test
	void test3() {
		System.out.println("* 기본 함수형 인터페이스 Consumer");
		Consumer<String> printString = text -> System.out.println("Miss " + text + "?");
		printString.accept("me");
		
		Consumer<String> printString2 = text -> System.out.println("--> Yes");
		// andThen()을 사용하면 두개 이상의 Consumer를 연속적으로 실행할 수 있습니다.
		printString.andThen(printString2).accept("me");
	}
	
	@Test
	void test4() {
		System.out.println("* 기본 함수형 인터페이스 Function");
		Function<Integer, Integer> multiplay = (value) -> value * 2;
		Integer result = multiplay.apply(3);
		System.out.println(result);

		/**
		 * compose()는 두개의 Function을 조합하여 새로운 Function 객체를 만들어주는 메서드입니다.
		 * 주의할 점은 andThen()과는 실행 순서가 반대입니다.
		 * compose()에 인자로 전달되는 Function이 먼저 수행되고 그 이후에 호출하는 객체의 Function이 수행됩니다.
		 */
		Function<Integer, Integer> add = (value) -> value + 3;
		Function<Integer, Integer> andThenMultiply = multiplay.compose(add);
		Integer result1 = andThenMultiply.apply(3);
		System.out.println(result1);
		assertEquals(12, result1);
		
		Integer result3 = multiplay.andThen(add).apply(3);
		System.out.println(result3);
		assertEquals(9, result3);
		
		
		// Integer 인자를 받고, String 리턴
		Function<Integer, String> convertStr = (value) -> "STR-" + String.valueOf(value);
		System.out.println(convertStr.apply(11));
	}
	
	
	@Test
	void test5() {
		System.out.println("* 기본 함수형 인터페이스 Predicate");
		Predicate<Integer> isBiggerThanFive = num -> num > 5;
		System.out.println("10 is bigger than 5? -> " + isBiggerThanFive.test(10));
		
		Predicate<Integer> isLowerThanSix = num -> num < 5;
		boolean result1 = isBiggerThanFive.and(isLowerThanSix).test(10);
		System.out.println(result1);
		assertFalse(result1);
		
		boolean result2 = isBiggerThanFive.or(isLowerThanSix).test(10);
		System.out.println(result2);
		assertTrue(result2);
		
	}
}
