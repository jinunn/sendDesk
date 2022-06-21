package com.jinunn.send.handler;

import com.jinunn.send.entity.TaskInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

/**
 * 发送各个渠道的handler
 *
 * @author jin
 */
@Slf4j
public abstract class Handler {

    @Autowired
    private HandlerHolder handlerHolder;

    /**
     * 标识渠道的Code , 子类初始化的时候指定
     */
    protected Integer channelCode;


    /**
     * 统一处理的handler接口
     *
     * @param taskInfo 消息
     * @return t or f
     */
    public abstract boolean handler(TaskInfo taskInfo);

    public void doHandler(TaskInfo taskInfo){
        if (this.handler(taskInfo)){
            log.info("task is null");
        }
    }

    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }
}
