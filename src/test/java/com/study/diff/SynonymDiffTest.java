package com.study.diff;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class SynonymDiffTest {

	@Test
	void test1() {
		log.info("단어 AND 첫번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 휴대폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(3, sdf.getEqualWordCnt());
		assertEquals("[고객님, 휴대폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test12() {
		log.info("단어 AND 두번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "고객님 핸드폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(3, sdf.getEqualWordCnt());
		assertEquals("[고객님, 핸드폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test13() {
		log.info("단어 AND 세번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 스마트폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(3, sdf.getEqualWordCnt());
		assertEquals("[고객님, 스마트폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test11() {
		log.info("단어 AND 첫번째 유의어 두번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 휴대폰 핸드폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(3, sdf.getEqualWordCnt());
		assertEquals("[고객님, 휴대폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test111() {
		log.info("단어 AND 두번째 유의어 첫번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 핸드폰 휴대폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(3, sdf.getEqualWordCnt());
		assertEquals("[고객님, 핸드폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test2() {
		log.info("단어 AND 유의어 없음 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "고객님 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[고객님, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[휴대폰|핸드폰|스마트폰]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test3() {
		log.info("단어 없음 AND 첫번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "휴대폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[휴대폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[고객님]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test4() {
		log.info("단어 없음 AND 두번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "핸드폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[핸드폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[고객님]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test5() {
		log.info("단어 없음 AND 세번째 유의어 AND 단어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "스마트폰 번호를 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[스마트폰, 번호]", sdf.getEqualWordList().toString());
		assertEquals("[고객님]", sdf.getDiffWordList().toString());
	}

	@Test
	void test6() {
		log.info("단어 AND 첫번째 유의어 AND 단어 없음");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호";
		String text2 = null;
		SynonymDiffResult sdf = null;
	
		text2 = "고객님 핸드폰 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[고객님, 핸드폰]", sdf.getEqualWordList().toString());
		assertEquals("[번호]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test7() {
		log.info("단어 AND 첫번째 유의어 AND 단어 AND 첫번째 유의어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호&이름|성함";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 휴대폰 번호와 이름을 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(4, sdf.getEqualWordCnt());
		assertEquals("[고객님, 휴대폰, 번호, 이름]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test71() {
		log.info("단어 AND 첫번째 유의어 AND 단어 AND 두번째 유의어");
		String text1 = "고객님&휴대폰|핸드폰|스마트폰&번호&이름|성함";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "고객님 휴대폰 번호와 성함을 알려주세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(4, sdf.getEqualWordCnt());
		assertEquals("[고객님, 휴대폰, 번호, 성함]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test8() {
		log.info("첫번째 유의어 AND 단어");
		String text1 = "점심|식사|저녁&맛있게";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "점심 맛있게 드세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[점심, 맛있게]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test81() {
		log.info("두번째 유의어 AND 단어");
		String text1 = "점심|식사|저녁&맛있게";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "식사 맛있게 드세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[식사, 맛있게]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	
	@Test
	void test82() {
		log.info("세번째 유의어 AND 단어");
		String text1 = "점심|식사|저녁&맛있게";
		String text2 = null;
		SynonymDiffResult sdf = null;

		text2 = "저녁 맛있게 드세요";
		sdf = SynonymDiff.diff(text1, text2);
		assertEquals(2, sdf.getEqualWordCnt());
		assertEquals("[저녁, 맛있게]", sdf.getEqualWordList().toString());
		assertEquals("[]", sdf.getDiffWordList().toString());
	}
	// * 기존 정규식
	// (좋은,즐거운,행복한)+(하루,오후,명절,연휴,주말,한가위,추석)~,감사합니다,고맙습니다,좋은(결과,일)+바랍니다,(감기,건강,코로나,무더위,추위) 조심하세요,새해 복 많이 받으세요,건강 유의 하세요,(점심,식사,저녁) 맛있게~
	
	// * 유의어 비교  => STT가 음성을 텍스트로 정확히 변환하지 못하므로 정확도를 높이기 유의어를 등록하거나, 다양한 표현을 사용하고자 할때 유의어를 등록합니다.
	// 좋은|즐거운|행복한&하루|오후|명절|연휴|주말|한가위|추석
	// 감사합니다
	// 고맙습니다
	// 좋은&결과|일&바랍니다
	// 감기|건강|코로나|무더위|추위&조심하세요
	// 새해&복&많이&받으세요
	// 건강&유의&하세요
	// 점심|식사|저녁&맛있게
	
	
	// * 기존 정규식
	// (상담사,저는)+(이었습니다,이였습니다,입니다,였습니다),(감사,행복,좋은,고맙)+(었,였)~
	
	// * 유의어 비교
	// 상담사|저는&이었습니다|이였습니다|입니다|였습니다
	// 감사|행복|좋은|고맙&었|였
}
