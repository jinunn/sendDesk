package com.jinunn.send.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 短信记录信息
 * </p>
 *
 * @author jinunn
 * @since 2022-06-15
 */
@Getter
@Setter
@TableName("sms_record")
public class SmsRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 消息模板ID
     */
    private Long messageTemplateId;

    /**
     * 手机号
     */
    private Long phone;

    /**
     * 发送短信渠道商的ID
     */
    private Integer supplierId;

    /**
     * 发送短信渠道商的名称
     */
    private String supplierName;

    /**
     * 短信发送的内容
     */
    private String msgContent;

    /**
     * 下发批次的ID
     */
    private String seriesId;

    /**
     * 计费条数
     */
    private Integer chargingNum;

    /**
     * 回执内容
     */
    private String reportContent;

    /**
     * 短信状态： 10.发送 20.成功 30.失败
     */
    private Integer status;

    /**
     * 发送日期：20211112
     */
    private Integer sendDate;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;


}
