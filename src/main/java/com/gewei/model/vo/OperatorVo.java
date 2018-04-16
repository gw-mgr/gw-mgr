package com.gewei.model.vo;
import java.io.Serializable;
/**
 * @Description: 人员管理搜索，接收前端参数实体
 * @author: Tiger
 * @date: 2018年1月28日 下午2:01:47
 */
public class OperatorVo implements Serializable {
	private String id;
	private String userName;
	private String telephone;
	private String roleId;
	private String rootdkCommission;
	private String rootccCommissionValue;
	private String rootdkCommissionRate;
	private String rootccCommission;
	private String rootrsCommissionValue;
	private String rootccCommissionRate;
	private String rootrsCommission;
	private String rootdkCommissionValue;
	private String rootrsCommissionRate;
	private String bankName;
	private String bankHost;
	private String bankCard;
	private String mail;
	private String createTime;
	private String updateTime;
	private String status;
	private String description;
	private String remark1;
	private String remark2;
	private String remark3;
	public String getRootdkCommission() {
		return rootdkCommission;
	}
	public void setRootdkCommission(String rootdkCommission) {
		this.rootdkCommission = rootdkCommission;
	}
	public String getRootccCommissionValue() {
		return rootccCommissionValue;
	}
	public void setRootccCommissionValue(String rootccCommissionValue) {
		this.rootccCommissionValue = rootccCommissionValue;
	}
	public String getRootdkCommissionRate() {
		return rootdkCommissionRate;
	}
	public void setRootdkCommissionRate(String rootdkCommissionRate) {
		this.rootdkCommissionRate = rootdkCommissionRate;
	}
	public String getRootccCommission() {
		return rootccCommission;
	}
	public void setRootccCommission(String rootccCommission) {
		this.rootccCommission = rootccCommission;
	}
	public String getRootrsCommissionValue() {
		return rootrsCommissionValue;
	}
	public void setRootrsCommissionValue(String rootrsCommissionValue) {
		this.rootrsCommissionValue = rootrsCommissionValue;
	}
	public String getRootccCommissionRate() {
		return rootccCommissionRate;
	}
	public void setRootccCommissionRate(String rootccCommissionRate) {
		this.rootccCommissionRate = rootccCommissionRate;
	}
	public String getRootrsCommission() {
		return rootrsCommission;
	}
	public void setRootrsCommission(String rootrsCommission) {
		this.rootrsCommission = rootrsCommission;
	}
	public String getRootdkCommissionValue() {
		return rootdkCommissionValue;
	}
	public void setRootdkCommissionValue(String rootdkCommissionValue) {
		this.rootdkCommissionValue = rootdkCommissionValue;
	}
	public String getRootrsCommissionRate() {
		return rootrsCommissionRate;
	}
	public void setRootrsCommissionRate(String rootrsCommissionRate) {
		this.rootrsCommissionRate = rootrsCommissionRate;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankHost() {
		return bankHost;
	}
	public void setBankHost(String bankHost) {
		this.bankHost = bankHost;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	private static final long serialVersionUID = 1L;
	private String keywordType;
	private String keywordInfo;
	public String getKeywordType() {
		return keywordType;
	}
	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}
	public String getKeywordInfo() {
		return keywordInfo;
	}
	public void setKeywordInfo(String keywordInfo) {
		this.keywordInfo = keywordInfo;
	}
}
