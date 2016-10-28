package com.jcnetwork.iotums.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/device/")
public class TestController {
	
	@RequestMapping(value = "add", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String hello() {
		return "你好！hello";
	}

}
