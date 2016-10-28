package com.jcnetwork.iotums.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jcnetwork.iotums.entity.OauthClientDetails;
import com.jcnetwork.iotums.service.OauthService;
import com.jcnetwork.iotums.web.oauth.OauthClientDetailsValidator;

/**
 * Handle 'client_details' management
 *
 * @author Jingdong Wang
 */
@Controller
public class ClientDetailsController {


    @Autowired
    private OauthService oauthService;

    @Autowired
    private OauthClientDetailsValidator clientDetailsValidator;


    @RequestMapping("client_details")
    public String clientDetails(Model model) {
        List<OauthClientDetails> clientDetailsList = oauthService.loadAllOauthClientDetails();
        model.addAttribute("clientDetailsList", clientDetailsList);
        return "clientdetails/client_details";
    }


    /*
    * Logic delete
    * */
    @RequestMapping("archive_client/{clientId}")
    public String archiveClient(@PathVariable("clientId") String clientId) {
        oauthService.archiveOauthClientDetails(clientId);
        return "redirect:../client_details";
    }

    /*
    * Test client
    * */
    @RequestMapping("test_client/{clientId}")
    public String testClient(@PathVariable("clientId") String clientId, Model model) {
    	OauthClientDetails clientDetails = oauthService.loadOauthClientDetails(clientId);
        model.addAttribute("clientDetailsDto", clientDetails);
        return "clientdetails/test_client";
    }


    /*
    * Register client
    * */
    @RequestMapping(value = "register_client", method = RequestMethod.GET)
    public String registerClient(Model model) {
        model.addAttribute("formDto", new OauthClientDetails());
        return "clientdetails/register_client";
    }


   /**
    * 客户端注册
    *  
    * @param formClient
    * @param result
    * @return
    */
    @RequestMapping(value = "register_client", method = RequestMethod.POST)
    public String submitRegisterClient(@ModelAttribute("formDto") OauthClientDetails formClient, BindingResult result) {
        clientDetailsValidator.validate(formClient, result);
        if (result.hasErrors()) {
            return "clientdetails/register_client";
        }
        oauthService.registerClientDetails(formClient);
        return "redirect:client_details";
    }


}