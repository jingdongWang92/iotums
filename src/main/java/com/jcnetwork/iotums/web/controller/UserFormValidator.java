package com.jcnetwork.iotums.web.controller;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcnetwork.iotums.entity.User;
import com.jcnetwork.iotums.service.UserService;

/**
 * 
 * 用户注册数据校验
 * </p>
 * 2016/9/18
 * </p>
 * 
 * @author Jingdong Wang
 */
@Component
public class UserFormValidator {

	@Autowired
	private UserService userService;

	public void validate(Object target) throws Exception {
		User user = (User) target;
		if (user == null) {
			throw new Exception("请求参数错误");
		}
		validateUsername(user);
		validatePassword(user);
		validateEmail(user);
	}

	private void validatePassword(User formUser) throws Exception {
		final String password = formUser.getPassword();
		if (StringUtils.isBlank(password)) {
			throw new Exception("密码不能为空");
		} else {
			int length = password.trim().length();
			if (length < 6 || length > 16) {
				throw new Exception("密码必须为6到16位的字符或数字");
			}
		}
	}

	private void validateUsername(User formUser) throws Exception {
		final String username = formUser.getUsername();
		if (StringUtils.isBlank(username)) {
			throw new Exception("用户名不能为空");
		}

		boolean existed = userService.isExistedUsername(username);
		if (existed) {
			throw new Exception("用户名已被占用");
		}
	}

	private void validateEmail(User formUser) throws Exception {
		final String email = formUser.getEmail();
		if (StringUtils.isBlank(email)) {
			throw new Exception("邮箱不能为空");
		}
		if (!isEmial(email)) {
			throw new Exception("邮箱格式不正确");
		}

		boolean existed = userService.isExistedEmail(email);
		if (existed) {
			throw new Exception("邮箱已被占用");
		}
	}

	public static boolean isEmial(String str) {
		Pattern pattern = Pattern.compile(
				"^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
		return pattern.matcher(str).matches();
	}
}
