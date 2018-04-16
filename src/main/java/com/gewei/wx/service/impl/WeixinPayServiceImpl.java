package com.gewei.wx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gewei.wx.service.IWeixinPayService;
import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;

@Service
public class WeixinPayServiceImpl implements IWeixinPayService {
	@Autowired
	private BestPayServiceImpl bestPayService;

	@Override
	public void pay(String orderName, String openId, String orderId, Double orderAmount) {
		if (Double.compare(Double.valueOf("0"), orderAmount) >= 0) {
			throw new RuntimeException("支付金额不能小于零");
		}
		PayRequest payRequest = new PayRequest();
		payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_H5);
		payRequest.setOrderName(orderName);
		payRequest.setOpenid(openId);
		payRequest.setOrderAmount(orderAmount);
		payRequest.setOrderId(orderId);
		bestPayService.pay(payRequest);
	}

}
