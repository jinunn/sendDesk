package com.jinunn.send.pending;

import com.jinunn.send.config.ThreadPoolConfig;
import com.jinunn.send.urils.GroupIdMappingUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 存储 每种消息类型(通知、营销、认证） 与 TaskPending 的关系
 *
 * @author : JinDun
 * @date : 2022/6/21 17:47
 */
@Component
public class TaskPendingHolder {

    private final Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    private static final List<String> ALL_GROUP_IDS = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 给每个groupId初始化一个线程池
     */
    @PostConstruct
    public void init() {
        for (String groupId : ALL_GROUP_IDS) {
            Integer coreSize = 3;
            Integer maxSize = 3;
            Integer queueSize = 100;
            taskPendingHolder.put(groupId, ThreadPoolConfig.getThreadPool(coreSize, maxSize, queueSize));
        }
    }


    /**
     * 获取线程池
     *
     * @param groupId groupID
     * @return 线程池
     */
    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }
}
