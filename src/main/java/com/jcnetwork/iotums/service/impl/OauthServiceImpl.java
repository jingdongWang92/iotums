package com.jcnetwork.iotums.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jcnetwork.iotums.entity.OauthClientDetails;
import com.jcnetwork.iotums.persistence.repository.OauthMapper;
import com.jcnetwork.iotums.service.OauthService;

/**
 * OAuth 业务处理服务对象, 事务拦截也加在这一层
 *
 * @author Jingdong Wang
 */
@Service("oauthService")
public class OauthServiceImpl implements OauthService {

    @Autowired
    private OauthMapper oauthMapper;

    @Override
    public OauthClientDetails loadOauthClientDetails(String clientId) {
        return oauthMapper.findOauthClientDetails(clientId);
    }

    @Override
    public List<OauthClientDetails> loadAllOauthClientDetails() {
        List<OauthClientDetails> clientDetailses = oauthMapper.findAllOauthClientDetails();
        return clientDetailses;
    }

    @Override
    public void archiveOauthClientDetails(String clientId) {
        oauthMapper.updateOauthClientDetailsArchive(clientId, true);
    }

    @Override
    public void registerClientDetails(OauthClientDetails formDetails) {
        OauthClientDetails clientDetails = formDetails.createDomain();
        oauthMapper.saveOauthClientDetails(clientDetails);
    }
}