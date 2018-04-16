package com.gewei.fuwushang.service.impl;

import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.EditOrderInfo;
import com.gewei.mapper.TCustomerBasicinfoMapper;
import com.gewei.fuwushang.service.ITCustomerBasicinfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员基本信息表，以会员编号%8取莫横向分表。 服务实现类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-07
 */
@Service
public class TCustomerBasicinfoServiceImpl extends ServiceImpl<TCustomerBasicinfoMapper, TCustomerBasicinfo> implements ITCustomerBasicinfoService {
	@Autowired
	private TCustomerBasicinfoMapper tCustomerBasicinfoMapper;

	@Override
	public TCustomerBasicinfo getCustomerInfoByUserId(String customerId) {
		return tCustomerBasicinfoMapper.getCustomerInfoByUserId(customerId);
	}

	@Override
	public void editCustomerInfo(EditOrderInfo editOrderInfo) {
		tCustomerBasicinfoMapper.editCustomerInfo(editOrderInfo);
	}
	@Override
	public void updateCustomerStatus(TCustomerBasicinfo customerBasicinfo){
		tCustomerBasicinfoMapper.updateCustomerStatus(customerBasicinfo);
	}
}
