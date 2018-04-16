package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.EditOrderCx;
import com.gewei.model.OrderInfo;

public interface BaoXianDingDanMapper extends BaseMapper<OrderInfo> {
	Map<String, Object> getBXOrderSummaryById(String userId);

	List<Map<String, String>> getRSOrderListOfSaleManByOrderType(Map<String, Object> condition);

	List<Map<String, String>> getCXOrderListOfSaleManByOrderType(Map<String, Object> condition);

	long getRSOrderCountOfSaleManByOrderType(Map<String, Object> condition);

	long getCXOrderCountOfSaleManByOrderType(Map<String, Object> condition);

	void editCXOrderCarInfoByOrderId(EditOrderCx editOrderCx);

	@Update("update ${tableName} set ORDER_FLAG = #{ORDER_FLAG} where ORDER_ID = #{orderId}")
	void updateOrderFlagByOrderId(Map<String, String> condition);
}