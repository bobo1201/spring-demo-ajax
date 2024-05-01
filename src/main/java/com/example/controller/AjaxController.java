package com.example.controller;

import com.example.domain.DataSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AjaxController {
	private static Logger logger = LoggerFactory.getLogger(AjaxController.class);

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home(){
		return "index";
	}

	@ResponseBody
	@RequestMapping(value = "/test1", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	public List<Map<String, String>> test1(@ModelAttribute("a") String a, @RequestParam("b") String b){
		logger.info("Test List.....");
		logger.info("a : " + a);
		logger.info("b : " + b);
		List<String> testList = new ArrayList<>();
		testList.add(a);
		testList.add(b);



		List<Map<String, String>> listMap= new ArrayList<>();

		Map<String, String> map = new HashMap<>();

		map.put("a", a);
		map.put("b", b);

		listMap.add(map);

		logger.info("listMap : " + listMap);


		return listMap;
	}


	// Test Case - 1
	@ResponseBody
	@RequestMapping(value="/list", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<String> list(@ModelAttribute("username") String username, @RequestParam("password") String password){
		logger.info("Request List....");
		logger.info("username : "+username);
		logger.info("password : "+password);
		List<String> response = new ArrayList<String>();
		response.add(username);
		response.add(password);
//		response.add(username);
//		response.add(username);

		return response;
	}

	// Test Case - 2
	@ResponseBody
	@RequestMapping(value="/list_model", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<String> list_model(@ModelAttribute("dataSet") DataSet dataSet){
		logger.info("Request List_Model.... - {}", dataSet);
		List<String> response = new ArrayList<String>();
		response.add(dataSet.getUsername());
		response.add(dataSet.getPassword());
		return response;
	}


	// Test Case - 3
	@RequestMapping(value="/list_nobody", method=RequestMethod.GET, consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<String> list_noresponsebody(@ModelAttribute("username") String username, @RequestParam("password") String password){
		logger.info("Request List....");
		logger.info("username : "+username);
		logger.info("password : "+password);
		List<String> response = new ArrayList<String>();
		response.add(username);
		response.add(password);
		return response;
	}
	
	// Test Case - 4
	@ResponseBody
	@RequestMapping(value="/map", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> map(@RequestBody DataSet dataSet){
		logger.info("Request Map.... - {}", dataSet);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("username", dataSet.getUsername());
		response.put("password", dataSet.getPassword());
		return response;
	}
	
	// Test Case - 5
	@ResponseBody
	@RequestMapping(value="/map_get", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public Map<String, Object> map_get(@RequestBody DataSet dataSet){
		logger.info("Request Map_Get.... - {}", dataSet);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("username", dataSet.getUsername());
		response.put("password", dataSet.getPassword());
		return response;
	}
	
	// Test Case - 6
	@ResponseBody
	@RequestMapping(value="/entity", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> entity(@RequestBody DataSet dataSet){
		logger.info("Request Entity.... - {}", dataSet);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", dataSet.getUsername());
		map.put("password", dataSet.getPassword());
		
		//응답과 함깨 HttpStatus를 지정할 수 있습니다.
		ResponseEntity<Object> response = new ResponseEntity<Object>(map, HttpStatus.OK);
		return response;
	}
	
	// Test Case - 7
	//@ResponseBody
	@RequestMapping(value="/entity_nobody", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> entity_nobody(@RequestBody DataSet dataSet){
		logger.info("Request Entity Nobody.... - {}", dataSet);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", dataSet.getUsername());
		map.put("password", dataSet.getPassword());
		
		//응답과 함깨 HttpStatus를 지정할 수 있습니다.
		ResponseEntity<Object> response = new ResponseEntity<Object>(map, HttpStatus.OK);
		return response;
	}
	
	// Test Case - 8
	@RequestMapping(value="/entity_nobody_param", method=RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> entity_nobody_param(@RequestBody DataSet dataSet, @RequestParam String param){
		logger.info("Request Entity Nobody.... - {}", dataSet);
		logger.info("param {}",param);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", dataSet.getUsername());
		map.put("password", dataSet.getPassword());
		
		//응답과 함깨 HttpStatus를 지정할 수 있습니다.
		ResponseEntity<Object> response = new ResponseEntity<Object>(map, HttpStatus.OK);
		return response;
	}
}
