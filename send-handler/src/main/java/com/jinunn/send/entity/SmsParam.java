package com.jinunn.send.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * @author : JinDun
 * @date : 2022/6/16 10:15
 */
@Data
@Accessors(chain = true)
public class SmsParam {

    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;

}
