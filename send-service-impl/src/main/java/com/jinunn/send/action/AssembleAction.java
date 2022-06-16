package com.jinunn.send.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Throwables;
import com.jinunn.send.constant.AustinConstant;
import com.jinunn.send.dto.ContentModel;
import com.jinunn.send.entity.MessageParam;
import com.jinunn.send.entity.MessageTemplate;
import com.jinunn.send.entity.SendTaskModel;
import com.jinunn.send.entity.TaskInfo;
import com.jinunn.send.enums.ChannelType;
import com.jinunn.send.enums.RespStatusEnum;
import com.jinunn.send.mapper.MessageTemplateMapper;
import com.jinunn.send.pipeline.BusinessProcess;
import com.jinunn.send.pipeline.ProcessContext;
import com.jinunn.send.utils.ContentHolderUtil;
import com.jinunn.send.utils.TaskInfoUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import vo.BasicResultVO;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 参数拼装
 *
 * @author : JinDun
 * @date : 2022/6/16 14:05
 */
@Slf4j
public class AssembleAction implements BusinessProcess {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @Override
    public void process(ProcessContext context) {
        SendTaskModel model = (SendTaskModel) context.getProcessModel();

        // 模板ID
        Long templateId = model.getMessageTemplateId();

        MessageTemplate messageTemplate = messageTemplateMapper.selectById(templateId);

        // 如果模板为空，或者无效状态
        if (messageTemplate == null || messageTemplate.getIsDeleted().equals(AustinConstant.TRUE)) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.TEMPLATE_NOT_FOUND));
            return;
        }

        try {
            List<TaskInfo> taskInfoList = this.taskInfoBuild(model, messageTemplate);
            model.setTaskInfo(taskInfoList);
        } catch (Exception e) {
            context.setNeedBreak(true)
                    .setResponse(BasicResultVO.fail(RespStatusEnum.SERVICE_ERROR));
            log.error("assemble task fail! templateId:{}, e:{}", templateId, Throwables.getStackTraceAsString(e));
        }
    }


    /**
     * 组装 TaskInfo 任务消息
     *
     * @param sendTaskModel   消息任务模型
     * @param messageTemplate 数据库的消息模板
     * @return taskInfoList
     */
    private List<TaskInfo> taskInfoBuild(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        List<TaskInfo> taskInfoList = new ArrayList<>();

        for (MessageParam messageParam : messageParamList) {
            TaskInfo taskInfo = new TaskInfo()
                    .setMessageTemplateId(messageTemplate.getId())
                    .setBusinessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(), messageTemplate.getTemplateType()))
                    .setReceiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(String.valueOf(StrUtil.C_COMMA)))))
                    .setIdType(messageTemplate.getIdType())
                    .setSendChannel(messageTemplate.getSendChannel())
                    .setTemplateType(messageTemplate.getTemplateType())
                    .setMsgType(messageTemplate.getMsgType())
                    .setSendAccount(messageTemplate.getSendAccount())
                    .setContentModel(this.getContentModelValue(messageTemplate, messageParam));
            taskInfoList.add(taskInfo);
        }
        return taskInfoList;
    }

    /**
     * 获取 contentModel，替换模板msgContent中占位符信息
     *
     * @param messageTemplate 模板类型
     * @param messageParam    消息参数
     * @return 发送内容的模型
     */
    private ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {
        // 通过发送渠道，得到真正的 ContentModel 类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class aClass = ChannelType.getChanelModelClassByCode(sendChannel);

        // 得到前端传入的参数 做内容替换
        Map<String, String> variables = messageParam.getVariables();
        // 得到模板msgContent
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());

        // 反射 组装 contentModel
        Field[] fields = ReflectUtil.getFields(aClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(aClass);
        for (Field field : fields) {
            // 通过数据库模板
            String originValue = jsonObject.getString(field.getName());
            log.info("field Name ==> {},value == >{}", field.getName(), originValue);
            if (StringUtils.isNotBlank(originValue)) {
                // 内容替换
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                // 设置值
                ReflectUtil.setFieldValue(contentModel, field, resultValue);
            }
        }

        // 获取是否存在url字段
        String url = (String) ReflectUtil.getFieldValue(contentModel, "url");
        if (StringUtils.isNotBlank(url)) {
            String resultUrl = TaskInfoUtils.generateUrl(url, messageTemplate.getId(), messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel, "url", resultUrl);
        }
        return contentModel;
    }
}
