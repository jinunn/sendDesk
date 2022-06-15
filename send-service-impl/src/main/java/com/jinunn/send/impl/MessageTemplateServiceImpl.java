package com.jinunn.send.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinunn.send.entity.MessageTemplate;
import com.jinunn.send.mapper.MessageTemplateMapper;
import com.jinunn.send.service.IMessageTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 消息模板信息 服务实现类
 * </p>
 *
 * @author jinunn
 * @since 2022-06-15
 */
@Service
public class MessageTemplateServiceImpl extends ServiceImpl<MessageTemplateMapper, MessageTemplate> implements IMessageTemplateService {

    @Override
    public void add() {
        MessageTemplate messageTemplate = MessageTemplate.builder()
                .name("test邮件")
                .auditStatus(10)
                .flowId("yyyy")
                .msgStatus(10)
                .idType(50)
                .sendChannel(40)
                .templateType(20)
                .msgType(10)
                .expectPushTime("0")
                .msgContent("{\"content\":\"{$contentValue}\",\"title\":\"{$title}\"}")
                .sendAccount(66)
                .creator("yyyyc")
                .updator("yyyyu")
                .team("yyyt")
                .proposer("yyyy22")
                .auditor("yyyyyyz")
                .isDeleted(0)
                .build();
        this.save(messageTemplate);
    }

    @Override
    public List<MessageTemplate> getList() {
        return baseMapper.selectList(null);
    }
}
