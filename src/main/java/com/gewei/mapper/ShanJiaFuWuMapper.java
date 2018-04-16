package com.gewei.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.OrderInfo;
import com.gewei.model.TAccount;

public interface ShanJiaFuWuMapper extends BaseMapper<OrderInfo> {
	Map<String, Object> getMerchantOrderSummaryById(String merchantId);

	List<Map<String, String>> getMerchantOrderListOfROOTCC(Map<String, Object> condition);

	List<Map<String, String>> getMerchantOrderListOfROOTRS(Map<String, Object> condition);

	List<Map<String, String>> getMerchantOrderListOfOthers(Map<String, Object> condition);

	Map<String, Object> getCCOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getRSOrderDetailInfoByOrderId(String orderId);

	Map<String, Object> getMerchantSettleOrderByOrderType(@Param("merchantId") String merchantId, @Param("createTime") String createTime);

	Map<String, Object> getMerchantSettleSummary(@Param("merchantId") String merchantId, @Param("createTime") String createTime);

	@Select("select UPDATE_TIME updateTime,TRADE_VALUE tradeValue,BANK_CARD_ID bankCardId,STATUS status from t_account_flow where PERSON_ID = #{merchantId} and TRADE_TYPE = '-' order by UPDATE_TIME desc limit #{start},#{rows}")
	@ResultType(Map.class)
	List<Map<String, Object>> getMerchantSettleFlow(@Param("merchantId") String merchantId, @Param("start") long start, @Param("rows") long rows);

	@Select("select count(*) from t_account_flow where PERSON_ID = #{merchantId} and TRADE_TYPE = '-'")
	long getMerchantSettleFlowCount(String merchantId);

	@Select("select ID id,PERSON_ID personId,BANK_HOST_NAME bankHostName,BANK_CARD_ID bankCardId,SETTLE_APPLYING settleApplying,BALANCE balance,UPDATE_TIME updateTime from t_account where PERSON_ID = #{merchantId}")
	@ResultType(TAccount.class)
	TAccount getAccountByPersonId(String merchantId);

	long getMerchantOrderCountOfROOTCC(HashMap<String, String> condition);

	long getMerchantOrderCountOfROOTRS(HashMap<String, String> condition);

	long getMerchantOrderCountOfOthers(HashMap<String, String> condition);
}