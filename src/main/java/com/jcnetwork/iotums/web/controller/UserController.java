package com.jcnetwork.iotums.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jcnetwork.iotums.common.CommonEnum;
import com.jcnetwork.iotums.entity.BaseRespEntity;
import com.jcnetwork.iotums.entity.User;
import com.jcnetwork.iotums.service.UserService;

/**
 * @author Jingdong Wang
 */
@Controller
@RequestMapping("/user/")
public class UserController {


    @Autowired
    private UserService userService;
    
    @Autowired
    private UserFormValidator validator;

    /**
     * @return View page
     */
    @RequestMapping("overview")
    public String overview(User user, Model model) {
    	List<User> users = userService.loadUserOverview(user);
        model.addAttribute("users", users);
        return "user_overview";
    }


    @RequestMapping(value = "join", method = RequestMethod.GET)
    public String join(Model model) {
    	User user = new User();
        model.addAttribute("userForm", user);
        return "user_form";
    }
    
    @RequestMapping(value = "join", method = RequestMethod.POST)
    @ResponseBody
    public BaseRespEntity userRegister(@RequestBody User userForm) {
    	BaseRespEntity resp = new BaseRespEntity();
        try {
        	validator.validate(userForm);
        	userService.saveUser(userForm);
		} catch (Exception e) {
			resp.setRespCode(CommonEnum.RespCode.Failure.getCode());
        	resp.setRespMsg(e.getMessage());
        	System.out.println(resp);
		}
        return resp;
    }

    @RequestMapping(value = "tologin",method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,ModelMap model) throws IOException {
    	String errNum = request.getParameter("authentication_error");
		model.put("authentication_error", errNum);
		return "login";
	}
    
    
//    @RequestMapping(value = "login",method = RequestMethod.GET)
//    @ResponseBody
//	public BaseRespEntity doLogin(HttpServletRequest request,ModelMap model,Authentication authentication) throws IOException {
//    	BaseRespEntity resp = new BaseRespEntity();
//    	if(authentication == null) {
//    		resp.setRespMsg("认证失败");
//    		resp.setRespCode(CommonEnum.RespCode.Failure.getCode());
//    	} else {
//    		String errNum = request.getParameter("authentication_error");
//    		WdcyUserDetails d  = (WdcyUserDetails) authentication.getPrincipal();
//        	if(StringUtils.isNotBlank(errNum)) {
//        		if("1".equals(errNum)) {
//        			resp.setRespMsg("用户名或密码错误");
//        		} else if("2".equals(errNum)) {
//        			resp.setRespMsg("认证失败");
//        		}
//        		resp.setRespCode(CommonEnum.RespCode.Failure.getCode());
//        	}
//    	}
//    	
//		return resp;
//	}

}