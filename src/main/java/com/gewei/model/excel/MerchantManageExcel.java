package com.gewei.model.excel;
import com.gewei.commons.report.excel.ExcelField;
/**
 * @Description: 服务商EXCEL实体
 * @author: Tiger
 * @date: 2018年2月7日 下午1:18:51
 */
public class MerchantManageExcel {
	@ExcelField("服务商编号")
	private String merchantId;
	@ExcelField("服务商名称")
	private String merchantName;
	@ExcelField("服务范围")
	private String merchantType;
	@ExcelField("授权区域")
	private String grantArea;
	@ExcelField("服务商地址")
	private String merchantAddr;
	@ExcelField("联系电话")
	private String telphone;
	@ExcelField(value = "余额")
	private String balance;
	@ExcelField("状态")
	private String status;
	@ExcelField(value = "创建时间")
	private String createTime;
	@ExcelField("介绍")
	private String merchantDescript;
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
	public String getMerchantType() {
		return merchantType;
	}
	public void setMerchantType(String merchantType) {
		this.merchantType = merchantType;
	}
	public String getGrantArea() {
		return grantArea;
	}
	public void setGrantArea(String grantArea) {
		this.grantArea = grantArea;
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
	public String getBalance() {
		return balance;
	}
	public void setBalance(String balance) {
		this.balance = balance;
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
	public String getMerchantDescript() {
		return merchantDescript;
	}
	public void setMerchantDescript(String merchantDescript) {
		this.merchantDescript = merchantDescript;
	}
}
