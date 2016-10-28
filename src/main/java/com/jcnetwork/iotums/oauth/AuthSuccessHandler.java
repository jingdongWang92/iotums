package com.jcnetwork.iotums.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 登录成功后的操作 
 * 
 * @author Jingdong Wang
 *
 */	         
public class AuthSuccessHandler implements AuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		WdcyUserDetails user = (WdcyUserDetails) authentication.getPrincipal();
		System.out.println(user.toString());
	}

}
