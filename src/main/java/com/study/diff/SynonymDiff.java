package com.study.diff;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch;
import org.bitbucket.cowwoc.diffmatchpatch.DiffMatchPatch.Diff;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SynonymDiff {
	public static SynonymDiffResult diff(String text1, String text2) {
		log.info("START 검색할 단어들={}, 검색할 문장={}", text1, text2);
		List<List<String>> word2List = new ArrayList<List<String>>();
		
		String[] wordArray = text1.trim().split("&");
		for(String word: wordArray) {
//			log.info("\tword={}", word);
			String[] synonymWordArray = word.trim().split("\\|");
			word2List.add(Arrays.asList(synonymWordArray));
		}
		log.info("\t검색할 단어들=" + word2List.toString());
		
		
		DiffMatchPatch dmp = new DiffMatchPatch();
		LinkedList<Diff> diffList = dmp.diffMain(text1, text2, false);
		SynonymDiffResult sdf = new SynonymDiffResult();
		
		List<String> equalWordList = new ArrayList<String>();
		for(Diff d: diffList) {
			if(d.operation == DiffMatchPatch.Operation.EQUAL) {
				equalWordList.add(d.text);
			}
		}
		log.info("\t검색된 단어들={}", equalWordList.toString());
		
		
		int startPos = 0;
		boolean find = false;
		for(int i = 0; i < word2List.size(); i++) {
			log.info("\t--------------------------------------------------");
			find = false;
//			log.info("\tstartPos={}", startPos);
			log.info("\t검색할 단어들에서 {}번째 {} 단어 검색", i+1, word2List.get(i).toString());
			for(int j = startPos; j < equalWordList.size(); j++) {
				List<String> wordList = new ArrayList<String>(word2List.get(i));
				String word = equalWordList.get(j);
				List<String> equalList = Stream.of(word).collect(Collectors.toList());
//				log.info("\t검색할 단어들에서 {}번째 {} 단어 검색", i+1, wordList.toString());
//				log.info("\twordList={}, equalList={}", wordList, equalList);
				log.info("\t\t검색된 단어들에서 {}번째 {} 단어와 비교", j+1, equalList);
				
				// 교집합
				wordList.retainAll(equalList);
				if( wordList.size() > 0 ) {
					log.info("\t\t검색 성공");
					sdf.addEqualWord(word);
					startPos = j + 1;
					find = true;
					break;
				} 
			}
			if(find == false) {
				log.info("\t\t검색 실패");
//				for(String word: word2List.get(i)) {
//					sdf.addDiffWord(word);
//				}
				// map()을 사용해도 되고 안해도 됨
//				String joinStr = word2List.get(i).stream().map(str->str).collect(Collectors.joining("|"));
				String joinStr = word2List.get(i).stream().collect(Collectors.joining("|"));
				sdf.addDiffWord(joinStr);
			}
		}
		log.info("\tsdf={}", sdf.toString());
		
		return sdf;
	}
}
