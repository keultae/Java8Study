package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

class SortedExample {

	@Test
	void test() {
		System.out.println("* sorted()");
		
		/*
		 * sorted는 스트림의 아이템들을 정렬하여 새로운 스트림을 생성합니다. 
		 * sorted()는 param이 없습니다. 
		 * 그렇기 때문에 이를 사용하려면 정렬하려는 객체에 Comparable 인터페이스가 구현되어있어야 사용이 가능합니다. 
		 * String은 기본적으로 Comparable이 구현되어 있습니다. 그렇게 때문에 아래 코드는 정상적으로 동작합니다.
		 */
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "smalltalk");
		System.out.println("sorted:");
		langs.stream().sorted().forEach(System.out::println);
		
		System.out.println("reversed:");
		langs.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* sorted(Comparator)");
		
		/*
		 * 정렬하려는 객체가 Comparable 인터페이스를 구현하지 않았다면 sorted(Comparator)를 사용하면 됩니다. 이 경우 Comparator를 정의해야 합니다.
		 * 
		 * 아래 코드는 sorted의 param을 Comparator.comparing(String::length)로 설정하였습니다. 
		 * 이것은 스트링의 길이를 비교하는 Comparator를 생성합니다. 역순으로 정렬하려면 .reversed()를 붙이면 됩니다.
		 */
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "smalltalk");
		System.out.println("sorted:");
		langs.stream().sorted(Comparator.comparing(String::length))
		.forEach(System.out::println);
		
		System.out.println("reversed:");
		langs.stream().sorted(Comparator.comparing(String::length).reversed())
		.forEach(System.out::println);
	}
	
	@Test
	void test2() {
		System.out.println("* sorted(Comparator) 2");
		
		/*
		 */
		List<String> langs = Arrays.asList("java", "kotlin", "haskell", "ruby", "smalltalk");
		
		System.out.println("sorted:");
		final Comparator<String> comp = (p1, p2) -> Integer.compare( p1.length(), p2.length());
		langs.stream().sorted(comp)
		.forEach(System.out::println);
		
		System.out.println("reversed:");
		langs.stream().sorted(comp.reversed())
		.forEach(System.out::println);
	}
	
	@Test
	void test3() {
		System.out.println("* sorted() + custom class");
		
		// MyString class 정의
		class MyString implements Comparable<MyString> {
		    public String str;

		    public MyString(String str) {
		        this.str = str;
		    }

		    @Override
		    public int compareTo(MyString o) {
		        return Integer.compare(this.str.length(), o.str.length());
		    }

		    @Override
		    public int hashCode() {
		        return str.hashCode();
		    }

		    @Override
		    public String toString() {
		        return str;
		    }
		}
		

		List<MyString> langs2 =
		        Arrays.asList(new MyString("java"), new MyString("kotlin"),
		                new MyString("haskell"), new MyString("ruby"),
		                new MyString("smalltalk"));

		langs2.stream().sorted().forEach(System.out::println);
		
		/*
		 * 객체가 Comparable 인터페이스를 구현하지 않았을때
		 */
		langs2.stream().sorted((a, b) -> Integer.compare(a.str.length(), b.str.length())).forEach(System.out::println);
	}

}
