package com.gewei.model.vo;

import java.io.Serializable;

/**
 * @Description: 服务商搜索，接收前端参数实体
 * @author: Tiger
 * @date: 2018年1月28日 下午2:01:47
 */
public class MerchantVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String createStartTime;
	private String createEndTime;
	private String province;
	private String city;
	private String country;
	private String merchantType;
	private String keywordType;
	private String keywordInfo;
	private String status;

	public String getKeywordType() {
		return keywordType;
	}

	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateStartTime() {
		return createStartTime;
	}

	public void setCreateStartTime(String createStartTime) {
		this.createStartTime = createStartTime;
	}

	public String getCreateEndTime() {
		return createEndTime;
	}

	public void setCreateEndTime(String createEndTime) {
		this.createEndTime = createEndTime;
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

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getKeywordInfo() {
		return keywordInfo;
	}

	public void setKeywordInfo(String keywordInfo) {
		this.keywordInfo = keywordInfo;
	}
}
