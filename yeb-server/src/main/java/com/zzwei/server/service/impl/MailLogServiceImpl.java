package com.zzwei.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zzwei.server.mapper.MailLogMapper;
import com.zzwei.server.pojo.MailLog;
import com.zzwei.server.service.IMailLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzwei
 * @since 2022-08-19
 */
@Service
public class MailLogServiceImpl extends ServiceImpl<MailLogMapper, MailLog> implements IMailLogService {

}
