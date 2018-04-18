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
	private String cardType;
	@ExcelField("证件号码")
	private String cardId;
	@ExcelField("电话")
	private String telephone;
	@ExcelField("手机")
	private String mobilePhone;
	@ExcelField("Mail")
	private String mail;
	@ExcelField("QQ")
	private String qq;
	@ExcelField("微信号")
	private String wechatNum;
	@ExcelField("微信昵称")
	private String wechatName;
	@ExcelField("区县")
	private String country;
	@ExcelField("城市")
	private String city;
	@ExcelField("省/直辖市")
	private String province;
	@ExcelField("地址")
	private String userAddr;
	@ExcelField("保险公司")
	private String insCompanyName;
	@ExcelField("保险公司ID")
	private String insCompany	;
	@ExcelField("开户行")
	private String bankName;
	@ExcelField("开户行名称")
	private String bankHost;
	@ExcelField("银行卡号")
	private String bankCard;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getWechatNum() {
		return wechatNum;
	}

	public void setWechatNum(String wechatNum) {
		this.wechatNum = wechatNum;
	}

	public String getWechatName() {
		return wechatName;
	}

	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getUserAddr() {
		return userAddr;
	}

	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}

	public String getInsCompanyName() {
		return insCompanyName;
	}

	public void setInsCompanyName(String insCompanyName) {
		this.insCompanyName = insCompanyName;
	}

	public String getInsCompany() {
		return insCompany;
	}

	public void setInsCompany(String insCompany) {
		this.insCompany = insCompany;
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

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
