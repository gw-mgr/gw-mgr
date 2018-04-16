package com.gewei.wx.service;


public interface IWeixinPayService {
	public void pay(String orderName, String openId, String orderId, Double orderAmount);
}
