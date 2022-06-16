package com.jinunn.send.enums;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 发送ID类型枚举
 *
 * @author 3y
 */
@Getter
@ToString
@AllArgsConstructor
public enum SendIdType {
    //  消息的发送ID类型：10. userId  20.did  30.手机号  40.openId  50.email
    USER_ID(10, "userId"),
    DID(20, "did"),
    PHONE(30, "phone"),
    OPEN_ID(40, "openId"),
    EMAIL(50, "email");

    private final Integer code;
    private final String description;
}
