package com.jinunn.send.utils;

import cn.hutool.core.date.DateUtil;
import com.jinunn.send.constant.AustinConstant;

import java.util.Date;

/**
 * 生成 消息推送的URL 工具类
 *
 * @author : JinDun
 * @date : 2022/6/16 14:38
 */
public class TaskInfoUtils {

    /**
     * 生成 BusinessId  模板类型+模板ID+当天日期 (固定16位)
     *
     * @param templateId   模板id
     * @param templateType 模板类型
     */
    public static Long generateBusinessId(Long templateId, Integer templateType) {
        Integer today = Integer.valueOf(DateUtil.format(new Date(), AustinConstant.YYYYMMDD));
        int typeFlag = 1000000;
        return Long.valueOf(String.format("%d%s", templateType * typeFlag + templateId, today));
    }


    /**
     * 对url添加平台参数（用于追踪数据)
     */
    public static String generateUrl(String url, Long templateId, Integer templateType) {
        url = url.trim();
        Long businessId = generateBusinessId(templateId, templateType);
        if (url.indexOf('?') == -1) {
            return url + "?track_code_bid=" + businessId;
        } else {
            return url + "&track_code_bid=" + businessId;
        }
    }
}
