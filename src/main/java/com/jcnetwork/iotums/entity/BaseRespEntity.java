package com.jcnetwork.iotums.entity;

import com.jcnetwork.iotums.common.CommonEnum;

public class BaseRespEntity {

    /**
     * 响应状态编码
     */
    private Integer respCode = CommonEnum.RespCode.SUCCESS.getCode();
    /**
     * 结果描述
     */
    private String respMsg = CommonEnum.RespCode.SUCCESS.getName();

    public Integer getRespCode() {
        return respCode;
    }

    public void setRespCode(Integer respCode) {
        this.respCode = respCode;
    }

    public String getRespMsg() {
        return respMsg;
    }

    public void setRespMsg(String respMsg) {
        this.respMsg = respMsg;
    }
}
