package com.gewei.zhongshu.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.mapper.MessageMapper;
import com.gewei.model.Message;
import com.gewei.zhongshu.service.IZMessageService;

/**
 * <p>
 * 消息表 服务实现类
 * </p>
 *
 * @author tiger
 * @since 2018-04-07
 */
@Service
public class ZMessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements IZMessageService {
	
}
