package com.study.diff;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

public class DiffString {

	public static void main(String[] args) {
		DiffMatchPatch dmp = new DiffMatchPatch();
		String text1 = "희망^나누^한국^장학^재단^김예린^니다";
//		String test2 = "희망을 나누는 한국 장학재단  김예린 입니다 무엇을 도와드릴까요";
		String test2 = "희망을 나누는 한국 장학재단입니다 무엇을 도와드릴까요";
		LinkedList<Diff> diffList = dmp.diffMain(text1, test2, false);
		int equalCnt = 0;
		StringBuffer sb = new StringBuffer();
		for(Diff d: diffList) {
//			System.out.println(d.toString());
			if(d.operation == DiffMatchPatch.Operation.EQUAL) {
				equalCnt++;
				System.out.println("[" + equalCnt + "] = " + d.text);
			}
		}
		System.out.println("equalCnt=" + equalCnt);
		
		// EQUAL인 항목의 text를 List<String)으로 변환
		List<String> strList = diffList.stream().filter( (d) -> d.operation == DiffMatchPatch.Operation.EQUAL )
				.map(d -> d.text).collect(Collectors.toList());
		System.out.println("strList: " + strList);
		
		// 갯수만 구할때
		int cnt = (int) diffList.stream().filter( (d) -> d.operation == DiffMatchPatch.Operation.EQUAL ).count();
		
		System.out.println("cnt: " + cnt);
	}

}
