package com.jinunn.send.service;

import com.jinunn.send.entity.SendRequest;
import com.jinunn.send.entity.SendResponse;

/**
 * @author jinunn
 */
public interface SendService {

    /**
     * 单文案发送接口
     *
     * @param sendRequest 发送接口的参数
     * @return 发送接口返回值
     */
    SendResponse send(SendRequest sendRequest);
}
