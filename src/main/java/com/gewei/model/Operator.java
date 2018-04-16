package com.gewei.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 操作员表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-19
 */
@TableName("operator")
public class Operator extends Model<Operator> {
	private static final long serialVersionUID = 1L;
	private String operId;
	private String rootdkCommissionType;
	private Float rootdkCommissionValue;
	private String rootccCommissionType;
	private Float rootccCommissionValue;
	private String rootrsCommissionType;
	private Float rootrsCommissionValue;
	private String bankName;
	private String bankHost;
	private String bankCard;
	private String mail;
	private String updateTime;
	private String description;
	private String remark1;
	private String remark2;
	private String remark3;

	public String getOperId() {
		return operId;
	}

	public void setOperId(String operId) {
		this.operId = operId;
	}

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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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
		return this.operId;
	}
}
