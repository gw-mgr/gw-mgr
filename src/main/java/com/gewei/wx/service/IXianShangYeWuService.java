package com.gewei.wx.service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.service.IService;
import com.gewei.model.OrderInfo;

public interface IXianShangYeWuService extends IService<OrderInfo> {
	List<Map<String, String>> getROOTCDList(Map<String, Object> condition);

	Object getROOTCDMerchantGrantAreaList();

	List<Map<String, String>> getROOTCMMerchantList(Integer page, Integer rows);

	List<Map<String, String>> getProductListByMerchantId(Map<String, String> condition);

	Map<String, Object> getProductInfoById(Map<String, Object> condition);

	List<Map<String, String>> getDKProductList(Map<String, Object> condition);

	void createCommonOrder(OrderInfo orderInfo);

	void createESCOrder(OrderInfo orderInfo);

	long getROOTCDCount(Map<String, Object> condition);

	long getROOTCMMerchantCount();

	long getDKProductCount(Map<String, Object> condition);
}
