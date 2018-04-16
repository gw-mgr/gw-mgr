package com.gewei.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.EditOrderInfo;

public interface TCustomerBasicinfoMapper extends BaseMapper<TCustomerBasicinfo> {
	TCustomerBasicinfo getCustomerInfoByUserId(String customerId);
	void updateCustomerStatus(TCustomerBasicinfo customerBasicinfo);
	void editCustomerInfo(EditOrderInfo editOrderInfo);
}