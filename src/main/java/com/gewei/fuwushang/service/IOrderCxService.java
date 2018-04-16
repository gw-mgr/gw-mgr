package com.gewei.fuwushang.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.OrderCx;
import com.gewei.model.vo.OrderCxVo;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 财产保险订单表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-14
 */
public interface IOrderCxService extends IService<OrderCx> {
	void selectDataGrid(PageInfo pageInfo);
	OrderCxVo selectOrderByOrderId(String id);
}
