package com.jinunn.send.urils;

import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.enums.ChannelType;
import com.jinunn.send.enums.MessageType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * groupId 标识着每一个消费者组
 * @author : JinDun
 * @date : 2021/12/17 22:47
 */
public class GroupIdMappingUtils {

    /**
     * 组装所有的groupIds，不同的渠道不同的消息类型拥有自己的groupId（例如：短信渠道拥有营销、通知、认证）
     *  组装格式: sms.notice 通知groupId
     *           sms.marketing 营销groupId
     */
    public static List<String> getAllGroupIds() {
        List<String> groupIds = new ArrayList<>();
        // 渠道
        for (ChannelType channelType : ChannelType.values()) {
            // 消息类型
            for (MessageType messageType : MessageType.values()) {
                groupIds.add(channelType.getCodeEn() + "." + messageType.getCodeEn());
            }
        }
        return groupIds;
    }

    /**
     * 根据TaskInfo获取当前消息的groupId
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo){
        String channelCodeEn = Objects.requireNonNull(ChannelType.getEnumByCode(taskInfo.getSendChannel())).getCodeEn();
        String msgCodeEn = Objects.requireNonNull(MessageType.getEnumByCode(taskInfo.getMsgType())).getCodeEn();
        return channelCodeEn + "." + msgCodeEn;
    }
}
