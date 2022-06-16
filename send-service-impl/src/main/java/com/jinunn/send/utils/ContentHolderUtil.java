package com.jinunn.send.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.expression.MapAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.PropertyPlaceholderHelper;

import java.text.MessageFormat;
import java.util.Map;

/**
 * 内容占位符 替换 占位符格式{$var}
 * {
 * "content": "{$content}",
 * "url": "",
 * "title": ""
 * }
 *
 * @author : JinDun
 * @date : 2022/6/16 14:55
 */
public class ContentHolderUtil {

    /**
     * 占位符前缀
     */
    private static final String PLACE_HOLDER_PREFIX = "{$";

    /**
     * 占位符后缀
     */
    private static final String PLACE_HOLDER_ENDFIX = "}";

    private static final StandardEvaluationContext EVALUTION_CONTEXT;

    private static final PropertyPlaceholderHelper
            PROPERTY_PLACEHOLDER_HELPER = new PropertyPlaceholderHelper(PLACE_HOLDER_PREFIX, PLACE_HOLDER_ENDFIX);

    static {
        EVALUTION_CONTEXT = new StandardEvaluationContext();
        EVALUTION_CONTEXT.addPropertyAccessor(new MapAccessor());
    }

    /**
     * 替换
     *
     * @param template 要替换的内容
     * @param paramMap 前端传的参数
     * @return value
     */
    public static String replacePlaceHolder(final String template, final Map<String, String> paramMap) {
        return PROPERTY_PLACEHOLDER_HELPER.replacePlaceholders(template, placeholderName -> {
            String value = paramMap.get(placeholderName);
            if (StringUtils.isBlank(value)) {
                String errorStr = MessageFormat
                        .format("template:{} require param:{},but not exist! paramMap:{}",
                                placeholderName, paramMap.toString());
                throw new IllegalArgumentException(errorStr);
            }
            return value;
        });
    }
}
