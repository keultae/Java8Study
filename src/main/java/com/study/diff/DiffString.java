package com.study.diff;

import java.util.LinkedList;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

public class DiffString {

	public static void main(String[] args) {
		DiffMatchPatch dmp = new DiffMatchPatch();
		String text1 = "희망^나누^한국^장학^재단^김예린^니다";
//		String test2 = "희망을 나누는 한국 장학재단  김예린 입니다 무엇을 도와드릴까요";
		String test2 = "희망을 나누는 한국 장학재단입니다 무엇을 도와드릴까요";
		LinkedList<Diff> diff = dmp.diffMain(text1, test2, false);
		int equalCnt = 0;
		StringBuffer sb = new StringBuffer();
		for(Diff d: diff) {
//			System.out.println(d.toString());
			if(d.operation == DiffMatchPatch.Operation.EQUAL) {
				equalCnt++;
				System.out.println("[" + equalCnt + "] = " + d.text);
			}
		}
		System.out.println("equalCnt=" + equalCnt);
	}

}
