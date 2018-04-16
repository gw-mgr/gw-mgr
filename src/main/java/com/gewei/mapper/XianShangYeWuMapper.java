package com.gewei.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gewei.model.OrderInfo;

public interface XianShangYeWuMapper extends BaseMapper<OrderInfo> {
	List<Map<String, String>> getROOTCDList(Map<String, Object> condition);

	@Select("select M.MERCHANT_ID merchantId, M.MERCHANT_NAME merchantName, M.MERCHANT_PHOTO_URL merchantHeadPhoto, M.MERCHANT_ADDR merchantAddr, M.TELPHONE telephone, M.MERCHANT_ADDR merchantAddr, M.MERCHANT_DESCRIPT merchantDescript FROM t_merchant_manage M WHERE M.MERCHANT_TYPE like '%ROOTCM%' and M.STATUS = '02' limit #{start},#{rows}")
	List<Map<String, String>> getROOTCMMerchantList(@Param("start") Integer start, @Param("rows") Integer rows);

	@Select("select count(*) FROM t_merchant_manage M WHERE M.MERCHANT_TYPE like '%ROOTCM%' and M.STATUS = '02'")
	long getROOTCMMerchantCount();

	List<Map<String, String>> getProductListByMerchantId(Map<String, String> condition);

	Map<String, Object> getProductInfoById(Map<String, Object> condition);

	List<Map<String, String>> getDKProductList(Map<String, Object> condition);

	long getDKProductCount(Map<String, Object> condition);

	@Insert("insert into order_info ( ORDER_ID,ORDER_TYPE,ORDER_MONEY,USER_ID,CON_TEL,MERCHANT_ID,PRODUCT_ID,CREATE_TIME,UPDATE_TIME,ORDER_FLAG,COMMISSION,COMMISSION_RATE,COMMISSION_FOR ) "
			+ "VALUE (#{orderId}, #{orderType}, #{orderMoney}, #{userId}, #{conTel}, #{merchantId}, #{productId}, #{createTime}, #{updateTime}, #{orderFlag}, #{commission}, #{commissionRate}, #{commissionFor})")
	void createCommonOrder(OrderInfo orderInfo);

	@Insert("insert into order_info ( ORDER_ID,ORDER_TYPE,USER_ID,CON_TEL,TRADE_ADDR,CREATE_TIME,UPDATE_TIME,ORDER_FLAG,COMMISSION,COMMISSION_RATE,COMMISSION_FOR ) "
			+ "VALUE (#{orderId}, #{orderType}, #{userId}, #{conTel}, #{tradeAddr}, #{createTime}, #{updateTime}, #{orderFlag}, #{commission}, #{commissionRate}, #{commissionFor})")
	void createESCOrder(OrderInfo orderInfo);

	long getROOTCDCount(Map<String, Object> condition);

}