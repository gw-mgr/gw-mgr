package com.gewei.fuwushang.service.impl;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.fuwushang.service.IMessageService;
import com.gewei.mapper.MessageMapper;
import com.gewei.model.Message;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-07
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IMessageService {
	
}
