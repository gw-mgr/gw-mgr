package com.gewei.model.vo;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;

public class EditOrderInfo extends Model<EditOrderInfo> {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String userId;
	private String customerId;
	private String customerName;
	private String userName;
	private String tradeAddr;
	private String detailAddr;
	private String province;
	private String city;
	private String country;
	private String telephone;
	private String cardType;
	private String cardId;
	private String marryFlag;
	private String socialFlag;
	private String houseFlag;
	private String HOUSE_AREA_PROVINCE;
	private String HOUSE_AREA_CITY;
	private String HOUSE_AREA_COUNTRY;
	private String inCome;
	private String workType;
	private String carFlag;
	private String carNum;
	private String carFrameNum;
	private String engineNum;
	private String changType;
	private String registerTime;
	private String trafficInsStartTime;
	private String businessInsStartTime;
	private String trafficInsEndTime;
	private String examineTime;
	private String carType;
	private String carDistance;
	private String updateTime;
	private Long orderMoney;

	public String getHOUSE_AREA_PROVINCE() {
		return HOUSE_AREA_PROVINCE;
	}

	public void setHOUSE_AREA_PROVINCE(String hOUSE_AREA_PROVINCE) {
		HOUSE_AREA_PROVINCE = hOUSE_AREA_PROVINCE;
	}

	public String getHOUSE_AREA_CITY() {
		return HOUSE_AREA_CITY;
	}

	public void setHOUSE_AREA_CITY(String hOUSE_AREA_CITY) {
		HOUSE_AREA_CITY = hOUSE_AREA_CITY;
	}

	public String getHOUSE_AREA_COUNTRY() {
		return HOUSE_AREA_COUNTRY;
	}

	public void setHOUSE_AREA_COUNTRY(String hOUSE_AREA_COUNTRY) {
		HOUSE_AREA_COUNTRY = hOUSE_AREA_COUNTRY;
	}

	public String getBusinessInsStartTime() {
		return businessInsStartTime;
	}

	public void setBusinessInsStartTime(String businessInsStartTime) {
		this.businessInsStartTime = businessInsStartTime;
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

	public String getDetailAddr() {
		return detailAddr;
	}

	public void setDetailAddr(String detailAddr) {
		this.detailAddr = detailAddr;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getTradeAddr() {
		return tradeAddr;
	}

	public void setTradeAddr(String tradeAddr) {
		this.tradeAddr = tradeAddr;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public String getMarryFlag() {
		return marryFlag;
	}

	public void setMarryFlag(String marryFlag) {
		this.marryFlag = marryFlag;
	}

	public String getSocialFlag() {
		return socialFlag;
	}

	public void setSocialFlag(String socialFlag) {
		this.socialFlag = socialFlag;
	}

	public String getHouseFlag() {
		return houseFlag;
	}

	public void setHouseFlag(String houseFlag) {
		this.houseFlag = houseFlag;
	}

	public String getInCome() {
		return inCome;
	}

	public void setInCome(String inCome) {
		this.inCome = inCome;
	}

	public String getWorkType() {
		return workType;
	}

	public void setWorkType(String workType) {
		this.workType = workType;
	}

	public String getCarFlag() {
		return carFlag;
	}

	public void setCarFlag(String carFlag) {
		this.carFlag = carFlag;
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

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getTrafficInsStartTime() {
		return trafficInsStartTime;
	}

	public void setTrafficInsStartTime(String trafficInsStartTime) {
		this.trafficInsStartTime = trafficInsStartTime;
	}

	public String getTrafficInsEndTime() {
		return trafficInsEndTime;
	}

	public void setTrafficInsEndTime(String trafficInsEndTime) {
		this.trafficInsEndTime = trafficInsEndTime;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getCarDistance() {
		return carDistance;
	}

	public void setCarDistance(String carDistance) {
		this.carDistance = carDistance;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}
}
