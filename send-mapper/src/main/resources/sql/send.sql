CREATE TABLE `sms_record`
(
    `id`                  bigint                                                        NOT NULL AUTO_INCREMENT,
    `message_template_id` bigint                                                        NOT NULL DEFAULT '0' COMMENT '消息模板ID',
    `phone`               bigint                                                        NOT NULL DEFAULT '0' COMMENT '手机号',
    `supplier_id`         tinyint                                                       NOT NULL DEFAULT '0' COMMENT '发送短信渠道商的ID',
    `supplier_name`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '发送短信渠道商的名称',
    `msg_content`         varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '短信发送的内容',
    `series_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '下发批次的ID',
    `charging_num`        tinyint                                                       NOT NULL DEFAULT '0' COMMENT '计费条数',
    `report_content`      varchar(50) COLLATE utf8mb4_unicode_ci                        NOT NULL DEFAULT '' COMMENT '回执内容',
    `status`              tinyint                                                       NOT NULL DEFAULT '0' COMMENT '短信状态： 10.发送 20.成功 30.失败',
    `send_date`           int                                                           NOT NULL DEFAULT '0' COMMENT '发送日期：20211112',
    `create_time`         datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time`         datetime                                                      NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                   `idx_send_date` (`send_date`)
) COMMENT='短信记录信息';


CREATE TABLE `sms_record`
(
    `id`                  bigint                                                        NOT NULL AUTO_INCREMENT,
    `message_template_id` bigint                                                        NOT NULL DEFAULT '0' COMMENT '消息模板ID',
    `phone`               bigint                                                        NOT NULL DEFAULT '0' COMMENT '手机号',
    `supplier_id`         tinyint                                                       NOT NULL DEFAULT '0' COMMENT '发送短信渠道商的ID',
    `supplier_name`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci  NOT NULL DEFAULT '' COMMENT '发送短信渠道商的名称',
    `msg_content`         varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '短信发送的内容',
    `series_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '' COMMENT '下发批次的ID',
    `charging_num`        tinyint                                                       NOT NULL DEFAULT '0' COMMENT '计费条数',
    `report_content`      varchar(50) COLLATE utf8mb4_unicode_ci                        NOT NULL DEFAULT '' COMMENT '回执内容',
    `status`              tinyint                                                       NOT NULL DEFAULT '0' COMMENT '短信状态： 10.发送 20.成功 30.失败',
    `send_date`           int                                                           NOT NULL DEFAULT '0' COMMENT '发送日期：20211112',
    `create_time`         datetime                                                      NOT NULL COMMENT '创建时间',
    `update_time`         datetime                                                      NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`id`),
    KEY                   `idx_send_date` (`send_date`)
)COMMENT='短信记录信息';