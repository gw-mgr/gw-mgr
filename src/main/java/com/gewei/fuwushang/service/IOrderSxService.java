package com.gewei.fuwushang.service;

import com.gewei.commons.result.PageInfo;
import com.gewei.model.OrderSx;
import com.gewei.model.vo.OrderSxVo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 人寿保险订单表 服务类
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-01
 */
public interface IOrderSxService extends IService<OrderSx> {
	void selectDataGrid(PageInfo pageInfo);

	OrderSxVo selectOrderByOrderId(String id);

	List<Map<String, Object>> selectSXList(HashMap<String, Object> condition);

	int getSXOrderCount(HashMap<String, Object> condition);
}
