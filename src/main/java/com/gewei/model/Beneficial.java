package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.gewei.commons.utils.JsonUtils;

/**
 * <p>
 * 人寿保险-受益人表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-04-02
 */
public class Beneficial extends Model<Beneficial> {
	private static final long serialVersionUID = 1L;
	private String personId;
	private String orderId;
	private String beneficialRate;
	private String beneficialName;
	private String sex;
	private String birthDate;
	private String relationship;
	private String certType;
	private String certNo;
	private String validityDate;
	private String residentialAddress;
	private String province;
	private String city;
	private String country;
	private String beneficiaryOrder;

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
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 备注
	 */
	private String description;
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

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getBeneficialRate() {
		return beneficialRate;
	}

	public void setBeneficialRate(String beneficialRate) {
		this.beneficialRate = beneficialRate;
	}

	public String getBeneficialName() {
		return beneficialName;
	}

	public void setBeneficialName(String beneficialName) {
		this.beneficialName = beneficialName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
	}

	public String getBeneficiaryOrder() {
		return beneficiaryOrder;
	}

	public void setBeneficiaryOrder(String beneficiaryOrder) {
		this.beneficiaryOrder = beneficiaryOrder;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
		return this.personId;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
