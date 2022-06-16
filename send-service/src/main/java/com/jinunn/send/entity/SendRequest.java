package com.jinunn.send.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送接口的参数
 *
 * @author : JinDun
 * @date : 2022/6/16 10:21
 */
@Data
@Accessors(chain = true)
public class SendRequest {

    /**
     * 执行业务类型（比如：发送还是撤回？）
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 消息参数
     */
    private MessageParam messageParam;

}
