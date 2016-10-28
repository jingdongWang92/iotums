package com.jcnetwork.iotums.persistence.repository;

import java.util.List;

import com.jcnetwork.iotums.entity.OauthClientDetails;

/**
 * 处理 OAuth 相关业务的 Repository
 *
 * @author Jingdong Wang
 */
public interface OauthMapper {

	/**
	 * 根据id获取客户端信息
	 * 
	 * @param clientId
	 * @return
	 */
    OauthClientDetails findOauthClientDetails(String clientId);

    /**
     * 获取所有客户端信息
     * 
     * @return
     */
    List<OauthClientDetails> findAllOauthClientDetails();

    /**
     * 修改客户端archiv状态
     * 
     * @param clientId 客户端id
     * @param archive
     */
    void updateOauthClientDetailsArchive(String clientId, boolean archive);

    void saveOauthClientDetails(OauthClientDetails clientDetails);
}


	
	