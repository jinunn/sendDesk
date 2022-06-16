package com.jinunn.send.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 消息参数 single
 *
 * @author jinDnn
 */
@Data
@Accessors(chain = true)
public class MessageParam {

    /**
     * 接收者 多个用 , 逗号号分隔开
     */
    private String receiver;

    /**
     * 消息内容中的可变部分
     */
    private Map<String, String> variables;

    /**
     * 扩展参数
     */
    private Map<String, String> extra;
}
