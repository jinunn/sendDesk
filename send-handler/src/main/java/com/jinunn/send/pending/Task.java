package com.jinunn.send.pending;

import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.handler.HandlerHolder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

/**
 * 执行器
 * 1、丢弃消息
 * 2、去重
 * 3、发送消息
 *
 * @author : JinDun
 * @date : 2022/6/21 17:53
 */
@Slf4j
@Data
@Accessors(chain = true)
public class Task implements Runnable {

    private TaskInfo taskInfo;

    @Autowired
    private HandlerHolder handlerHolder;

    @Override
    public void run() {
        // todo 1、丢弃消息

        // todo 2、去重

        // 3、发送消息
        if (!CollectionUtils.isEmpty(taskInfo.getReceiver())) {
            // 根据不同的渠道去发送消息
            handlerHolder.route(taskInfo.getSendChannel()).doHandler(taskInfo);
        }
    }
}
