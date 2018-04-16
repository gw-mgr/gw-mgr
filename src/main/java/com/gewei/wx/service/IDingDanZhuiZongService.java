package com.gewei.wx.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.OrderInfo;
import com.gewei.model.vo.CreateBXOrderVo;
import com.gewei.model.vo.EditOrderInfo;

public interface IDingDanZhuiZongService extends IService<OrderInfo> {
	List<Map<String, String>> getOrderListByOrderTypeAndUserId(Map<String, Object> condition);

	String getOrderTypeByOrderId(String orderId);

	Map<String, Object> getCDOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getESCOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getCMOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getDKOrderDetailInfoByOrderId(String orderId);

	void editOrderDetailInfo(EditOrderInfo editOrderInfo);

	List<Map<String, String>> getOrderSummaryByUserId(String userId);

	void updateOrderStatusByOrderId(String orderId, String memberId, String orderFlag);

	OrderInfo getOrderInfoByOrderId(String orderId, String memberId);

	void createCommonBXOrder(CreateBXOrderVo createBXOrderVo);

	long getOrderCountByOrderTypeAndUserId(Map<String, Object> condition);
}
