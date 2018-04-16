package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("order_info")
public class OrderInfo extends Model<OrderInfo> {

    private static final long serialVersionUID = 1L;

	private String orderId;
	private String orderType;
	private Long orderMoney;
	private String userId;
	private String merchantId;
	private String productId;
	private String conTel;
	private String tradeAddr;
	private String salesMan;
	private Long recorderCommission;
	private String ldyYjZf;
	private String recorder;
	private String createTime;
	private String updateTime;
	private String orderFlag;
	private Long targetAnnualIncome;
	private Long investmentAmount;
	private String beginDate;
	private String endDate;
	private Long commission;
	private Float commissionRate;
	private String commissionFor;
	private String yjZf;
	private String remark1;
	private String remark2;
	private String remark3;


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getConTel() {
		return conTel;
	}

	public void setConTel(String conTel) {
		this.conTel = conTel;
	}

	public String getTradeAddr() {
		return tradeAddr;
	}

	public void setTradeAddr(String tradeAddr) {
		this.tradeAddr = tradeAddr;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public Long getRecorderCommission() {
		return recorderCommission;
	}

	public void setRecorderCommission(Long recorderCommission) {
		this.recorderCommission = recorderCommission;
	}

	public String getLdyYjZf() {
		return ldyYjZf;
	}

	public void setLdyYjZf(String ldyYjZf) {
		this.ldyYjZf = ldyYjZf;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public Long getTargetAnnualIncome() {
		return targetAnnualIncome;
	}

	public void setTargetAnnualIncome(Long targetAnnualIncome) {
		this.targetAnnualIncome = targetAnnualIncome;
	}

	public Long getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(Long investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public Float getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Float commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getCommissionFor() {
		return commissionFor;
	}

	public void setCommissionFor(String commissionFor) {
		this.commissionFor = commissionFor;
	}

	public String getYjZf() {
		return yjZf;
	}

	public void setYjZf(String yjZf) {
		this.yjZf = yjZf;
	}

	public String getRemark1() {
		return remark1;
	}

	public void setRemark1(String remark1) {
		this.remark1 = remark1;
	}

	public String getRemark2() {
		return remark2;
	}

	public void setRemark2(String remark2) {
		this.remark2 = remark2;
	}

	public String getRemark3() {
		return remark3;
	}

	public void setRemark3(String remark3) {
		this.remark3 = remark3;
	}

	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

}
