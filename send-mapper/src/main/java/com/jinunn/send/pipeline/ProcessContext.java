package com.jinunn.send.pipeline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import vo.BasicResultVO;

/**
 * 责任链上下文
 *
 * @author jinunn
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext {

    /**
     * 标识责任链的code
     */
    private String code;

    /**
     * 用来统一存储责任链上下文数据的模型对象接口
     */
    private ProcessModel processModel;

    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

    /**
     * 流程处理结果
     */
    private BasicResultVO<Void> response;
}
