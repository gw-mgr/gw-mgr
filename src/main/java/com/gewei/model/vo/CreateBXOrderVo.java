package com.gewei.model.vo;

import java.io.Serializable;
import com.gewei.commons.utils.JsonUtils;

public class CreateBXOrderVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String userId;
	private String orderType;
	private String tbrName;
	private String tbrSex;
	private Integer tbrAge;
	private String bbRName;
	private String bbRSex;
	private Integer bbRAge;
	private String bxRemark;
	private String carOwnerName;
	private String carNum;
	private String tbDanWei;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTbrName() {
		return tbrName;
	}

	public void setTbrName(String tbrName) {
		this.tbrName = tbrName;
	}

	public String getTbrSex() {
		return tbrSex;
	}

	public void setTbrSex(String tbrSex) {
		this.tbrSex = tbrSex;
	}

	public Integer getTbrAge() {
		return tbrAge;
	}

	public void setTbrAge(Integer tbrAge) {
		this.tbrAge = tbrAge;
	}

	public String getBbRName() {
		return bbRName;
	}

	public void setBbRName(String bbRName) {
		this.bbRName = bbRName;
	}

	public String getBbRSex() {
		return bbRSex;
	}

	public void setBbRSex(String bbRSex) {
		this.bbRSex = bbRSex;
	}

	public Integer getBbRAge() {
		return bbRAge;
	}

	public void setBbRAge(Integer bbRAge) {
		this.bbRAge = bbRAge;
	}

	public String getBxRemark() {
		return bxRemark;
	}

	public void setBxRemark(String bxRemark) {
		this.bxRemark = bxRemark;
	}

	public String getCarOwnerName() {
		return carOwnerName;
	}

	public void setCarOwnerName(String carOwnerName) {
		this.carOwnerName = carOwnerName;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getTbDanWei() {
		return tbDanWei;
	}

	public void setTbDanWei(String tbDanWei) {
		this.tbDanWei = tbDanWei;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
