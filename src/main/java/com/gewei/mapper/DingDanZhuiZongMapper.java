package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.OrderInfo;
import com.gewei.model.vo.EditOrderInfo;

public interface DingDanZhuiZongMapper extends BaseMapper<OrderInfo> {
	List<Map<String, String>> getOrderListByOrderTypeAndUserId(Map<String, Object> condition);

	String getOrderTypeByOrderId(String orderId);

	Map<String, Object> getCDOrderDetailInfoByOrderId(String orderID);

	Map<String, Object> getESCOrderDetailInfoByOrderId(String orderID);

	Map<String, Object> getCMOrderDetailInfoByOrderId(String orderID);

	Map<String, Object> getDKOrderDetailInfoByOrderId(String orderId);

	void editCustomerInfo(EditOrderInfo editOrderInfo);

	void editOrderDetailInfo(EditOrderInfo editOrderInfo);

	List<Map<String, String>> getOrderSummaryByUserId(String userId);

	@Update("update order_info set ORDER_FLAG = #{orderFlag} where ORDER_ID = #{orderId} and SALES_MAN = #{memberId}")
	void updateOrderStatusByOrderId(@Param("orderId") String orderId, @Param("memberId") String memberId, @Param("orderFlag") String orderFlag);

	@Select("select ORDER_ID orderId,USER_ID userId from order_info where ORDER_ID = #{orderId} and SALES_MAN = #{memberId}")
	@ResultType(OrderInfo.class)
	OrderInfo getOrderInfoByOrderId(@Param("orderId") String orderId, @Param("memberId") String memberId);

	@Update("update order_cx set ORDER_NO = #{orderNo} where ORDER_ID = #{orderId}")
	void editCCMerchantOrderInfo(@Param("orderId") String orderId, @Param("orderNo") String orderNo);

	@Update("update order_sx set ORDER_NO = #{orderNo} where ORDER_ID = #{orderId}")
	void editRSMerchantOrderInfo(@Param("orderId") String orderId, @Param("orderNo") String orderNo);

	long getOrderCountByOrderTypeAndUserId(Map<String, Object> condition);
}
