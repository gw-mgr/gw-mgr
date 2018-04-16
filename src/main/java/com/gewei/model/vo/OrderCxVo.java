package com.gewei.model.vo;

import com.gewei.commons.utils.JsonUtils;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 财产保险订单表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-21
 */
public class OrderCxVo implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private String orderId;
	/**
	 * 下单日期
	 */
	private String createTime;
	/**
	 * 产险类型，见表t_merchant_product_category
	 */
	private String orderType;
	private String orderTypeName;
	/**
	 * 服务商编号，见表t_merchant_manage
	 */
	private String merchantId;
	/**
	 * 保单号
	 */
	private String orderNo;
	/**
	 * 最后修改时间
	 */
	private String updateTime;
	/**
	 * 最后修改人
	 */
	private String updateUser;
	/**
	 * 是否成交（01未成交、02成交、03删除），默认01
	 */
	private String orderFlag;
	/**
	 * 礼品是否发放（01未发放、02已发放），默认01
	 */
	private String giftFlag;
	/**
	 * 佣金支付状态（01未发放、02已发放），默认01
	 */
	private String commissionFlag;
	/**
	 * 是否打单（01未打印、02已打印），默认01
	 */
	private String printFlag;
	/**
	 * 保险公司是否结账（01否、02是），默认01
	 */
	private String checkoutFlag;
	/**
	 * 打单人
	 */
	private String ddr;
	/**
	 * 打单日期
	 */
	private String ddDate;
	/**
	 * 保险公司交强险税前佣金比例
	 */
	private BigDecimal bxgssqjqyjbl;
	/**
	 * 保险公司税前佣金比例
	 */
	private BigDecimal bxgssqyjbl;
	/**
	 * 保险公司交强险税前佣金
	 */
	private BigDecimal bxgssqjqyj;
	/**
	 * 保险公司税前佣金比例
	 */
	private BigDecimal bxgssqyj;
	/**
	 * 保险公司开票佣金比例
	 */
	private BigDecimal bxgskpyjbl;
	/**
	 * 保险公司开票佣金
	 */
	private BigDecimal bxgskpyj;
	/**
	 * 公司管理费比例
	 */
	private BigDecimal gsglfbl;
	/**
	 * 公司管理费金额
	 */
	private BigDecimal gsglfje;
	/**
	 * 公司交税比例
	 */
	private BigDecimal gsjsbl;
	/**
	 * 公司交税金额
	 */
	private BigDecimal gsjsje;
	/**
	 * 公司提成比例
	 */
	private BigDecimal gstcbl;
	/**
	 * 公司提成金额
	 */
	private BigDecimal gstcje;
	/**
	 * 本公司毛利
	 */
	private BigDecimal bgsml;
	/**
	 * 车牌号
	 */
	private String carNum;
	/**
	 * 车架号
	 */
	private String carFrameNum;
	/**
	 * 发动机号
	 */
	private String engineNum;
	/**
	 * 厂牌类型
	 */
	private String changType;
	/**
	 * 初登日期
	 */
	private String registerTime;
	/**
	 * 年审到期日期,是车辆上户当月的最后一天（不考虑2年或者1年，全部按照1年显示）每月1号，退送当月的车（年审到期的车）
	 */
	private String examineTime;
	/**
	 * 商业险起保日
	 */
	private String businessInsStartTime;
	/**
	 * 车辆类型（01家庭自用车、02公司自用车、03党机关用车、04非营运货车、05工程机械、06摩托车、07特一、08特二、09特三、10特四、11营运货车、12营运客车），默认01
	 */
	private String carType;
	/**
	 * 交强险起保日
	 */
	private String trafficInsStartTime;
	/**
	 * 车辆备注
	 */
	private String carRemark;
	/**
	 * 车主
	 */
	private String carOwner;
	/**
	 * 车主证件类型 01身份证 02毕业证 03出生证 04独生子女证 05护照 06驾驶证 07结婚证 08警官证 09离婚证 10签证 11学生证 12执行公务证
	 */
	private String ownerCardType;
	/**
	 * 车主证件号
	 */
	private String ownerCardId;
	/**
	 * 投保人ID
	 */
	private String policyholderId;
	/**
	 * 投保人
	 */
	private String policyholder;
	/**
	 * 投保人证件类型 01身份证 02毕业证 03出生证 04独生子女证 05护照 06驾驶证 07结婚证 08警官证 09离婚证 10签证 11学生证 12执行公务证
	 */
	private String tbrCardType;
	/**
	 * 投保人证件号
	 */
	private String tbrCardId;
	/**
	 * 被保险人
	 */
	private String beneficiary;
	/**
	 * 被保险人ID
	 */
	private String beneficiaryId;
	/**
	 * 被保险人证件类型 01身份证 02毕业证 03出生证 04独生子女证 05护照 06驾驶证 07结婚证 08警官证 09离婚证 10签证 11学生证 12执行公务证
	 */
	private String bbrCardType;
	/**
	 * 被保险人证件号
	 */
	private String bbrCardId;
	/**
	 * 电话
	 */
	private String telphone;
	/**
	 * 手机
	 */
	private String mtelphone;
	/**
	 * Mail
	 */
	private String mail;
	/**
	 * qq
	 */
	private String qq;
	/**
	 * 区县
	 */
	private String district;
	/**
	 * 城市
	 */
	private String city;
	/**
	 * 省/直辖市
	 */
	private String province;
	/**
	 * 地址
	 */
	private String linkaddr;
	/**
	 * 车辆备注
	 */
	private String ownerRemark;
	/**
	 * 保险公司编号
	 */
	private String insuranceCompanyId;
	/**
	 * 保险公司名称
	 */
	private String insuranceCompany;
	/**
	 * 保险公司报案电话
	 */
	private String insuranceCompanyTel;
	/**
	 * 出单渠道（操作人）
	 */
	private String operator;
	private String operatorName;
	/**
	 * 投保方式
	 */
	private String tbfs;
	/**
	 * 净商业险
	 */
	private BigDecimal jsyx;
	/**
	 * 商业险税
	 */
	private BigDecimal syxs;
	/**
	 * 总商业险
	 */
	private BigDecimal zsyx;
	/**
	 * 净交强险
	 */
	private BigDecimal jjqx;
	/**
	 * 交强税
	 */
	private BigDecimal jqxs;
	/**
	 * 总交强
	 */
	private BigDecimal zjq;
	/**
	 * 车船税
	 */
	private BigDecimal ccs;
	/**
	 * 总保费
	 */
	private BigDecimal zbf;
	/**
	 * 业务员
	 */
	private String salesMan;
	private String salesManName;
	/**
	 * 佣金支付对象（01业务员、02录单员）
	 */
	private String yjzfdx;
	/**
	 * 商业险佣金比例
	 */
	private BigDecimal syxyjbl;
	/**
	 * 商业险佣金
	 */
	private BigDecimal syxyj;
	/**
	 * 综合金融奖比例
	 */
	private BigDecimal zhjrjbl;
	/**
	 * 综合金融奖金额
	 */
	private BigDecimal zhjrjje;
	/**
	 * 交强险佣金比例
	 */
	private BigDecimal jjxyjbl;
	/**
	 * 交强险佣金
	 */
	private BigDecimal jjxyj;
	/**
	 * 车船税佣金比例
	 */
	private BigDecimal ccsyjbl;
	/**
	 * 车船税佣金
	 */
	private BigDecimal ccsyj;
	/**
	 * 保险公司短期激励
	 */
	private BigDecimal bxgsdqjl;
	/**
	 * 是否有礼品（01否、02是），默认01
	 */
	private String giftSf;
	/**
	 * 礼品内容
	 */
	private String giftComment;
	/**
	 * 实际支付佣金
	 */
	private BigDecimal sjzfyj;
	/**
	 * 佣金支付时间
	 */
	private String yjzfsj;
	/**
	 * 佣金支付人
	 */
	private String yjzfr;
	/**
	 * 录单员
	 */
	private String recorder;
	/**
	 * 录单员ID
	 */
	private String recorderid;
	/**
	 * 录单员佣金
	 */
	private BigDecimal recorderyj;
	/**
	 * 机动车损失险保额
	 */
	private BigDecimal jdcssxbe;
	/**
	 * 机动车损失险保费
	 */
	private BigDecimal jdcssxbf;
	/**
	 * 第三责任险保额
	 */
	private BigDecimal dszrxbe;
	/**
	 * 第三责任险保费
	 */
	private BigDecimal dszrxbf;
	/**
	 * 全车盗抢险
	 */
	private BigDecimal qcdqx;
	/**
	 * 司机责任险保额(万元)
	 */
	private BigDecimal sjzrxbe;
	/**
	 * 司机责任险保费
	 */
	private BigDecimal sjzrxbf;
	/**
	 * 乘客责任险保额(万元)
	 */
	private BigDecimal ckzrxbe;
	/**
	 * 乘客责任险保费
	 */
	private BigDecimal ckzrxbf;
	/**
	 * 玻璃单独破碎险
	 */
	private BigDecimal blddpsx;
	/**
	 * 自燃损失险
	 */
	private BigDecimal zrssx;
	/**
	 * 车身划痕险
	 */
	private BigDecimal cshhx;
	/**
	 * 发动车涉水损失险
	 */
	private BigDecimal fdcssssx;
	/**
	 * 机动车损失无法找到第三方特约险
	 */
	private BigDecimal jdcsstyx;
	/**
	 * 不计免赔
	 */
	private BigDecimal bjmp;
	/**
	 * 备注
	 */
	private String remark;
	private String salesTelphone;
	private String wechat;
	private String salesLinkaddr;
	private String bankName;
	private String bankUserName;
	private String bankNo;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public String getCommissionFlag() {
		return commissionFlag;
	}

	public void setCommissionFlag(String commissionFlag) {
		this.commissionFlag = commissionFlag;
	}

	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getCheckoutFlag() {
		return checkoutFlag;
	}

	public void setCheckoutFlag(String checkoutFlag) {
		this.checkoutFlag = checkoutFlag;
	}

	public String getDdr() {
		return ddr;
	}

	public void setDdr(String ddr) {
		this.ddr = ddr;
	}

	public String getDdDate() {
		return ddDate;
	}

	public void setDdDate(String ddDate) {
		this.ddDate = ddDate;
	}

	public BigDecimal getBxgssqyjbl() {
		return bxgssqyjbl;
	}

	public void setBxgssqyjbl(BigDecimal bxgssqyjbl) {
		this.bxgssqyjbl = bxgssqyjbl;
	}

	public BigDecimal getBxgssqyj() {
		return bxgssqyj;
	}

	public void setBxgssqyj(BigDecimal bxgssqyj) {
		this.bxgssqyj = bxgssqyj;
	}

	public BigDecimal getBxgskpyjbl() {
		return bxgskpyjbl;
	}

	public void setBxgskpyjbl(BigDecimal bxgskpyjbl) {
		this.bxgskpyjbl = bxgskpyjbl;
	}

	public BigDecimal getBxgskpyj() {
		return bxgskpyj;
	}

	public void setBxgskpyj(BigDecimal bxgskpyj) {
		this.bxgskpyj = bxgskpyj;
	}

	public BigDecimal getGsglfbl() {
		return gsglfbl;
	}

	public void setGsglfbl(BigDecimal gsglfbl) {
		this.gsglfbl = gsglfbl;
	}

	public BigDecimal getGsglfje() {
		return gsglfje;
	}

	public void setGsglfje(BigDecimal gsglfje) {
		this.gsglfje = gsglfje;
	}

	public BigDecimal getGsjsbl() {
		return gsjsbl;
	}

	public void setGsjsbl(BigDecimal gsjsbl) {
		this.gsjsbl = gsjsbl;
	}

	public BigDecimal getGsjsje() {
		return gsjsje;
	}

	public void setGsjsje(BigDecimal gsjsje) {
		this.gsjsje = gsjsje;
	}

	public BigDecimal getGstcbl() {
		return gstcbl;
	}

	public void setGstcbl(BigDecimal gstcbl) {
		this.gstcbl = gstcbl;
	}

	public BigDecimal getGstcje() {
		return gstcje;
	}

	public void setGstcje(BigDecimal gstcje) {
		this.gstcje = gstcje;
	}

	public BigDecimal getBgsml() {
		return bgsml;
	}

	public void setBgsml(BigDecimal bgsml) {
		this.bgsml = bgsml;
	}

	public String getCarNum() {
		return carNum;
	}

	public void setCarNum(String carNum) {
		this.carNum = carNum;
	}

	public String getCarFrameNum() {
		return carFrameNum;
	}

	public void setCarFrameNum(String carFrameNum) {
		this.carFrameNum = carFrameNum;
	}

	public String getEngineNum() {
		return engineNum;
	}

	public void setEngineNum(String engineNum) {
		this.engineNum = engineNum;
	}

	public String getChangType() {
		return changType;
	}

	public void setChangType(String changType) {
		this.changType = changType;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	public String getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(String examineTime) {
		this.examineTime = examineTime;
	}

	public String getBusinessInsStartTime() {
		return businessInsStartTime;
	}

	public void setBusinessInsStartTime(String businessInsStartTime) {
		this.businessInsStartTime = businessInsStartTime;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public String getTrafficInsStartTime() {
		return trafficInsStartTime;
	}

	public void setTrafficInsStartTime(String trafficInsStartTime) {
		this.trafficInsStartTime = trafficInsStartTime;
	}

	public String getCarRemark() {
		return carRemark;
	}

	public void setCarRemark(String carRemark) {
		this.carRemark = carRemark;
	}

	public String getCarOwner() {
		return carOwner;
	}

	public void setCarOwner(String carOwner) {
		this.carOwner = carOwner;
	}

	public String getOwnerCardType() {
		return ownerCardType;
	}

	public void setOwnerCardType(String ownerCardType) {
		this.ownerCardType = ownerCardType;
	}

	public String getOwnerCardId() {
		return ownerCardId;
	}

	public void setOwnerCardId(String ownerCardId) {
		this.ownerCardId = ownerCardId;
	}

	public String getPolicyholderId() {
		return policyholderId;
	}

	public void setPolicyholderId(String policyholderId) {
		this.policyholderId = policyholderId;
	}

	public String getPolicyholder() {
		return policyholder;
	}

	public void setPolicyholder(String policyholder) {
		this.policyholder = policyholder;
	}

	public String getTbrCardType() {
		return tbrCardType;
	}

	public void setTbrCardType(String tbrCardType) {
		this.tbrCardType = tbrCardType;
	}

	public String getTbrCardId() {
		return tbrCardId;
	}

	public void setTbrCardId(String tbrCardId) {
		this.tbrCardId = tbrCardId;
	}

	public String getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(String beneficiary) {
		this.beneficiary = beneficiary;
	}

	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getBbrCardType() {
		return bbrCardType;
	}

	public void setBbrCardType(String bbrCardType) {
		this.bbrCardType = bbrCardType;
	}

	public String getBbrCardId() {
		return bbrCardId;
	}

	public void setBbrCardId(String bbrCardId) {
		this.bbrCardId = bbrCardId;
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

	public String getOwnerRemark() {
		return ownerRemark;
	}

	public void setOwnerRemark(String ownerRemark) {
		this.ownerRemark = ownerRemark;
	}

	public String getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(String insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public String getInsuranceCompany() {
		return insuranceCompany;
	}

	public void setInsuranceCompany(String insuranceCompany) {
		this.insuranceCompany = insuranceCompany;
	}

	public String getInsuranceCompanyTel() {
		return insuranceCompanyTel;
	}

	public void setInsuranceCompanyTel(String insuranceCompanyTel) {
		this.insuranceCompanyTel = insuranceCompanyTel;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTbfs() {
		return tbfs;
	}

	public void setTbfs(String tbfs) {
		this.tbfs = tbfs;
	}

	public BigDecimal getJsyx() {
		return jsyx;
	}

	public void setJsyx(BigDecimal jsyx) {
		this.jsyx = jsyx;
	}

	public BigDecimal getSyxs() {
		return syxs;
	}

	public void setSyxs(BigDecimal syxs) {
		this.syxs = syxs;
	}

	public BigDecimal getZsyx() {
		return zsyx;
	}

	public void setZsyx(BigDecimal zsyx) {
		this.zsyx = zsyx;
	}

	public BigDecimal getJjqx() {
		return jjqx;
	}

	public void setJjqx(BigDecimal jjqx) {
		this.jjqx = jjqx;
	}

	public BigDecimal getJqxs() {
		return jqxs;
	}

	public void setJqxs(BigDecimal jqxs) {
		this.jqxs = jqxs;
	}

	public BigDecimal getZjq() {
		return zjq;
	}

	public void setZjq(BigDecimal zjq) {
		this.zjq = zjq;
	}

	public BigDecimal getCcs() {
		return ccs;
	}

	public void setCcs(BigDecimal ccs) {
		this.ccs = ccs;
	}

	public BigDecimal getZbf() {
		return zbf;
	}

	public void setZbf(BigDecimal zbf) {
		this.zbf = zbf;
	}

	public String getSalesMan() {
		return salesMan;
	}

	public void setSalesMan(String salesMan) {
		this.salesMan = salesMan;
	}

	public String getYjzfdx() {
		return yjzfdx;
	}

	public void setYjzfdx(String yjzfdx) {
		this.yjzfdx = yjzfdx;
	}

	public BigDecimal getSyxyjbl() {
		return syxyjbl;
	}

	public void setSyxyjbl(BigDecimal syxyjbl) {
		this.syxyjbl = syxyjbl;
	}

	public BigDecimal getSyxyj() {
		return syxyj;
	}

	public void setSyxyj(BigDecimal syxyj) {
		this.syxyj = syxyj;
	}

	public BigDecimal getZhjrjbl() {
		return zhjrjbl;
	}

	public void setZhjrjbl(BigDecimal zhjrjbl) {
		this.zhjrjbl = zhjrjbl;
	}

	public BigDecimal getZhjrjje() {
		return zhjrjje;
	}

	public void setZhjrjje(BigDecimal zhjrjje) {
		this.zhjrjje = zhjrjje;
	}

	public BigDecimal getJjxyjbl() {
		return jjxyjbl;
	}

	public void setJjxyjbl(BigDecimal jjxyjbl) {
		this.jjxyjbl = jjxyjbl;
	}

	public BigDecimal getJjxyj() {
		return jjxyj;
	}

	public void setJjxyj(BigDecimal jjxyj) {
		this.jjxyj = jjxyj;
	}

	public BigDecimal getCcsyjbl() {
		return ccsyjbl;
	}

	public void setCcsyjbl(BigDecimal ccsyjbl) {
		this.ccsyjbl = ccsyjbl;
	}

	public BigDecimal getCcsyj() {
		return ccsyj;
	}

	public void setCcsyj(BigDecimal ccsyj) {
		this.ccsyj = ccsyj;
	}

	public BigDecimal getBxgsdqjl() {
		return bxgsdqjl;
	}

	public void setBxgsdqjl(BigDecimal bxgsdqjl) {
		this.bxgsdqjl = bxgsdqjl;
	}

	public String getGiftSf() {
		return giftSf;
	}

	public void setGiftSf(String giftSf) {
		this.giftSf = giftSf;
	}

	public String getGiftComment() {
		return giftComment;
	}

	public void setGiftComment(String giftComment) {
		this.giftComment = giftComment;
	}

	public BigDecimal getSjzfyj() {
		return sjzfyj;
	}

	public void setSjzfyj(BigDecimal sjzfyj) {
		this.sjzfyj = sjzfyj;
	}

	public String getYjzfsj() {
		return yjzfsj;
	}

	public void setYjzfsj(String yjzfsj) {
		this.yjzfsj = yjzfsj;
	}

	public String getYjzfr() {
		return yjzfr;
	}

	public void setYjzfr(String yjzfr) {
		this.yjzfr = yjzfr;
	}

	public String getRecorder() {
		return recorder;
	}

	public void setRecorder(String recorder) {
		this.recorder = recorder;
	}

	public String getRecorderid() {
		return recorderid;
	}

	public void setRecorderid(String recorderid) {
		this.recorderid = recorderid;
	}

	public BigDecimal getRecorderyj() {
		return recorderyj;
	}

	public void setRecorderyj(BigDecimal recorderyj) {
		this.recorderyj = recorderyj;
	}

	public BigDecimal getJdcssxbe() {
		return jdcssxbe;
	}

	public void setJdcssxbe(BigDecimal jdcssxbe) {
		this.jdcssxbe = jdcssxbe;
	}

	public BigDecimal getJdcssxbf() {
		return jdcssxbf;
	}

	public void setJdcssxbf(BigDecimal jdcssxbf) {
		this.jdcssxbf = jdcssxbf;
	}

	public BigDecimal getDszrxbe() {
		return dszrxbe;
	}

	public void setDszrxbe(BigDecimal dszrxbe) {
		this.dszrxbe = dszrxbe;
	}

	public BigDecimal getDszrxbf() {
		return dszrxbf;
	}

	public void setDszrxbf(BigDecimal dszrxbf) {
		this.dszrxbf = dszrxbf;
	}

	public BigDecimal getQcdqx() {
		return qcdqx;
	}

	public void setQcdqx(BigDecimal qcdqx) {
		this.qcdqx = qcdqx;
	}

	public BigDecimal getSjzrxbe() {
		return sjzrxbe;
	}

	public void setSjzrxbe(BigDecimal sjzrxbe) {
		this.sjzrxbe = sjzrxbe;
	}

	public BigDecimal getSjzrxbf() {
		return sjzrxbf;
	}

	public void setSjzrxbf(BigDecimal sjzrxbf) {
		this.sjzrxbf = sjzrxbf;
	}

	public BigDecimal getCkzrxbe() {
		return ckzrxbe;
	}

	public void setCkzrxbe(BigDecimal ckzrxbe) {
		this.ckzrxbe = ckzrxbe;
	}

	public BigDecimal getCkzrxbf() {
		return ckzrxbf;
	}

	public void setCkzrxbf(BigDecimal ckzrxbf) {
		this.ckzrxbf = ckzrxbf;
	}

	public BigDecimal getBlddpsx() {
		return blddpsx;
	}

	public void setBlddpsx(BigDecimal blddpsx) {
		this.blddpsx = blddpsx;
	}

	public BigDecimal getZrssx() {
		return zrssx;
	}

	public void setZrssx(BigDecimal zrssx) {
		this.zrssx = zrssx;
	}

	public BigDecimal getCshhx() {
		return cshhx;
	}

	public void setCshhx(BigDecimal cshhx) {
		this.cshhx = cshhx;
	}

	public BigDecimal getFdcssssx() {
		return fdcssssx;
	}

	public void setFdcssssx(BigDecimal fdcssssx) {
		this.fdcssssx = fdcssssx;
	}

	public BigDecimal getJdcsstyx() {
		return jdcsstyx;
	}

	public void setJdcsstyx(BigDecimal jdcsstyx) {
		this.jdcsstyx = jdcsstyx;
	}

	public BigDecimal getBjmp() {
		return bjmp;
	}

	public void setBjmp(BigDecimal bjmp) {
		this.bjmp = bjmp;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getSalesManName() {
		return salesManName;
	}

	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}

	public BigDecimal getBxgssqjqyjbl() {
		return bxgssqjqyjbl;
	}

	public void setBxgssqjqyjbl(BigDecimal bxgssqjqyjbl) {
		this.bxgssqjqyjbl = bxgssqjqyjbl;
	}

	public BigDecimal getBxgssqjqyj() {
		return bxgssqjqyj;
	}

	public String getSalesTelphone() {
		return salesTelphone;
	}

	public void setSalesTelphone(String salesTelphone) {
		this.salesTelphone = salesTelphone;
	}

	public String getWechat() {
		return wechat;
	}

	public void setWechat(String wechat) {
		this.wechat = wechat;
	}

	public String getSalesLinkaddr() {
		return salesLinkaddr;
	}

	public void setSalesLinkaddr(String salesLinkaddr) {
		this.salesLinkaddr = salesLinkaddr;
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

	public void setBxgssqjqyj(BigDecimal bxgssqjqyj) {
		this.bxgssqjqyj = bxgssqjqyj;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
