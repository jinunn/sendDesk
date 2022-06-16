package com.jinunn.send.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.base.Throwables;
import com.jinunn.send.entity.SendTaskModel;
import com.jinunn.send.enums.RespStatusEnum;
import com.jinunn.send.pipeline.BusinessProcess;
import com.jinunn.send.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import vo.BasicResultVO;

/**
 * 将消息发送到MQ
 *
 * @author : JinDun
 * @date : 2022/6/16 14:07
 */
@Slf4j
public class SendMqAction implements BusinessProcess {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    @Value("${send.topic-name}")
    private String topicName;

    @Override
    public void process(ProcessContext context) {

        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();

        try {
            kafkaTemplate
                    .send(topicName, JSON.toJSONString(sendTaskModel.getTaskInfo(), SerializerFeature.WriteClassName));
        } catch (Exception e) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("send kafka fail! e:{},params:{}",
                    Throwables.getStackTraceAsString(e),
                    JSON.toJSONString(sendTaskModel.getTaskInfo().get(0)));
        }
    }
}
