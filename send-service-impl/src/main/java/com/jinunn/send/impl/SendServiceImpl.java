package com.jinunn.send.impl;

import com.jinunn.send.entity.SendRequest;
import com.jinunn.send.entity.SendResponse;
import com.jinunn.send.entity.SendTaskModel;
import com.jinunn.send.pipeline.ProcessContext;
import com.jinunn.send.pipeline.ProcessController;
import com.jinunn.send.service.SendService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.BasicResultVO;

import java.util.Collections;

/**
 * @author : JinDun
 * @date : 2022/6/16 10:24
 */
@Slf4j
@Service
public class SendServiceImpl implements SendService {

    @Autowired
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {
        // 包装请求任务模型
        SendTaskModel taskModel = new SendTaskModel()
                .setMessageTemplateId(sendRequest.getMessageTemplateId())
                .setMessageParamList(Collections.singletonList(sendRequest.getMessageParam()));

        // 封装成责任链参数
        ProcessContext processContext = new ProcessContext()
                .setCode(sendRequest.getCode())
                .setProcessModel(taskModel)
                .setNeedBreak(false)
                .setResponse(BasicResultVO.success());

        // 执行责任链
        ProcessContext context = processController.process(processContext);
        return new SendResponse(context.getResponse().getCode(), context.getResponse().getMsg());
    }
}
