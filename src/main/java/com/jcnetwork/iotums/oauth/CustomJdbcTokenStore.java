package com.jcnetwork.iotums.oauth;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2RefreshToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

import static com.jcnetwork.iotums.common.constant.CacheConstants.ACCESS_TOKEN_CACHE;
import static com.jcnetwork.iotums.common.constant.CacheConstants.REFRESH_TOKEN_CACHE;

/**
 * 2016/9/18
 * <p/>
 * 扩展默认的 jdbcTokenStore, 增加对缓存的支持
 *
 * @author Jingdong Wang
 */
public class CustomJdbcTokenStore extends JdbcTokenStore {


    public CustomJdbcTokenStore(DataSource dataSource) {
        super(dataSource);
    }


    @Cacheable(value = ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public OAuth2AccessToken readAccessToken(String tokenValue) {
        return super.readAccessToken(tokenValue);
    }


    @CacheEvict(value = ACCESS_TOKEN_CACHE, key = "#tokenValue")
    public void removeAccessToken(String tokenValue) {
        super.removeAccessToken(tokenValue);
    }


    @Cacheable(value = REFRESH_TOKEN_CACHE, key = "#token")
    public OAuth2RefreshToken readRefreshToken(String token) {
        return super.readRefreshToken(token);
    }

    @CacheEvict(value = REFRESH_TOKEN_CACHE, key = "#token")
    public void removeRefreshToken(String token) {
        super.removeRefreshToken(token);
    }


}
