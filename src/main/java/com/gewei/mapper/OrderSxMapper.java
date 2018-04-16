package com.gewei.mapper;

import com.gewei.model.OrderSx;
import com.gewei.model.vo.OrderSxVo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 人寿保险订单表 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-01
 */
public interface OrderSxMapper extends BaseMapper<OrderSx> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
	OrderSxVo selectOrderByOrderId(String id);
	boolean updateByOrderId(OrderSx orderSx);
}