package com.jinunn.send.handler;

import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.enums.ChannelType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : JinDun
 * @date : 2022/6/21 18:06
 */
@Component
@Slf4j
public class SmsHandler extends Handler{

    public SmsHandler() {
        // 初始化渠道
        super.channelCode = ChannelType.SMS.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        return false;
    }
}
