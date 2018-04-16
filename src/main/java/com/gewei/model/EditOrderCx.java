package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.gewei.commons.utils.JsonUtils;

/**
 * <p>
 * 财产保险订单表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-23
 */
public class EditOrderCx extends Model<EditOrderCx> {
	private static final long serialVersionUID = 1L;
	private String orderId;
	private String userId;
	private String carNum;
	private String carFrameNum;
	private String engineNum;
	private String changType;
	private String carType;
	private String registerTime;
	private String businessInsStartTime;
	private String trafficInsStartTime;
	private String carOwner;
	private String ownerCardType;
	private String ownerCardId;
	private String tbrId;
	private String tbr;
	private String tbrCardType;
	private String tbrCardId;
	private String beneficiary;
	private String bbrCardType;
	private String bbrCardId;
	private String updateTime;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getBbrCardId() {
		return bbrCardId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public void setBbrCardId(String bbrCardId) {
		this.bbrCardId = bbrCardId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getBusinessInsStartTime() {
		return businessInsStartTime;
	}

	public void setBusinessInsStartTime(String businessInsStartTime) {
		this.businessInsStartTime = businessInsStartTime;
	}

	public String getTrafficInsStartTime() {
		return trafficInsStartTime;
	}

	public void setTrafficInsStartTime(String trafficInsStartTime) {
		this.trafficInsStartTime = trafficInsStartTime;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getOwnerCardType() {
		return ownerCardType;
	}

	public void setOwnerCardType(String ownerCardType) {
		this.ownerCardType = ownerCardType;
	}

	public String getOwnerCardId() {
		return ownerCardId;
	}

	public void setOwnerCardId(String ownerCardId) {
		this.ownerCardId = ownerCardId;
	}

	public String getTbrCardType() {
		return tbrCardType;
	}

	public void setTbrCardType(String tbrCardType) {
		this.tbrCardType = tbrCardType;
	}

	public String getTbrCardId() {
		return tbrCardId;
	}

	public void setTbrCardId(String tbrCardId) {
		this.tbrCardId = tbrCardId;
	}

	public String getTbrId() {
		return tbrId;
	}

	public void setTbrId(String tbrId) {
		this.tbrId = tbrId;
	}

	public String getTbr() {
		return tbr;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public void setTbr(String tbr) {
		this.tbr = tbr;
	}

	public String getBbrCardType() {
		return bbrCardType;
	}

	public void setBbrCardType(String bbrCardType) {
		this.bbrCardType = bbrCardType;
	}

	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
