package com.gewei.mapper;

import com.gewei.model.OrderInfo;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

/**
 * <p>
  * 订单表，包括贷款，理财和车 Mapper 接口
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-06
 */
public interface FOrderInfoMapper extends BaseMapper<OrderInfo> {
	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);
}