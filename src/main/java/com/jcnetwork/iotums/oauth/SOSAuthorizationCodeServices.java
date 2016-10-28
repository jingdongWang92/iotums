package com.jcnetwork.iotums.oauth;

import static com.jcnetwork.iotums.common.constant.CacheConstants.AUTHORIZATION_CODE_CACHE;

import javax.sql.DataSource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;

/**
 * 2016/9/18
 *
 * @author Jingdong Wang
 */
public class SOSAuthorizationCodeServices extends JdbcAuthorizationCodeServices {


    public SOSAuthorizationCodeServices(DataSource dataSource) {
        super(dataSource);
    }


    @Override
    @Cacheable(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    protected void store(String code, OAuth2Authentication authentication) {
        super.store(code, authentication);
    }

    @Override
    @CacheEvict(value = AUTHORIZATION_CODE_CACHE, key = "#code")
    public OAuth2Authentication remove(String code) {
        return super.remove(code);
    }
}
