package com.gewei.model.excel;

import com.gewei.commons.report.excel.ExcelField;

public class CustomerExcel {

	@ExcelField("姓名")
	private String userName;
	@ExcelField("电话")
	private String telephone;
	@ExcelField("性别")
	private String sex;
	@ExcelField("年龄")
	private Integer age;
	@ExcelField("证件类型")
	private String cardType;
	@ExcelField("证件号码")
	private String cardId;
	@ExcelField("是否结婚")
	private String marryFlag;
	@ExcelField("有无社保")
	private String socialFlag;
	@ExcelField("房产类型")
	private String houseFlag;
	@ExcelField("房产区域")
	private String houseArea;
	@ExcelField("月收入")
	private String income;
	@ExcelField("工作性质")
	private String workType;
	@ExcelField("车辆类型")
	private String carFlag;
	@ExcelField("车牌号")
	private String carNum;
	@ExcelField("车架号")
	private String carFrameNum;
	@ExcelField("车辆类型")
	private String carType;
	@ExcelField("汽车行驶公里数")
	private Long carDistance;
	@ExcelField("发动机号")
	private String engineNum;
	@ExcelField("厂牌类型")
	private String changType;
	@ExcelField("交强险起保日")
	private String trafficInsStartTime;
	@ExcelField("商业险起保日")
	private String businessInsStartTime;
	@ExcelField("初登日期")
	private String registerTime;
	@ExcelField("年审到期日")
	private String examineTime;
	@ExcelField("创建时间")
	private String createTime;


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

	public String getHouseArea() {
		return houseArea;
	}

	public void setHouseArea(String houseArea) {
		this.houseArea = houseArea;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}


}
