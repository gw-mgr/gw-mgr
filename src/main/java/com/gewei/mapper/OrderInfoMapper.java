package com.gewei.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.gewei.model.OrderInfo;

/**
 * <p>
 * 订单表，包括贷款，理财和车 Mapper 接口
 * </p>
 *
 * @author tiger
 * @since 2018-02-22
 */
public interface OrderInfoMapper extends BaseMapper<OrderInfo> {
	long getOrderTotalNum();

	long getOrderTotalMoney();

	long getYesterdayOrderTotalNum(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

	long getYesterdayOrderTotalMoney(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

	Map<String, Long> getYesterdayOrderNumOfAllOrderType(@Param("startTime") Long startTime, @Param("endTime") Long endTime);

	// TODO: 订单管理
	// List<Map<String, Object>> orderListGroupByProvince(Page<Map<String,
	// Object>> page);
	// List<Map<String, String>> getOrderInsProvinceList();
	// Integer getYesterdayTotalNumByProvinceId(String provinceId);
	// Integer getYesterdayFinishedNumByProvinceId(String provinceId);
	// Float getdealMoneyByProvinceId(String provinceId);
	// Float getrecommendMoneyByProvinceId(String provinceId);
	// TODO: 订单管理
	List<Map<String, String>> getOrderTypeList();

	List<Map<String, Object>> selectPage(Pagination page, Map<String, Object> params);

	List<Map<String, Object>> selectOtherList(HashMap<String, Object> condition);

	int getOtherOrderCount(HashMap<String, Object> condition);
}