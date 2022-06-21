package com.jinunn.send.handler;

import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.enums.ChannelType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author : JinDun
 * @date : 2022/6/21 18:08
 */
@Component
@Slf4j
public class EmailHandler extends Handler{

    public EmailHandler() {
        // 初始化渠道
        channelCode = ChannelType.EMAIL.getCode();
    }

    @Override
    public boolean handler(TaskInfo taskInfo) {
        return false;
    }
}
