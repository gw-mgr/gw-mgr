package com.gewei.fuwushang.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.OrderInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 订单表，包括贷款，理财和车 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-06
 */
public interface IOrderInfoService extends IService<OrderInfo> {
	void selectDataGrid(PageInfo pageInfo);
}
