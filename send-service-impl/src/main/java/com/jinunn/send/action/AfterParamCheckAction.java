package com.jinunn.send.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.jinunn.send.entity.SendTaskModel;
import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.enums.ChannelType;
import com.jinunn.send.enums.RespStatusEnum;
import com.jinunn.send.enums.SendIdType;
import com.jinunn.send.pipeline.BusinessProcess;
import com.jinunn.send.pipeline.ProcessContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import vo.BasicResultVO;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 后置参数校验Action
 *
 * @author : JinDun
 * @date : 2022/6/16 14:06
 */
@Slf4j
public class AfterParamCheckAction implements BusinessProcess {

    public static final String PHONE_REGEX_EXP = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(166)|(17[0-9])|(18[0-9])|(19[1,8,9]))\\d{8}$";

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        // 获取在前置参数拼装的值
        List<TaskInfo> taskInfo = sendTaskModel.getTaskInfo();

        if (CollectionUtils.isEmpty(taskInfo)) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.CLIENT_BAD_PARAMETERS));
            return;
        }

        // 1. 过滤掉不合法的手机号
        this.filterIllegalPhoneNum(taskInfo);
    }

    private void filterIllegalPhoneNum(List<TaskInfo> taskInfo) {
        // id类型
        Integer idType = taskInfo.get(0).getIdType();
        // 渠道
        Integer sendChannel = taskInfo.get(0).getSendChannel();

        if (SendIdType.PHONE.getCode().equals(idType) && ChannelType.SMS.getCode().equals(sendChannel)) {
            Iterator<TaskInfo> iterator = taskInfo.iterator();
            // 利用正则找出不合法的手机号
            while (iterator.hasNext()) {
                TaskInfo task = iterator.next();

                Set<String> illegalPhone = task.getReceiver()
                        .stream()
                        .filter(phone -> !ReUtil.isMatch(PHONE_REGEX_EXP, phone))
                        .collect(Collectors.toSet());

                // 删除不合法的手机号
                if (CollUtil.isNotEmpty(illegalPhone)) {
                    task.getReceiver().removeAll(illegalPhone);
                    log.error("{} find illegal phone!{}", task.getMessageTemplateId(), JSON.toJSONString(illegalPhone));
                }

                // 删除接收者为null
                if (CollUtil.isEmpty(task.getReceiver())) {
                    iterator.remove();
                }
            }
        }
    }
}
