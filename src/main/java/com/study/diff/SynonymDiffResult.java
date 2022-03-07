package com.study.diff;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SynonymDiffResult {
	private int equalWordCnt = 0;			// 일치 단어 갯수
	private List<String> equalWordList = new ArrayList<String>();	// 일치하는 단어
	private List<String> diffWordList = new ArrayList<String>();	// 불일치 단어
	
	public int addEqualWord(String word) {
		this.equalWordList.add(word.trim());
		this.equalWordCnt += 1;
		return this.equalWordList.size();
	}
	
	public int addDiffWord(String word) {
		this.diffWordList.add(word.trim());
		return this.diffWordList.size();
	}
}
