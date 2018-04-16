package com.gewei.model.excel;

import com.gewei.commons.report.excel.ExcelField;

/**
 * <p>
 * 操作员表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-19
 */
public class OperatorExcel{
	@ExcelField("贷款业务类型")
	private String rootdkCommissionType;
	@ExcelField("贷款业务")
	private Float rootdkCommissionValue;
	@ExcelField("财产保险类型")
	private String rootccCommissionType;
	@ExcelField("财产保险")
	private Float rootccCommissionValue;
	@ExcelField("人寿保险类型")
	private String rootrsCommissionType;
	@ExcelField("人寿保险")
	private Float rootrsCommissionValue;
	@ExcelField("开户行")
	private String bankName;
	@ExcelField("开户名称")
	private String bankHost;
	@ExcelField("银行卡号")
	private String bankCard;
	@ExcelField("邮箱")
	private String mail;
	@ExcelField("备注")
	private String description;
	@ExcelField("姓名")
	private String userName;
	@ExcelField("手机")
	private String telephone;
	@ExcelField("角色")
	private String roleName;


	public String getRootdkCommissionType() {
		return rootdkCommissionType;
	}

	public void setRootdkCommissionType(String rootdkCommissionType) {
		this.rootdkCommissionType = rootdkCommissionType;
	}

	public Float getRootdkCommissionValue() {
		return rootdkCommissionValue;
	}

	public void setRootdkCommissionValue(Float rootdkCommissionValue) {
		this.rootdkCommissionValue = rootdkCommissionValue;
	}

	public String getRootccCommissionType() {
		return rootccCommissionType;
	}

	public void setRootccCommissionType(String rootccCommissionType) {
		this.rootccCommissionType = rootccCommissionType;
	}

	public Float getRootccCommissionValue() {
		return rootccCommissionValue;
	}

	public void setRootccCommissionValue(Float rootccCommissionValue) {
		this.rootccCommissionValue = rootccCommissionValue;
	}

	public String getRootrsCommissionType() {
		return rootrsCommissionType;
	}

	public void setRootrsCommissionType(String rootrsCommissionType) {
		this.rootrsCommissionType = rootrsCommissionType;
	}

	public Float getRootrsCommissionValue() {
		return rootrsCommissionValue;
	}

	public void setRootrsCommissionValue(Float rootrsCommissionValue) {
		this.rootrsCommissionValue = rootrsCommissionValue;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
