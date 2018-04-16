package com.gewei.fuwushang.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.fuwushang.service.IBeneficialService;
import com.gewei.mapper.BeneficialMapper;
import com.gewei.model.Beneficial;

/**
 * <p>
 * 人寿保险-受益人表 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-02
 */
@Service
public class BeneficialServiceImpl extends ServiceImpl<BeneficialMapper, Beneficial> implements IBeneficialService {
	@Autowired
	private BeneficialMapper mapper;
	@Override
	public Beneficial selectBeneficial(Beneficial beneficial) {
		return mapper.selectBeneficial(beneficial);
	}
	
}
