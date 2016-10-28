package com.jcnetwork.iotums.web.oauth;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jcnetwork.iotums.entity.OauthClientDetails;
import com.jcnetwork.iotums.service.OauthService;

/**
 * @author Jingdong Wang
 */
@Component
public class OauthClientDetailsValidator implements Validator {


    @Autowired
    private OauthService oauthService;

    @Override
    public boolean supports(Class<?> clazz) {
        return OauthClientDetails.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
    	OauthClientDetails clientDetails = (OauthClientDetails) target;

        validateClientId(clientDetails, errors);
        validateClientSecret(clientDetails, errors);

        validateGrantTypes(clientDetails, errors);
    }

    private void validateGrantTypes(OauthClientDetails clientDetails, Errors errors) {
        final String grantTypes = clientDetails.getAuthorizedGrantTypes();
        if (StringUtils.isEmpty(grantTypes)) {
            errors.rejectValue("authorizedGrantTypes", null, "grant_type(s) is required");
            return;
        }

        if ("refresh_token".equalsIgnoreCase(grantTypes)) {
            errors.rejectValue("authorizedGrantTypes", null, "grant_type(s) 不能只是[refresh_token]");
        }
    }

    private void validateClientSecret(OauthClientDetails clientDetails, Errors errors) {
        final String clientSecret = clientDetails.getClientSecret();
        if (StringUtils.isEmpty(clientSecret)) {
            errors.rejectValue("clientSecret", null, "client_secret is required");
            return;
        }

        if (clientSecret.length() < 8) {
            errors.rejectValue("clientSecret", null, "client_secret 长度至少8位");
        }
    }

    private void validateClientId(OauthClientDetails clientDetails, Errors errors) {
        final String clientId = clientDetails.getClientId();
        if (StringUtils.isEmpty(clientId)) {
            errors.rejectValue("clientId", null, "client_id is required");
            return;
        }

        if (clientId.length() < 5) {
            errors.rejectValue("clientId", null, "client_id 长度至少5位");
            return;
        }

        OauthClientDetails clientDetails1 = oauthService.loadOauthClientDetails(clientId);
        if (clientDetails1 != null) {
            errors.rejectValue("clientId", null, "client_id [" + clientId + "] 已存在");
        }

    }
}