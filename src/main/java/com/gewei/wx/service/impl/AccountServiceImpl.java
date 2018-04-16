package com.gewei.wx.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.mapper.AccountMapper;
import com.gewei.model.TAccount;
import com.gewei.wx.service.IAccountService;

@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, TAccount> implements IAccountService {
}
