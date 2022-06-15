package com.jinunn.send.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jinunn.send.entity.SmsRecord;
import com.jinunn.send.mapper.SmsRecordMapper;
import com.jinunn.send.service.ISmsRecordService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 短信记录信息 服务实现类
 * </p>
 *
 * @author jinunn
 * @since 2022-06-15
 */
@Service
public class SmsRecordServiceImpl extends ServiceImpl<SmsRecordMapper, SmsRecord> implements ISmsRecordService {

}
