package com.gewei.model;
import com.baomidou.mybatisplus.activerecord.Model;
import java.io.Serializable;
public class OperatorRes extends Model<OperatorRes> {
	private static final long serialVersionUID = 1L;
	private String id;
	private String userName;
	private String telephone;
	private Float rootdkCommissionValue;
	private String rootdkCommissionStr;
	private String rootdkCommissionFlag;
	private String rootdkCommissionRateFlag;
	private Float rootccCommissionValue;
	private String rootccCommissionStr;
	private String rootccCommissionFlag;
	private String rootccCommissionRateFlag;
	private Float rootrsCommissionValue;
	private String rootrsCommissionStr;
	private String rootrsCommissionFlag;
	private String rootrsCommissionRateFlag;
	private String bankName;
	private String bankHost;
	private String bankCard;
	private String roleId;
	private String roleName;
	private String mail;
	private String createTime;
	private String updateTime;
	private String status;
	private String description;
	private String remark1;
	private String remark2;
	private String remark3;
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRootdkCommissionStr() {
		return rootdkCommissionStr;
	}
	public void setRootdkCommissionStr(String rootdkCommissionStr) {
		this.rootdkCommissionStr = rootdkCommissionStr;
	}
	public String getRootccCommissionStr() {
		return rootccCommissionStr;
	}
	public void setRootccCommissionStr(String rootccCommissionStr) {
		this.rootccCommissionStr = rootccCommissionStr;
	}
	public String getRootrsCommissionStr() {
		return rootrsCommissionStr;
	}
	public void setRootrsCommissionStr(String rootrsCommissionStr) {
		this.rootrsCommissionStr = rootrsCommissionStr;
	}
	public Float getRootdkCommissionValue() {
		return rootdkCommissionValue;
	}
	public void setRootdkCommissionValue(Float rootdkCommissionValue) {
		this.rootdkCommissionValue = rootdkCommissionValue;
	}
	public Float getRootccCommissionValue() {
		return rootccCommissionValue;
	}
	public void setRootccCommissionValue(Float rootccCommissionValue) {
		this.rootccCommissionValue = rootccCommissionValue;
	}
	public Float getRootrsCommissionValue() {
		return rootrsCommissionValue;
	}
	public void setRootrsCommissionValue(Float rootrsCommissionValue) {
		this.rootrsCommissionValue = rootrsCommissionValue;
	}
	public String getRootdkCommissionFlag() {
		return rootdkCommissionFlag;
	}
	public void setRootdkCommissionFlag(String rootdkCommissionFlag) {
		this.rootdkCommissionFlag = rootdkCommissionFlag;
	}
	public String getRootdkCommissionRateFlag() {
		return rootdkCommissionRateFlag;
	}
	public void setRootdkCommissionRateFlag(String rootdkCommissionRateFlag) {
		this.rootdkCommissionRateFlag = rootdkCommissionRateFlag;
	}
	public String getRootccCommissionFlag() {
		return rootccCommissionFlag;
	}
	public void setRootccCommissionFlag(String rootccCommissionFlag) {
		this.rootccCommissionFlag = rootccCommissionFlag;
	}
	public String getRootccCommissionRateFlag() {
		return rootccCommissionRateFlag;
	}
	public void setRootccCommissionRateFlag(String rootccCommissionRateFlag) {
		this.rootccCommissionRateFlag = rootccCommissionRateFlag;
	}
	public String getRootrsCommissionFlag() {
		return rootrsCommissionFlag;
	}
	public void setRootrsCommissionFlag(String rootrsCommissionFlag) {
		this.rootrsCommissionFlag = rootrsCommissionFlag;
	}
	public String getRootrsCommissionRateFlag() {
		return rootrsCommissionRateFlag;
	}
	public void setRootrsCommissionRateFlag(String rootrsCommissionRateFlag) {
		this.rootrsCommissionRateFlag = rootrsCommissionRateFlag;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
	@Override
	protected Serializable pkVal() {
		return this.id;
	}
}
