package com.gewei.mapper;

import com.gewei.model.OrderCx;
import com.gewei.model.vo.OrderCxVo;

import java.util.List;
import java.util.Map;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 财产保险订单表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-14
 */
public interface OrderCxMapper extends BaseMapper<OrderCx> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
	OrderCxVo selectOrderByOrderId(String id);
	boolean updateByOrderId(OrderCx orderCx);
}