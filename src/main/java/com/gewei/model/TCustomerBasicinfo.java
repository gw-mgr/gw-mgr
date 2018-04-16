package com.gewei.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

@TableName("t_customer_basicinfo")
public class TCustomerBasicinfo extends Model<TCustomerBasicinfo> {

    private static final long serialVersionUID = 1L;

	private String userId;
	private String userName;
	private String telephone;
	private String detailAddr;
	private String province;
	private String city;
	private String country;
	private String organizations;
	private String sex;
	private Integer age;
	private String cardType;
	private String cardId;
	private String marryFlag;
	private String socialFlag;
	private String houseFlag;
	private String houseAreaProvince;
	private String houseAreaCity;
	private String houseAreaCountry;
	private String income;
	private String workType;
	private String carFlag;
	private String carNum;
	private String carFrameNum;
	private String carType;
	private Long carDistance;
	private String engineNum;
	private String changType;
	private String trafficInsStartTime;
	private String businessInsStartTime;
	private String registerTime;
	private String examineTime;
	private String status;
	private String createTime;
	private String updateTime;
	private String remark1;
	private String remark2;


	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
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

	public String getHouseAreaProvince() {
		return houseAreaProvince;
	}

	public void setHouseAreaProvince(String houseAreaProvince) {
		this.houseAreaProvince = houseAreaProvince;
	}

	public String getHouseAreaCity() {
		return houseAreaCity;
	}

	public void setHouseAreaCity(String houseAreaCity) {
		this.houseAreaCity = houseAreaCity;
	}

	public String getHouseAreaCountry() {
		return houseAreaCountry;
	}

	public void setHouseAreaCountry(String houseAreaCountry) {
		this.houseAreaCountry = houseAreaCountry;
	}

	public String getIncome() {
		return income;
	}

	public void setIncome(String income) {
		this.income = income;
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

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public Long getCarDistance() {
		return carDistance;
	}

	public void setCarDistance(Long carDistance) {
		this.carDistance = carDistance;
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

	@Override
	protected Serializable pkVal() {
		return this.userId;
	}

}
