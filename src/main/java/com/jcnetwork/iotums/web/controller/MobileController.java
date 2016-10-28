package com.jcnetwork.iotums.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcnetwork.iotums.entity.User;
import com.jcnetwork.iotums.service.UserService;

/**
 * @author Jingdong Wang
 */
@Controller
@RequestMapping("/m/")
public class MobileController {

    @Autowired
    private UserService userService;


    @RequestMapping("dashboard")
    public String dashboard() {
        return "mobile/dashboard";
    }

    @RequestMapping("user_info")
    @ResponseBody
    public User userInfo() {
        return userService.loadCurrentUser();
    }

}
