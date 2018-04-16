package com.gewei.model.vo;

import com.gewei.commons.utils.JsonUtils;

import java.io.Serializable;

/**
 * <p>
 * 订单表，包括贷款，理财和车
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-06
 */
public class FOrderInfoVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 订单业务编号，用于其他平台传输
     */
	private String orderId;
    /**
     * 订单类型，见t_merchant_product_category表
     */
	private String orderType;
    /**
     * 贷款金额，车贷业务成交价格，单位：分
     */
	private Long orderMoney;
    /**
     * 客户编号，即：该订单属于哪位客户
     */
	private String userId;
	private String userName;
    /**
     * 供服务商的联系方式
     */
	private String conTel;
    /**
     * 交易地址，联系地址，如二手车交易
     */
	private String tradeAddr;
    /**
     * 业务员编号
     */
	private String salesMan;
    /**
     * 录单员佣金
     */
	private Long recorderCommission;
    /**
     * 录单员
     */
	private String recorder;
    /**
     * 服务商ID，见表t_merchant_manage
     */
	private String merchantId;
	private String merchantName;
    /**
     * 商品编号，见t_product_manage
     */
	private String productId;
    /**
     * 创建时间
     */
	private String createTime;
    /**
     * 更新时间
     */
	private String updateTime;
    /**
     * 订单状态（01-未接单、02-接单未放款、03-已放款、04-已收回、05-未生效、06-投资中、07-投资结束、08-已接单、09-验车/报价、10-已成交、11-已取消、12-处理完毕、13-、14-、15-、），默认01
     */
	private String orderFlag;
    /**
     * 目标年化收益，单位：分
     */
	private Long targetAnnualIncome;
    /**
     * 投资金额，单位：分
     */
	private Long investmentAmount;
    /**
     * 生效时间
     */
	private String beginDate;
    /**
     * 到期时间
     */
	private String endDate;
    /**
     * 佣金
     */
	private Long commission;
    /**
     * 佣金支付对象
     */
	private String commissionFor;
    /**
     * 佣金比例
     */
	private String commissionRate;
    /**
     * 备用字段1
     */
	private String remark1;
    /**
     * 备用字段2
     */
	private String remark2;
    /**
     * 备用字段3
     */
	private String remark3;

    /**
     * 会员手机号，可修改，唯一
     */
	private String telephone;
    /**
     * 所属机构
     */
	private String organizations;
    /**
     * 会员性别，01男，02女，03保密，默认03
     */
	private String sex;
	private String cardType;
    /**
     * 对应的证件号
     */
	private String cardId;
    /**
     * 车牌号
     */
	private String carNum;
    /**
     * 车架号
     */
	private String carFrameNum;
    /**
     * 车辆类型
     */
	private String carType;
    /**
     * 发动机号
     */
	private String engineNum;
    /**
     * 厂牌类型
     */
	private String changType;
    /**
     * 交强险起保日
     */
	private String trafficInsStartTime;
    /**
     * 商业险起保日
     */
	private String businessInsStartTime;
    /**
     * 初登日期
     */
	private String registerTime;
    /**
     * 年审到期日
     */
	private String examineTime;
    /**
     * 状态，01-正常，02-删除
     */
	private String status;

	
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(String commissionRate) {
		this.commissionRate = commissionRate;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(Long orderMoney) {
		this.orderMoney = orderMoney;
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getCommissionFor() {
		return commissionFor;
	}

	public void setCommissionFor(String commissionFor) {
		this.commissionFor = commissionFor;
	}
	
	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getOrganizations() {
		return organizations;
	}

	public void setOrganizations(String organizations) {
		this.organizations = organizations;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarFrameNum() {
		return carFrameNum;
	}

	public void setCarFrameNum(String carFrameNum) {
		this.carFrameNum = carFrameNum;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getChangType() {
		return changType;
	}

	public void setChangType(String changType) {
		this.changType = changType;
	}

	public String getTrafficInsStartTime() {
		return trafficInsStartTime;
	}

	public void setTrafficInsStartTime(String trafficInsStartTime) {
		this.trafficInsStartTime = trafficInsStartTime;
	}

	public String getBusinessInsStartTime() {
		return businessInsStartTime;
	}

	public void setBusinessInsStartTime(String businessInsStartTime) {
		this.businessInsStartTime = businessInsStartTime;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
