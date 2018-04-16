package com.gewei.wx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gewei.commons.utils.DateUtil;
import com.gewei.mapper.BaoXianDingDanMapper;
import com.gewei.model.EditOrderCx;
import com.gewei.model.OrderInfo;
import com.gewei.wx.service.IBaoXianDingDanService;

@Service
public class BaoXianDingDanServiceImpl extends ServiceImpl<BaoXianDingDanMapper, OrderInfo> implements IBaoXianDingDanService {
	@Autowired
	private BaoXianDingDanMapper baoXianDingDanMapper;

//	@Override
//	public Map<String, Object> getSXOrderDetailInfoByOrderId(Map<String, String> condition) {
//		return baoXianDingDanMapper.getSXOrderDetailInfoByOrderId(condition);
//	}
//
//	@Override
//	public Map<String, Object> getCXOrderDetailInfoByOrderId(Map<String, String> condition) {
//		return baoXianDingDanMapper.getCXOrderDetailInfoByOrderId(condition);
//	}

	@Override
	public Map<String, Object> getBXOrderSummaryById(String userId) {
		return baoXianDingDanMapper.getBXOrderSummaryById(userId);
	}

	@Override
	public List<Map<String, String>> getRSOrderListOfSaleManByOrderType(Map<String, Object> condition) {
		return baoXianDingDanMapper.getRSOrderListOfSaleManByOrderType(condition);
	}

	@Override
	public List<Map<String, String>> getCXOrderListOfSaleManByOrderType(Map<String, Object> condition) {
		return baoXianDingDanMapper.getCXOrderListOfSaleManByOrderType(condition);
	}

	@Override
	public long getRSOrderCountOfSaleManByOrderType(Map<String, Object> condition) {
		return baoXianDingDanMapper.getRSOrderCountOfSaleManByOrderType(condition);
	}

	@Override
	public long getCXOrderCountOfSaleManByOrderType(Map<String, Object> condition) {
		return baoXianDingDanMapper.getCXOrderCountOfSaleManByOrderType(condition);
	}

	@Override
	public void editCXOrderCarInfoByOrderId(EditOrderCx editOrderCx) {
		editOrderCx.setUpdateTime(DateUtil.get_String$yyyyMMddHHmmss(new Date()));
		baoXianDingDanMapper.editCXOrderCarInfoByOrderId(editOrderCx);
	}

	@Override
	public void updateOrderFlagByOrderId(Map<String, String> condition) {
		baoXianDingDanMapper.updateOrderFlagByOrderId(condition);
	}
}
