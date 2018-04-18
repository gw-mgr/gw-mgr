package com.gewei.model.vo;

import java.math.BigDecimal;

public class TzljForOrderSx {
	private String orderId;
	/**
	 * 投资账户名称
	 */
	private String tzzhmc;
	/**
	 * 分配比例
	 */
	private String fpbl;
	/**
	 * 追加保险费分配比例
	 */
	private String zjbxfpbl;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getTzzhmc() {
		return tzzhmc;
	}
	public void setTzzhmc(String tzzhmc) {
		this.tzzhmc = tzzhmc;
	}
	public String getFpbl() {
		return fpbl;
	}
	public void setFpbl(String fpbl) {
		this.fpbl = fpbl;
	}
	public String getZjbxfpbl() {
		return zjbxfpbl;
	}
	public void setZjbxfpbl(String zjbxfpbl) {
		this.zjbxfpbl = zjbxfpbl;
	}
	@Override
	public String toString() {
		return "TzljForOrderSx [orderId=" + orderId + ", tzzhmc=" + tzzhmc + ", fpbl=" + fpbl + ", zjbxfpbl=" + zjbxfpbl
				+ "]";
	}
	
}
