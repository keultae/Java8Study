package com.study;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication

// 지정한 패키지 이하을 모두 검색 
@ComponentScan("com.study.api")
//@ComponentScan("com.study.api, com.study.category")

// 지정한 패키지 이하의 인터페이스들을 전부 맵퍼로 등록하기 때문
// 같은 패키지에 Service 인터페이스가 있으면 맵퍼로 등록이 되어서 ServiceImpl 호출이 안됨
// MapperScan은 소스 폴더가 아닌 Resources 폴더에 Mapper.xml이 있을때 사용하고 @ComponentScan을 사용한다면 사용하지 말아야 한다.
// @MapperScan을 사용해야 한다면 Mapper와 Service 인터페이스를 같은 패키지에 두지 말아야 한다.
//@MapperScan(value= {"com.study.api.mybatis"})

@ImportResource("classpath:application-context.xml")
public class KeultaeApplication {

	public static void main(String[] args) {
		SpringApplication.run(KeultaeApplication.class, args);
	}

}
