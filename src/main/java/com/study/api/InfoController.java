package com.study.api;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.study.api.model.Project;
import com.study.api.model.Script;
import com.study.common.LogUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InfoController {
	private static final String[] T = LogUtil.T;
	
	@Autowired
	EvalNormal evalNormal;
	
	@Autowired
    private ApplicationContext applicationContext;
	
	//@Autowired와 @Qualifier를 함께 사용하면 @Qualifier에 지정된 이름과 같은 이름의 빈을 찾아 자동 주입된다.
//	@Qualifier("obj2")
	
	// 빈 생성자 호출 이후 자동으로 호출될 메서드
    @PostConstruct
    public void init() {
    	log.info("init 메서드 호출");
    }
    
    //빈 객체가 소멸될 때 자동으로 호출되는 메서드를 등록
    @PreDestroy
    public void destroy() {
    	log.info("destroy 메서드 호출");
    }
    
	@GetMapping("/info")
	public Object info() {
		log.info("START");
		Project project = new Project();
		project.projectName = "keultae";
//		project.author = "hello spring boot";
		project.author = null;
		project.createDate = new Date();
		log.debug("{}{}", T[1], project.toString());
		
		evalNormal.eval("111111");
		return project;
	}
	
	@GetMapping("/info2")
	public String info2() {
		log.info("START");
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
		log.debug("{}{}", T[1], jo.toString());
		return jo.toString();
	}
	
	@GetMapping("/beans")
	public String beans() {
		log.info("START");
		
		if (applicationContext != null) {
            String[] beans = applicationContext.getBeanDefinitionNames();


            
            for (String bean : beans) {
            	log.info("bean : {}, {}", bean, applicationContext.getBean(bean).getClass().getName());
//        		if(applicationContext.getBean(bean) instanceof Script ) {
//        			log.info("bean : {}, {}", bean, applicationContext.getBean(bean).getClass().getName());
//        			
//                    Script script = (Script) applicationContext.getBean(bean);
//                    log.info(script.toString());
//                    log.info(script.getType());
//        		}
            }
        }
		
		return "OK";
	}
}
