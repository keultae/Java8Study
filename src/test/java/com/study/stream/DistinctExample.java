package com.study.stream;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class DistinctExample {

	@Test
	void test() {
		System.out.println("* distinct()");
		
		/*
		 * 어떤 스트림에서 중복되는 아이템들을 모두 제거해주고 새로운 스트림을 반환합니다. 
		 * 아래 코드에서 stream1.distinct()는 스트림에서 중복을 제거한 새로운 스트림을 반환하였습니다. 
		 * 동일한 객체인지 판단하는 기준은 Object.equals(Object)의 결과 값입니다. 
		 * String 객체의 경우 equals()가 이미 구현되어 있기 때문에 의도한대로 동작하였습니다.
		 */
		List<String> strings = Arrays.asList("google", "apple", "google", "apple", "samsung");
		Stream<String> stream1 = strings.stream();
		Stream<String> stream2 = stream1.distinct();
		stream2.forEach(System.out::println);
	}
	
	@Test
	void test1() {
		System.out.println("* distinct() custom class ");
		
		class MyString {
			public String str;
			
			public MyString(String str) {
				this.str = str;
			}
			
			@Override
			public boolean equals(Object o) {
				if (o instanceof MyString) {
					return str.equals(((MyString) o).str);
				}
				return false;
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
		
		/*
		 * 새로 정의하는 class의 경우 distinct를 사용하려면 equals()가 재정의되어야 합니다. 
		 * 중요한 것은 hashCode()도 재정의해야 한다는 것입니다. 
		 * distinct는 내부적으로 hashCode()를 이용하여 객체가 서로 다른 것을 확인하고 equals()로 컨텐츠가 같은지 체크하는 것 같습니다. 
		 * 둘 중에 하나라도 재정의되어 있지 않으면 의도한대로 동작하지 않습니다.
		 */
		List<MyString> myStrings =
				Arrays.asList(new MyString("google"),new  MyString("apple"),
						new MyString("google"), new MyString("apple"), new MyString("samsung"));
		Stream<MyString> stream3 = myStrings.stream();
		Stream<MyString> stream4 = stream3.distinct();
		stream4.forEach(System.out::println);
	}

}
