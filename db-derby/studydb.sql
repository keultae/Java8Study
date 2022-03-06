DROP TABLE org;
CREATE TABLE org (
	id integer NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	
	lev1_id integer NOT NULL,
	lev1_nm varchar(30) NOT NULL,
	
	lev2_id integer DEFAULT 0,
	lev2_nm varchar(30) DEFAULT '',

	lev3_id integer DEFAULT 0,
	lev3_nm varchar(30) DEFAULT '',

	use_yn char(1) DEFAULT 'Y',
	
	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	updated timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
	
	PRIMARY key(id)
);

SELECT * FROM org;

SELECT
	id, lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm, use_yn, created, updated, 
	CASE 
		WHEN lev2_id = 0 THEN 1
		WHEN lev3_id = 0 THEN 2
		ELSE 3
	END AS level
FROM org ORDER BY lev1_id, lev2_id, lev3_id ;

SELECT * FROM org ORDER BY lev1_nm, lev2_nm, lev3_nm ;

SELECT CASE WHEN max(LEV1_ID) is NULL THEN 0 ELSE max(lev1_id) END + 1 AS next_lev1_id  FROM org;

INSERT INTO org (lev1_id, lev1_nm) values((SELECT CASE WHEN max(LEV1_ID) is NULL THEN 0 ELSE max(lev1_id) END + 1 AS next_lev1_id  FROM org), '검색그룹');
INSERT INTO org (lev1_id, lev1_nm) values((SELECT CASE WHEN max(LEV1_ID) is NULL THEN 0 ELSE max(lev1_id) END + 1 AS next_lev1_id  FROM org), 'NLP그룹');
INSERT INTO org (lev1_id, lev1_nm) values((SELECT CASE WHEN max(LEV1_ID) is NULL THEN 0 ELSE max(lev1_id) END + 1 AS next_lev1_id  FROM org), 'AI그룹');
INSERT INTO org (lev1_id, lev1_nm) values((SELECT CASE WHEN max(LEV1_ID) is NULL THEN 0 ELSE max(lev1_id) END + 1 AS next_lev1_id  FROM org), '공용그룹');

INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm) values(2, 'NLP그룹', 
	(SELECT CASE WHEN max(LEV2_ID) is NULL THEN 0 ELSE max(lev2_id) END + 1 AS next_lev2_id  FROM org), 'NLP-기반기술본부');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm) values(2, 'NLP그룹', 
	(SELECT CASE WHEN max(LEV2_ID) is NULL THEN 0 ELSE max(lev2_id) END + 1 AS next_lev2_id  FROM org), 'NLP-빅데이터본부');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm) values(2, 'NLP그룹', 
	(SELECT CASE WHEN max(LEV2_ID) is NULL THEN 0 ELSE max(lev2_id) END + 1 AS next_lev2_id  FROM org), 'NLP-AI사업본부');

INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-영업팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI1팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI2팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI3팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI4팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI5팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI6팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI7팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 3, 'NLP-AI사업본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-AI8팀');

INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 1, 'NLP-기반기술본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-기반기술1팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 1, 'NLP-기반기술본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-기반기술2팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 1, 'NLP-기반기술본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-기반기술3팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 1, 'NLP-기반기술본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-기반기술4팀');
INSERT INTO org (lev1_id, lev1_nm, lev2_id, lev2_nm, lev3_id, lev3_nm) values(2, 'NLP그룹', 1, 'NLP-기반기술본부', 
	(SELECT CASE WHEN max(LEV3_ID) is NULL THEN 0 ELSE max(lev3_id) END + 1 AS next_lev3_id  FROM org), 'NLP-UI/UX팀');


DROP TABLE emp;
CREATE TABLE emp (
  	id varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
  	name varchar(20) default NULL,
  	email varchar(50) DEFAULT NULL,
  	use_yn char(1) DEFAULT 'Y',
  	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	
  	org_id int NOT NULL,
  	
	PRIMARY KEY (id)
);

SELECT * FROM emp;

SELECT 
	org.*
	, emp.*
FROM 
	org LEFT JOIN emp ON org.id = emp.org_id 
WHERE 
	org.use_yn = 'Y' AND (emp.use_yn = 'Y' OR emp.use_yn IS NULL)
;


INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai7_teamjang', '12345678', 'NLP-AI7팀|팀장', 'nlp_ai7_teamjang@diquest.com', 15);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai7_daeli1', '12345678', 'NLP-AI7팀|대리1', 'nlp_ai7_daeli1@diquest.com', 15);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai7_daeli2', '12345678', 'NLP-AI7팀|대리1', 'nlp_ai7_daeli2@diquest.com', 15);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai7_daeli3', '12345678', 'NLP-AI7팀|대리1', 'nlp_ai7_daeli3@diquest.com', 15);

INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_teamjang', '12345678', 'NLP-AI8팀|팀장', 'nlp_ai8_teamjang@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_gwajang1', '12345678', 'NLP-AI8팀|과장1', 'nlp_ai8_gwajang1@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon1', '12345678', 'NLP-AI8팀|사원1', 'nlp_ai8_sawon1@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon2', '12345678', 'NLP-AI8팀|사원2', 'nlp_ai8_sawon2@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon3', '12345678', 'NLP-AI8팀|사원3', 'nlp_ai8_sawon3@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon4', '12345678', 'NLP-AI8팀|사원4', 'nlp_ai8_sawon4@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon5', '12345678', 'NLP-AI8팀|사원5', 'nlp_ai8_sawon5@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon6', '12345678', 'NLP-AI8팀|사원6', 'nlp_ai8_sawon6@diquest.com', 16);
INSERT INTO emp (id, password, name, email, org_id) values('nlp_ai8_sawon7', '12345678', 'NLP-AI8팀|사원7', 'nlp_ai8_sawon7@diquest.com', 16);
