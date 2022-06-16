package com.jinunn.send.dto;

import lombok.*;

/**
 * 短信内容模型，在前端填写的时候分开，但最后处理的时候会将url拼接在content上
 * @author jin
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SmsContentModel extends ContentModel {

    /**
     * 短信发送内容
     */
    private String content;

    /**
     * 短信发送链接
     */
    private String url;

}
