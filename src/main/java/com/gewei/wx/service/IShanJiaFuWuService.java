package com.gewei.wx.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.OrderInfo;
import com.gewei.model.TAccount;
import com.gewei.model.vo.OrderInfoVo;

public interface IShanJiaFuWuService extends IService<OrderInfo> {
	Map<String, Object> getMerchantOrderSummaryById(String merchantId);

	List<Map<String, String>> getMerchantOrderList(OrderInfoVo orderInfoVo, long start, long rows);

	Map<String, Object> getCCOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getRSOrderDetailInfoByOrderId(String orderId);

	/**
	 * @Author: Tiger
	 * @Description: TODO 商家结算概述：累计收入+累计未结算金额
	 * @param @param merchantId
	 * @param @param createTime
	 * @param @return
	 * @return: Map<String,Object>
	 * @date: 2018年3月26日 下午1:57:55
	 * @throws
	 */
	Map<String, Object> getMerchantSettleSummary(String merchantId, String createTime);

	/**
	 * @Author: Tiger
	 * @Description: TODO 按类型-收入情况
	 * @param @param merchantId
	 * @param @param createTime
	 * @param @return
	 * @return: Map<String,Object>
	 * @date: 2018年3月26日 下午2:31:19
	 * @throws
	 */
	Map<String, Object> getMerchantSettleOrderByOrderType(String merchantId, String createTime);

	List<Map<String, Object>> getMerchantSettleFlow(String merchantId, long page, long rows);

	TAccount getAccountByPersonId(String merchantId);

	long getMerchantOrderCount(OrderInfoVo orderInfoVo);

	long getMerchantSettleFlowCount(String merchantId);

	/**
	 * @Author: Tiger
	 * @Description: TODO 订单状态修改(修改保单号)
	 * @param @param orderId
	 * @param @param orderNo
	 * @return: void
	 * @date: 2018年3月29日 下午7:47:57
	 * @throws
	 */
	void editCCMerchantOrderInfo(String orderId, String orderNo);

	void editRSMerchantOrderInfo(String orderId, String orderNo);
}
