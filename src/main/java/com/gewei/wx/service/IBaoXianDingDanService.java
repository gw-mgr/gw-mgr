package com.gewei.wx.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.EditOrderCx;
import com.gewei.model.OrderInfo;

public interface IBaoXianDingDanService extends IService<OrderInfo> {
//	Map<String, Object> getSXOrderDetailInfoByOrderId(Map<String, String> condition);
//
//	Map<String, Object> getCXOrderDetailInfoByOrderId(Map<String, String> condition);

	/**
	 * @Author: Tiger
	 * @Description: TODO 微信会员（业务员）保险订单统计
	 * @param @param useId
	 * @param @return
	 * @return: Map<String,Object>
	 * @date: 2018年4月2日 上午9:34:43
	 * @throws
	 */
	Map<String, Object> getBXOrderSummaryById(String userId);

	List<Map<String, String>> getRSOrderListOfSaleManByOrderType(Map<String, Object> condition);

	List<Map<String, String>> getCXOrderListOfSaleManByOrderType(Map<String, Object> condition);

	long getRSOrderCountOfSaleManByOrderType(Map<String, Object> condition);

	long getCXOrderCountOfSaleManByOrderType(Map<String, Object> condition);

	void editCXOrderCarInfoByOrderId(EditOrderCx editOrderCx);

	void updateOrderFlagByOrderId(Map<String, String> condition);
}
