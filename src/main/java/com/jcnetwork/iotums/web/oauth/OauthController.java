package com.jcnetwork.iotums.web.oauth;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/oauth/authorize")
public class OauthController {
	
	@RequestMapping(value="getCode",produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object getOauthCode(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		System.out.println(code);
		System.out.println(state);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("code", code);
		jsonObject.put("state", state);
		return jsonObject;

	}

}
