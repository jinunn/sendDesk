package com.jinunn.send.action;

import com.jinunn.send.entity.MessageParam;
import com.jinunn.send.entity.SendTaskModel;
import com.jinunn.send.enums.RespStatusEnum;
import com.jinunn.send.pipeline.BusinessProcess;
import com.jinunn.send.pipeline.ProcessContext;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import vo.BasicResultVO;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 前置参数校验
 * 判断模板ID是否有传入，消息参数是否有传入（对参数的常规检查，如果有问题，直接break掉链路，返回告诉调用方有问题）
 *
 * @author : JinDun
 * @date : 2022/6/16 14:03
 */
public class PreParamCheckAction implements BusinessProcess {

    @Override
    public void process(ProcessContext context) {
        // 模型对象接口 SendServiceImpl.send接口中对task包装的
        SendTaskModel model = (SendTaskModel) context.getProcessModel();

        // 模板ID
        Long templateId = model.getMessageTemplateId();

        // 获取请求参数
        List<MessageParam> messageParamList = model.getMessageParamList();

        // 判断模板是否为空，请求参数是否为空
        if (templateId == null || CollectionUtils.isEmpty(messageParamList)) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 过滤 接收者为null的请求参数
        List<MessageParam> paramList = messageParamList
                .stream()
                .filter(messageParam -> StringUtils.isNotBlank(messageParam.getReceiver()))
                .collect(Collectors.toList());

        if (CollectionUtils.isEmpty(paramList)) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        model.setMessageParamList(paramList);
    }
}
