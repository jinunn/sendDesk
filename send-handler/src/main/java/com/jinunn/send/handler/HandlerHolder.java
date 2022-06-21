package com.jinunn.send.handler;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * channel渠道 -> Handler的映射关系
 *
 * @author : JinDun
 * @date : 2022/6/21 18:01
 */
@Component
public class HandlerHolder {

    private final Map<Integer, Handler> handlers = new HashMap<>(32);

    /**
     * key: 渠道，Value: Handle
     *
     * @param channelCode 渠道
     * @param handler     Handle
     */
    public void putHandler(Integer channelCode, Handler handler) {
        handlers.put(channelCode, handler);
    }

    /**
     * 获取Handler
     *
     * @param channelCode 渠道
     * @return Handle
     */
    public Handler route(Integer channelCode) {
        return handlers.get(channelCode);
    }

}
