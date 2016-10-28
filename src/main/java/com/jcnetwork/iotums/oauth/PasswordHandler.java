package com.jcnetwork.iotums.oauth;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

/**
 * 2016/9/18
 * <p/>
 * 处理账号密码, 使用MD5加密
 *
 * @author Jingdong Wang
 */
public abstract class PasswordHandler {


    private PasswordHandler() {
    }


    public static String md5(String password) {
        Md5PasswordEncoder encoder = new Md5PasswordEncoder();
        return encoder.encodePassword(password, null);
    }
}
