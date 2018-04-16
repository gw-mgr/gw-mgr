package com.gewei.model.excel;


import com.gewei.commons.report.excel.ExcelField;
import com.gewei.commons.utils.JsonUtils;
/**
* @ClassName: SalesManExcel
* @Description: 业务员Excel实体
* @author caoyifu@iyooc.cn 
* @date 2018年1月24日 下午4:22:01 
*
 */
public class SalesManExcel{

	@ExcelField("姓名")
	private String userName;
	@ExcelField("证件类型")
	private String certType;
	@ExcelField("证件号码")
	private String certNo;
	@ExcelField("电话")
	private String telphone;
	@ExcelField("手机")
	private String mtelphone;
	@ExcelField("Mail")
	private String mail;
	@ExcelField("QQ")
	private String qq;
	@ExcelField("微信号")
	private String wechat;
	@ExcelField("区县")
	private String district;
	@ExcelField("城市")
	private String city;
	@ExcelField("省/直辖市")
	private String province;
	@ExcelField("地址")
	private String linkaddr;
	@ExcelField("保险公司")
	private String insuranceCompany;
	@ExcelField("保险公司ID")
	private String insuranceCompanyId;
	@ExcelField("开户行")
	private String bankName;
	@ExcelField("开户行名称")
	private String bankUserName;
	@ExcelField("银行卡号")
	private String bankNo;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getMtelphone() {
		return mtelphone;
	}

	public void setMtelphone(String mtelphone) {
		this.mtelphone = mtelphone;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getLinkaddr() {
		return linkaddr;
	}

	public void setLinkaddr(String linkaddr) {
		this.linkaddr = linkaddr;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(String insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankUserName() {
		return bankUserName;
	}

	public void setBankUserName(String bankUserName) {
		this.bankUserName = bankUserName;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
