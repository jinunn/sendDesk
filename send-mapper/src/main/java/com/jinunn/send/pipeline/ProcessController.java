package com.jinunn.send.pipeline;

import com.jinunn.send.enums.RespStatusEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import vo.BasicResultVO;

import java.util.List;
import java.util.Map;

/**
 * ProcessController 注入到IOC容器中，
 *
 * @author : JinDun
 * @date : 2022/6/16 13:40
 */
@Data
@Slf4j
public class ProcessController {

    /**
     * 模板映射
     */
    private Map<String, ProcessTemplate> templateMap = null;

    /**
     * 执行责任链，遍历
     */
    public ProcessContext process(ProcessContext context) {

        // 检查责任链
        if (!this.processCheck(context)) {
            return context;
        }

        // 根据业务code，获取任务执行器，遍历执行器，进行对应的校验
        List<BusinessProcess> processList = templateMap.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if (context.getNeedBreak()) {
                break;
            }
        }
        return context;
    }


    private Boolean processCheck(ProcessContext context) {
        // 上下文
        if (context == null) {
            log.error("count is null");
            context = new ProcessContext();
            context.setResponse(BasicResultVO.fail(RespStatusEnum.CONTEXT_IS_NULL));
            return false;
        }

        // 业务类型code（例如：发送还是撤回）是否为null
        if (StringUtils.isBlank(context.getCode())) {
            log.error("code is null");
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            return false;
        }

        // 根据业务code 获取执行责任链模板
        ProcessTemplate processTemplate = templateMap.get(context.getCode());
        if (processTemplate == null) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            return false;
        }

        // 判断责任链列表是否为空
        List<BusinessProcess> processList = processTemplate.getProcessList();
        if (CollectionUtils.isEmpty(processList)) {
            context.setResponse(BasicResultVO.fail(RespStatusEnum.BUSINESS_CODE_IS_NULL));
            return false;
        }

        return true;
    }
}
