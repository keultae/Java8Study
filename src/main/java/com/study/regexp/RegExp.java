package com.study.regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExp {

	public static void main(String[] args) {
//		Pattern p1 = Pattern.compile("희망[가-힣]{1,10} ?나누[가-힣]{1,10}");
		Pattern p1 = Pattern.compile("(희망)[가-힣]{1,10} ?(나누)[가-힣]{1,10}");
		Matcher m1 = p1.matcher("희망을 나누는 한국 장학재단  김예린 입니다 무엇을 도와드릴까요");
		
		if(m1.find()) {
			System.out.println(m1.toString());
			System.out.println(m1.groupCount());
			
			System.out.println(m1.group());
			System.out.println(m1.group(0));
			System.out.println(m1.group(1));
			System.out.println(m1.group(2));
		}

	}

}

