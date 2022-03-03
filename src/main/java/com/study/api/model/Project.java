package com.study.api.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

//ALWAYS : 속성의 값에 의존하지 말고 항상 포함
//NOT_EMPTY : null 또는 값이 빈 경우가 아니면 포함
//NOT_NULL : null 이 아니면 포함
//NOT_DEFAULT : bean의 기본생성자로 정의된 필드값과 다르게 변경된 필드만 포함
@JsonInclude(JsonInclude.Include.NON_NULL)	// null이 아닌것만 return에 포함
@ToString
public class Project {
	// return 프로퍼티 이름 변경, "projectName": "keultae" => "name": "keultae"
	@JsonProperty(value="name")
	public String projectName;
	public String author;
	
//	@JsonIgnore	// createDate json 리턴시 프로퍼티를 포함하지 않음 
	// 날짜 시간 값을 직렬화할 때 형식을 지정
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.SSS")
	public Date createDate;
}
