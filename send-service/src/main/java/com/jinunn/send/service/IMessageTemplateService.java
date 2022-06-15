package com.jinunn.send.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jinunn.send.entity.MessageTemplate;

import java.util.List;

/**
 * <p>
 * 消息模板信息 服务类
 * </p>
 *
 * @author jinunn
 * @since 2022-06-15
 */
public interface IMessageTemplateService extends IService<MessageTemplate> {

    /**
     * 添加模板
     */
    void add();

    /**
     * 获取List
     *
     * @return list
     */
    List<MessageTemplate> getList();
}
