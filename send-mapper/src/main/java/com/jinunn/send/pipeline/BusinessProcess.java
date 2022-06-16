package com.jinunn.send.pipeline;

/**
 * @author jin
 */
public interface BusinessProcess {

    /**
     * 真正处理逻辑
     *
     * @param context 处理逻辑的上下文
     */
    void process(ProcessContext context);
}
