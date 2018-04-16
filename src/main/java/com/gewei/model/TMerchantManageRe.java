package com.gewei.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 服务商基本信息
 * </p>
 *
 * @author zhixuan.wang
 * @since 2018-01-26
 */
public class TMerchantManageRe extends Model<TMerchantManageRe> {
	private static final long serialVersionUID = 1L;
	/**
	 * 服务商编号
	 */
	private String merchantId;
	/**
	 * 服务商名称
	 */
	private String merchantName;
	/**
	 * 服务商头像
	 */
	private String merchantPhotoUrl;
	/**
	 * 服务商类型/范围
	 */
	private String merchantType;
	private String province;
	private String city;
	private String country;
	/**
	 * 服务商地址
	 */
	private String merchantAddr;
	/**
	 * 联系电话
	 */
	private String telphone;
	/**
	 * 授权区域
	 */
	private String grantArea;
	/**
	 * 授权区域
	 */
	private String[] grantAreaIds;
	/**
	 * 商户创建时间，19700101000000
	 */
	private Long createTime;
	/**
	 * 商户修改时间
	 */
	private Long updateTime;
	/**
	 * 介绍
	 */
	private String merchantDescript;
	/**
	 * 操作人（上架、下架、删除等操作的人员）
	 */
	private String operatorId;
	/**
	 * 数据状态（00删除，01上架，02下架），默认02
	 */
	private String status;
	private Long balance;
	private String balanceResp;

	public String getBalanceResp() {
		return balanceResp;
	}

	public void setBalanceResp(String balanceResp) {
		this.balanceResp = balanceResp;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}
	/**
	 * 备用字段1
	 */
	private String remark1;
	/**
	 * 备用字段2
	 */
	private String remark2;
	/**
	 * 备用字段3
	 */
	private String remark3;

	public String[] getGrantAreaIds() {
		return grantAreaIds;
	}

	public void setGrantAreaIds(String[] grantAreaIds) {
		this.grantAreaIds = grantAreaIds;
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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantPhotoUrl() {
		return merchantPhotoUrl;
	}

	public void setMerchantPhotoUrl(String merchantPhotoUrl) {
		this.merchantPhotoUrl = merchantPhotoUrl;
	}

	public String getMerchantType() {
		return merchantType;
	}

	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}

	public String getMerchantAddr() {
		return merchantAddr;
	}

	public void setMerchantAddr(String merchantAddr) {
		this.merchantAddr = merchantAddr;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getGrantArea() {
		return grantArea;
	}

	public void setGrantArea(String grantArea) {
		this.grantArea = grantArea;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public String getMerchantDescript() {
		return merchantDescript;
	}

	public void setMerchantDescript(String merchantDescript) {
		this.merchantDescript = merchantDescript;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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
		return this.merchantId;
	}
}
