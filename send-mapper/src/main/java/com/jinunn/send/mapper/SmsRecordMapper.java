package com.jinunn.send.mapper;

import com.jinunn.send.entity.SmsRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 短信记录信息 Mapper 接口
 * </p>
 *
 * @author jinunn
 * @since 2022-06-15
 */
@Mapper
public interface SmsRecordMapper extends BaseMapper<SmsRecord> {

}
