package com.gewei.fuwushang.service.impl;

import com.gewei.model.Policyholder;
import com.gewei.mapper.PolicyholderMapper;
import com.gewei.fuwushang.service.IPolicyholderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 投保人表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-01-26
 */
@Service
public class PolicyholderServiceImpl extends ServiceImpl<PolicyholderMapper, Policyholder> implements IPolicyholderService {
	@Autowired
	private PolicyholderMapper mapper;

	@Override
	public Policyholder selectPolicyholder(Policyholder policyholder) {
		return mapper.selectPolicyholder(policyholder);
	}

	@Override
	@Transactional
	public void updatePolicyHolderStatus(Policyholder policyholder) {
		mapper.updatePolicyHolderStatus(policyholder);
	}

}
