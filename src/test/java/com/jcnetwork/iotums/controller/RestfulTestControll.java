package com.jcnetwork.iotums.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcnetwork.iotums.entity.User;

import net.sf.json.JSONObject;

/**
 * Restful 测试
 * 
 * @author Jingdong Wang
 *
 */
@Controller
public class RestfulTestControll {

	/** 日志实例 */
	private static final Logger logger = Logger.getLogger(RestfulTestControll.class);

	@RequestMapping(value = "/hello", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String hello() {
		return "你好！hello";
	}
	
	@RequestMapping(value = "getCode", produces = "text/plain;charset=UTF-8")
	public @ResponseBody
	String getCode() {
		return "你好！hello";
	}

	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody
	String say(@PathVariable(value = "msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@RequestMapping(value = "/User/{id:\\d+}", method = RequestMethod.GET)
	public @ResponseBody
	User getUser(@PathVariable("id") int id) {
		logger.info("获取人员信息id=" + id);
		User User = new User();
		User.setUsername("张三");
		User.setId(id);
		return User;
	}

	@RequestMapping(value = "/User/{id:\\d+}", method = RequestMethod.DELETE)
	public @ResponseBody
	Object deleteUser(@PathVariable("id") int id) {
		logger.info("删除人员信息id=" + id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "/User", method = RequestMethod.POST)
	public @ResponseBody
	Object addUser(User User) {
		logger.info("注册人员信息成功id=" + User.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "注册人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "/User", method = RequestMethod.PUT)
	public @ResponseBody
	Object updateUser(User User) {
		logger.info("更新人员信息id=" + User.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新人员信息成功");
		return jsonObject;
	}

	@RequestMapping(value = "/User", method = RequestMethod.PATCH)
	public @ResponseBody
	List<User> listUser(@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		logger.info("查询人员name like " + name);
		List<User> lstUsers = new ArrayList<User>();

		User User = new User();
		User.setUsername("张三");
		User.setId(101);
		lstUsers.add(User);

		User User2 = new User();
		User2.setUsername("李四");
		User2.setId(102);
		lstUsers.add(User2);

		User User3 = new User();
		User3.setUsername("王五");
		User3.setId(103);
		lstUsers.add(User3);

		return lstUsers;
	}

}
