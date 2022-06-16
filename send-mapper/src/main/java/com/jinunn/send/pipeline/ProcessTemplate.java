package com.jinunn.send.pipeline;

import java.util.List;

/**
 * 业务执行模板（把责任链的逻辑串起来）
 *
 * @author : JinDun
 * @date : 2022/6/16 13:42
 */
public class ProcessTemplate {

    /**
     * 业务执行器的 list （汇总所有的业务）
     */
    private List<BusinessProcess> processList;


    public List<BusinessProcess> getProcessList() {
        return processList;
    }

    public void setProcessList(List<BusinessProcess> processList) {
        this.processList = processList;
    }


}
