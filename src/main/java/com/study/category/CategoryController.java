package com.study.category;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.study.api.InfoController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class CategoryController {

	@GetMapping("/cate/test")
	public String test() {
		log.info("START");
		return "TEST";
	}

}
