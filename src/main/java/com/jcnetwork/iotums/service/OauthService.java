package com.jcnetwork.iotums.service;

import java.util.List;

import com.jcnetwork.iotums.entity.OauthClientDetails;

/**
 * @author Jingdong Wang
 */
public interface OauthService {

    OauthClientDetails loadOauthClientDetails(String clientId);

    List<OauthClientDetails> loadAllOauthClientDetails();

    void archiveOauthClientDetails(String clientId);

    void registerClientDetails(OauthClientDetails formDto);
}