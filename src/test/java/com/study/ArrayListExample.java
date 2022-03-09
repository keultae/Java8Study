package com.study;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ArrayListExample {
	@Test
	void test() {
		System.out.println("\n* String[] 배열을 List<String> 리스트로 변환");
//		String[] array = { "a1", "a2", "b1", "b2", "c1", "c2" };
		String[] array =  "a1,a2,b1,b2,c1,c2".split(",");
		// 배열에서 아쉬운 부분 값이 출력되지 않음.
		System.out.println(array);
		
		List<String> list = Arrays.asList(array);
//		List<String> list = Arrays.stream(array).collect(Collectors.toList());
		// 리스트는 값이 출력됨
		System.out.println(list);
	}
	
	@Test
	void test1() {
		System.out.println("\n* List<String> 리스트를 String[] 배열로 변환");
		List<String> list = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2");
		String[] array = (String[]) list.toArray();
//		String[] array = list.stream().toArray(String[]::new);
		
		System.out.println(list);

		System.out.println(array);
		for(String str: array) {
			System.out.println(str);
		}
	}
	
	@Test
	void test2() {
		System.out.println("\n* List<String> 리스트 복사");
		List<String> list1 = Arrays.asList("a1", "a2", "b1", "b2", "c1", "c2");
		List<String> list2= list1;
		
		list1.set(0, "CHANGE");
		
		// list2는 list2의 주소를 참조를 했기 때문에, list1의 값을 바꾸면 list2의 값도 바뀜
		System.out.println(list1);
		System.out.println(list2);
		
		List<String> list3 = new ArrayList<String>(list1);
		list1.set(0, "A1");
		System.out.println(list1);
		System.out.println(list3);
	}

}
