# Java8 Study

## Category 관리
조직도처럼 계층 형태로 데이터를 관리하기 위한 테이블 구조와 프로그램을 정리합니다.<br>
### 목표<br>
1. 테이블 갯수 최소화
2. 쿼리 조인 최소화<br>
조직도를 그리거나 직원의 조직 정보를 가져올때

### 테이블 명세서
1. org
```sql
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
```
2. emp
```sql
DROP TABLE emp;
CREATE TABLE emp (
  	id varchar(20) NOT NULL,
	password varchar(20) NOT NULL,
  	name varchar(20) default NULL,
  	email varchar(20) DEFAULT NULL,
  	use_yn char(1) DEFAULT 'Y',
  	created timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  	
  	org_id int NOT NULL,
  	
	PRIMARY KEY (id)
);
```

