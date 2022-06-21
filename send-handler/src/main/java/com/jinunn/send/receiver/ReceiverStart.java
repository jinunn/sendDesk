package com.jinunn.send.receiver;

import com.jinunn.send.urils.GroupIdMappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 初始化消费者
 *
 * @author : JinDun
 * @date : 2022/6/16 17:32
 */
@Service
public class ReceiverStart {

    @Autowired
    private ApplicationContext context;

    /**
     * Receiver的消费方法常量 消费者类名.方法名
     */
    private static final String RECEIVER_METHOD_NAME = "Receiver.consumer";

    /**
     * 获取得到所有组装的groupId 渠道+类型格式
     */
    private static final List<String> ALL_GROUP_IDS = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 下标(用于迭代groupIds位置)
     */
    private static Integer index = 0;

    /**
     * 为每个渠道不同的消息类型 创建一个Receiver对象
     */
    @PostConstruct
    public void init() {
        for (int i = 0; i < ALL_GROUP_IDS.size(); i++) {
            context.getBean(Receiver.class);
        }
    }


    /**
     * 给每个Receiver对象的consumer方法 @KafkaListener赋值相应的groupId
     */
    @Bean
    public static KafkaListenerAnnotationBeanPostProcessor.AnnotationEnhancer groupIdEnhancer() {
        return (attrs, element) -> {
            if (element instanceof Method) {
                String name = ((Method) element).getDeclaringClass().getSimpleName() + "." + ((Method) element).getName();
                if (RECEIVER_METHOD_NAME.equals(name)) {
                    attrs.put("groupId", ALL_GROUP_IDS.get(index));
                    index++;
                }
            }
            return attrs;
        };
    }
}
