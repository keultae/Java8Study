package com.study.api;

import java.util.Date;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.study.api.model.Project;

@RestController
public class InfoController {
	@GetMapping("/info")
	public Object info() {
		Project project = new Project();
		project.projectName = "keultae";
//		project.author = "hello spring boot";
		project.author = null;
		project.createDate = new Date();
		
		return project;
	}
	
	@GetMapping("/info2")
	public String info2() {
		/**
		 * spring boot에서 jackson을 선택했으니,
		 * 기본으로 jakson을 사용하고
		 * gson으로 하면 간단한 작업들(jackson 으로 하면 불편한것?)이 있다면 gson으로
		 */
		JsonObject jo = new JsonObject();
		
		jo.addProperty("projectName", "keultae");
		jo.addProperty("author", "hello spring boot");
		jo.addProperty("createDate", new Date().toString());
		
		JsonArray ja = new JsonArray();
		for(int i = 0; i < 5; i++) {
			JsonObject jObj = new JsonObject();
			jObj.addProperty("prop"+i, i);
			ja.add(jObj);
		}
		jo.add("fllower", ja);
		
		/*
		 * return 
		{
		   "projectName":"keultae",
		   "author":"hello spring boot",
		   "createDate":"Thu Mar 03 06:51:40 KST 2022",
		   "fllower":[
		      {
		         "prop0":0
		      },
		      {
		         "prop1":1
		      },
		      {
		         "prop2":2
		      },
		      {
		         "prop3":3
		      },
		      {
		         "prop4":4
		      }
		   ]
		}
		 */
		return jo.toString();
	}
}
