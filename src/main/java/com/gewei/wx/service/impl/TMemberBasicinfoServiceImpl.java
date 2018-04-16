package com.gewei.wx.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.mapper.TMemberBasicinfoMapper;
import com.gewei.model.TMemberBasicinfo;
import com.gewei.wx.service.ITMemberBasicinfoService;

@Service
public class TMemberBasicinfoServiceImpl extends ServiceImpl<TMemberBasicinfoMapper, TMemberBasicinfo> implements ITMemberBasicinfoService {
}
