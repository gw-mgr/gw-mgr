package com.gewei.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 产品基本信息表，C_：汽车产品，L_：理财产品，D_：贷款产品
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-08
 */
@TableName("t_product_manage")
public class TProductManage extends Model<TProductManage> {
	private static final long serialVersionUID = 1L;
	/**
	 * 业务主键（32位UUID，平台唯一）
	 */
	private String productId;
	/**
	 * 产品名称
	 */
	private String productName;
	/**
	 * 数据状态（00删除，01上架/启用，02下架/停用，03强制下架），默认02
	 */
	private String status;
	/**
	 * 产品类型，参见产品类型表t_merchant_product_category
	 */
	private String productType;
	/**
	 * 产品所属服务商编号
	 */
	private String merchantId;
	/**
	 * 当前价格
	 */
	private Long currPrice;
	/**
	 * 原始价格
	 */
	private Long origPrice;
	/**
	 * 产品已经售卖的数量
	 */
	private Long soldQuantity;
	/**
	 * 产品的库存数量
	 */
	private Long tockQuantity;
	/**
	 * 产品创建时间
	 */
	private Long createTime;
	/**
	 * 产品修改时间
	 */
	private Long updateTime;
	/**
	 * 产品详细描述信息
	 */
	private String productDescript;
	/**
	 * 操作人用户编号
	 */
	private String handleId;
	/**
	 * 平台佣金
	 */
	private Long commission;
	private Float commissionRate;
	/**
	 * 购买人数
	 */
	private Integer lNumberOfPerson;
	/**
	 * 起购金额，单位：分
	 */
	private Long lMixBuyMoney;
	/**
	 * 风险等级，共五级风险
	 */
	private Integer lRiskGrade;
	/**
	 * 投资期限（天）
	 */
	private Integer lInvestDays;
	/**
	 * 目标年化收益，单位：分
	 */
	private Long lGoalIncome;
	/**
	 * 贷款金额，单位：分
	 */
	private Long dMoney;
	/**
	 * 月收入，如：2000-3000
	 */
	private String dIncome;
	/**
	 * 有无车辆（0-无，1-按揭，2-全款）
	 */
	private String dCarFlag;
	/**
	 * 有无社保（0-无，1-有）
	 */
	private String dSocialFlag;
	/**
	 * 有无房产（0-无，1-按揭，2-全款）
	 */
	private String dHouseFlag;
	/**
	 * 婚姻状况（0-未婚，1-已婚，2-离异）
	 */
	private String dMarryFlag;
	private String dWorkType;

	public String getdWorkType() {
		return dWorkType;
	}

	public void setdWorkType(String dWorkType) {
		this.dWorkType = dWorkType;
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

	public Long getCurrPrice() {
		return currPrice;
	}

	public void setCurrPrice(Long currPrice) {
		this.currPrice = currPrice;
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

	public void setHandleId(String handleId) {
		this.handleId = handleId;
	}

	public Long getCommission() {
		return commission;
	}

	public void setCommission(Long commission) {
		this.commission = commission;
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
