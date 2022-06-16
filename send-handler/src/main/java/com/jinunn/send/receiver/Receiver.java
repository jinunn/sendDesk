package com.jinunn.send.receiver;

import com.alibaba.fastjson.JSON;
import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.urils.GroupIdMappingUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import java.util.List;
import java.util.Optional;

/**
 * 从MQ消费消息
 *
 * @author : JinDun
 * @date : 2022/6/16 16:05
 */
@Slf4j
public class Receiver {

    @KafkaListener(topics = "#{'${send.topic-name}'}")
    public void consumer(ConsumerRecord<?, String> consumerRecord, @Header(KafkaHeaders.GROUP_ID) String topicGroupId) {
        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {
            List<TaskInfo> taskInfoList = JSON.parseArray(kafkaMessage.get(), TaskInfo.class);
            String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(taskInfoList.get(0));

            if (topicGroupId.equals(messageGroupId)) {
                for (TaskInfo taskInfo : taskInfoList) {
                    log.info("taskInfo ===> {}", taskInfo);
                }
            }
        }
    }
}
