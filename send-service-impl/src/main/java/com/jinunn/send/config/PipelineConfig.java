package com.jinunn.send.config;

import com.jinunn.send.action.AfterParamCheckAction;
import com.jinunn.send.action.AssembleAction;
import com.jinunn.send.action.PreParamCheckAction;
import com.jinunn.send.action.SendMqAction;
import com.jinunn.send.enums.BusinessCode;
import com.jinunn.send.pipeline.BusinessProcess;
import com.jinunn.send.pipeline.ProcessController;
import com.jinunn.send.pipeline.ProcessTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author : JinDun
 * @date : 2022/6/16 13:55
 */
@Configuration
public class PipelineConfig {

    @Bean
    public ProcessController processController() {
        ProcessController processController = new ProcessController();
        // key: code（业务标识） value: 业务执行模板
        HashMap<String, ProcessTemplate> map = new HashMap<>(4);
        map.put(BusinessCode.COMMON_SEND.getCode(), this.commonSendTemplate());
        processController.setTemplateMap(map);
        return processController;
    }

    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        // 把各个具体需要实现的业务串联起
        ProcessTemplate processTemplate = new ProcessTemplate();

        // 总的业务抽象接口，子类实现父类接口
        ArrayList<BusinessProcess> list = new ArrayList<>();
        list.add(this.preParamCheckAction());
        list.add(this.assembleAction());
        list.add(this.afterParamCheckAction());
        list.add(this.sendMqAction());

        // 业务执行器的 list
        processTemplate.setProcessList(list);
        return processTemplate;
    }

    /**
     * 前置参数校验Action
     */
    @Bean
    public PreParamCheckAction preParamCheckAction() {
        return new PreParamCheckAction();
    }

    /**
     * 组装参数Action
     */
    @Bean
    public AssembleAction assembleAction() {
        return new AssembleAction();
    }

    /**
     * 后置参数校验Action
     */
    @Bean
    public AfterParamCheckAction afterParamCheckAction() {
        return new AfterParamCheckAction();
    }

    /**
     * 发送消息至MQ的Action
     */
    @Bean
    public SendMqAction sendMqAction() {
        return new SendMqAction();
    }
}
