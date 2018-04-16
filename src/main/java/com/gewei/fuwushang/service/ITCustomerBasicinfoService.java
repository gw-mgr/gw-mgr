package com.gewei.fuwushang.service;

import com.gewei.model.TCustomerBasicinfo;
import com.gewei.model.vo.EditOrderInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 会员基本信息表，以会员编号%8取莫横向分表。 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-07
 */
public interface ITCustomerBasicinfoService extends IService<TCustomerBasicinfo> {
	TCustomerBasicinfo getCustomerInfoByUserId(String customerId);

	void editCustomerInfo(EditOrderInfo editOrderInfo);

	void updateCustomerStatus(TCustomerBasicinfo customerBasicinfo);
}
