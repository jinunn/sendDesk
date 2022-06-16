package com.jinunn.send.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @author jinunn
 */
@Getter
@ToString
@AllArgsConstructor
public enum RespStatusEnum {

    // 成功
    SUCCESS("00000", "操作成功"),

    // 失败
    FAIL("00001", "操作失败"),


    //客户端参数错误
    CLIENT_BAD_PARAMETERS("A0001", "客户端参数错误"),

    // 模板错误
    TEMPLATE_NOT_FOUND("A0002", "找不到模板或模板已被删除"),

    //服务执行异常
    SERVICE_ERROR("B0001", "服务执行异常"),

    //资源不存在
    RESOURCE_NOT_FOUND("B0404", "资源不存在"),


    /**
     * pipeline 状态码
     */
    CONTEXT_IS_NULL("P0001", "流程上下文为空"),
    BUSINESS_CODE_IS_NULL("P0002", "业务代码为空"),
    PROCESS_TEMPLATE_IS_NULL("P0003", "流程模板配置为空"),
    PROCESS_LIST_IS_NULL("P0004", "业务处理器配置为空"),

    ;

    /**
     * 响应状态
     */
    private final String code;
    /**
     * 响应编码
     */
    private final String msg;

}
