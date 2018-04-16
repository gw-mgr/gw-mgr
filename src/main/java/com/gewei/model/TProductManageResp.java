package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * <p>
 * 产品基本信息表，C_：汽车产品，L_：理财产品，D_：贷款产品
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-08
 */
@TableName("t_product_manage")
public class TProductManageResp extends Model<TProductManageResp> {
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private String status;
	private String productType;
	private String merchantId;
	private Long currPrice;
	private String currPriceStr;
	private Long origPrice;
	private String origPriceStr;
	private Long soldQuantity;
	private Long tockQuantity;
	private Long createTime;
	private Long updateTime;
	private String productDescript;
	private String handleId;
	private Long commission;
	private Float commissionRate;
	private String commissionStr;
	private Integer lNumberOfPerson;
	private Long lMixBuyMoney;
	private float lMixBuyMoneyResp;
	private Integer lRiskGrade;
	private Integer lInvestDays;
	private Long lGoalIncome;
	private Long dMoney;
	private String dIncome;
	private String dCarFlag;
	private String dSocialFlag;
	private String dHouseFlag;
	private String dMarryFlag;
	private String dWorkType;
	private String remark1;
	private String remark2;
	private String remark3;

	public String getdWorkType() {
		return dWorkType;
	}

	public void setdWorkType(String dWorkType) {
		this.dWorkType = dWorkType;
	}

	public float getlMixBuyMoneyResp() {
		return lMixBuyMoneyResp;
	}

	public void setlMixBuyMoneyResp(float lMixBuyMoneyResp) {
		this.lMixBuyMoneyResp = lMixBuyMoneyResp;
	}

	public String getOrigPriceStr() {
		return origPriceStr;
	}

	public void setOrigPriceStr(String origPriceStr) {
		this.origPriceStr = origPriceStr;
	}

	public String getCurrPriceStr() {
		return currPriceStr;
	}

	public void setCurrPriceStr(String currPriceStr) {
		this.currPriceStr = currPriceStr;
	}

	public String getCommissionStr() {
		return commissionStr;
	}

	public void setCommissionStr(String commissionStr) {
		this.commissionStr = commissionStr;
	}

	public void setCurrPrice(Long currPrice) {
		this.currPrice = currPrice;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
	}

	public Float getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(Float commissionRate) {
		this.commissionRate = commissionRate;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public Long getOrigPrice() {
		return origPrice;
	}

	public void setOrigPrice(Long origPrice) {
		this.origPrice = origPrice;
	}

	public Long getSoldQuantity() {
		return soldQuantity;
	}

	public void setSoldQuantity(Long soldQuantity) {
		this.soldQuantity = soldQuantity;
	}

	public Long getTockQuantity() {
		return tockQuantity;
	}

	public void setTockQuantity(Long tockQuantity) {
		this.tockQuantity = tockQuantity;
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

	public String getProductDescript() {
		return productDescript;
	}

	public void setProductDescript(String productDescript) {
		this.productDescript = productDescript;
	}

	public String getHandleId() {
		return handleId;
	}

	public Long getCurrPrice() {
		return currPrice;
	}

	public Long getCommission() {
		return commission;
	}

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public Integer getlNumberOfPerson() {
		return lNumberOfPerson;
	}

	public void setlNumberOfPerson(Integer lNumberOfPerson) {
		this.lNumberOfPerson = lNumberOfPerson;
	}

	public Long getlMixBuyMoney() {
		return lMixBuyMoney;
	}

	public void setlMixBuyMoney(Long lMixBuyMoney) {
		this.lMixBuyMoney = lMixBuyMoney;
	}

	public Integer getlRiskGrade() {
		return lRiskGrade;
	}

	public void setlRiskGrade(Integer lRiskGrade) {
		this.lRiskGrade = lRiskGrade;
	}

	public Integer getlInvestDays() {
		return lInvestDays;
	}

	public void setlInvestDays(Integer lInvestDays) {
		this.lInvestDays = lInvestDays;
	}

	public Long getlGoalIncome() {
		return lGoalIncome;
	}

	public void setlGoalIncome(Long lGoalIncome) {
		this.lGoalIncome = lGoalIncome;
	}

	public Long getdMoney() {
		return dMoney;
	}

	public void setdMoney(Long dMoney) {
		this.dMoney = dMoney;
	}

	public String getdIncome() {
		return dIncome;
	}

	public void setdIncome(String dIncome) {
		this.dIncome = dIncome;
	}

	public String getdCarFlag() {
		return dCarFlag;
	}

	public void setdCarFlag(String dCarFlag) {
		this.dCarFlag = dCarFlag;
	}

	public String getdSocialFlag() {
		return dSocialFlag;
	}

	public void setdSocialFlag(String dSocialFlag) {
		this.dSocialFlag = dSocialFlag;
	}

	public String getdHouseFlag() {
		return dHouseFlag;
	}

	public void setdHouseFlag(String dHouseFlag) {
		this.dHouseFlag = dHouseFlag;
	}

	public String getdMarryFlag() {
		return dMarryFlag;
	}

	public void setdMarryFlag(String dMarryFlag) {
		this.dMarryFlag = dMarryFlag;
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
		return this.productId;
	}
}
