package com.jcnetwork.iotums.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jcnetwork.iotums.entity.User;

/**
 * @author Jingdong Wang
 */
public interface UserService extends UserDetailsService {

	User loadCurrentUser();

    List<User> loadUserOverview(User user);

    /**
     * 检查用户名是否已经被占用
     * 
     * @param username 用户名
     * @return 被占用返回true,否则返回false
     */
    boolean isExistedUsername(String username);

    void saveUser(User user) throws Exception ;

    /**
     * 检查邮箱是否已经被占用
     * 
     * @param email 邮箱
     * @return 被占用返回true,否则返回false
     */
	boolean isExistedEmail(String email);

}