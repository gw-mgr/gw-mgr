package com.gewei.model.excel;


import java.math.BigDecimal;

import com.gewei.commons.report.excel.ExcelField;
import com.gewei.commons.utils.JsonUtils;
/**
 * 财险订单导出信息
 * @author git
 *
 */
public class OrderCxExcel{

	@ExcelField("创建时间")
	private String createTime;
	@ExcelField("订单类型")
	private String orderType;
	@ExcelField("操作人")
	private String operator;
	@ExcelField("订单号")
	private String orderNo;
	@ExcelField("投保开始时间")
	private String tbStartTime;
	@ExcelField("投保结束时间")
	private String tbEndTime;
	@ExcelField("最后更新时间")
	private String updateTime;
	@ExcelField("最后更新人")
	private String updateUser;
	@ExcelField("订单状态")
	private String orderFlag;
	@ExcelField("礼品状态")
	private String giftFlag;
	@ExcelField("佣金支付状态")
	private String commissionFlag;
	@ExcelField("是否打印")
	private String printFlag;
	@ExcelField("是否结账")
	private String checkoutFlag;
	@ExcelField("打单人")
	private String ddr;
	@ExcelField("打单时间")
	private String ddDate;
	@ExcelField("保险公司交强险税前佣金比例")
	private BigDecimal bxgssqjqyjbl;
	@ExcelField("保险公司税前佣金比例")
	private BigDecimal bxgssqyjbl;
	@ExcelField("保险公司交强险税前佣金")
	private BigDecimal bxgssqjqyj;
	@ExcelField("保险公司税前佣金")
	private BigDecimal bxgssqyj;
	@ExcelField("保险公司开票佣金比例")
	private BigDecimal bxgskpyjbl;
	@ExcelField("保险公司开票佣金")
	private BigDecimal bxgskpyj;
	@ExcelField("公司管理费比例")
	private BigDecimal gsglfbl;
	@ExcelField("公司管理费金额")
	private BigDecimal gsglfje;
	@ExcelField("公司交税比例")
	private BigDecimal gsjsbl;
	@ExcelField("公司交税金额")
	private BigDecimal gsjsje;
	@ExcelField("公司提成比例")
	private BigDecimal gstcbl;
	@ExcelField("公司提成金额")
	private BigDecimal gstcje;
	@ExcelField("本公司毛利")
	private BigDecimal bgsml;
	@ExcelField("车牌号")
	private String carNum;
	@ExcelField("车架号")
	private String carFrameNum;
	@ExcelField("发动机号")
	private String engineNum;
	@ExcelField("厂牌类型")
	private String changType;
	@ExcelField("初登日期")
	private String registerTime;
	@ExcelField("年审到期日")
	private String examineTime;
	@ExcelField("商业险起保日")
	private String businessInsStartTime;
	@ExcelField("车辆类型")
	private String carType;
	@ExcelField("交强险起保日")
	private String trafficInsStartTime;
	@ExcelField("车辆备注")
	private String carRemark;
	@ExcelField("车主")
	private String carOwner;
	@ExcelField("车主证件类型")
	private String ownerCardType;
	@ExcelField("车主证件号")
	private String ownerCardId;
	@ExcelField("投保人ID")
	private String policyholderId;
	@ExcelField("投保人")
	private String policyholder;
	@ExcelField("投保人证件类型")
	private String tbrCardType;
	@ExcelField("投保人证件号码")
	private String tbrCardId;
	@ExcelField("被保险人")
	private String beneficiary;
	@ExcelField("被保险人性别")
	private String bbrSex;
	@ExcelField("被保险人年龄")
	private Integer bbrAge;
	@ExcelField("被保险人证件类型")
	private String bbrCardType;
	@ExcelField("被保险人证件号码")
	private String bbrCardId;
	@ExcelField("电话")
	private String telphone;
	@ExcelField("手机号")
	private String mtelphone;
	@ExcelField("邮箱")
	private String mail;
	@ExcelField("QQ")
	private String qq;
	@ExcelField("区县")
	private String district;
	@ExcelField("市")
	private String city;
	@ExcelField("省/直辖市")
	private String province;
	@ExcelField("具体地址")
	private String linkaddr;
	@ExcelField("车辆备注")
	private String ownerRemark;
	@ExcelField("保险公司")
	private String insuranceCompany;
	@ExcelField("保险公司电话")
	private String insuranceCompanyTel;
	@ExcelField("投保单位")
	private String tbdw;
	@ExcelField("投保方式")
	private String tbfs;
	@ExcelField("净商业险")
	private BigDecimal jsyx;
	@ExcelField("商业险税")
	private BigDecimal syxs;
	@ExcelField("总商业险")
	private BigDecimal zsyx;
	@ExcelField("净交强险")
	private BigDecimal jjqx;
	@ExcelField("交强险税")
	private BigDecimal jqxs;
	@ExcelField("总交强")
	private BigDecimal zjq;
	@ExcelField("车船税")
	private BigDecimal ccs;
	@ExcelField("总保费")
	private BigDecimal zbf;
	@ExcelField("业务员")
	private String salesMan;
	@ExcelField("佣金支付对象")
	private String yjzfdx;
	@ExcelField("商业险佣金比例")
	private BigDecimal syxyjbl;
	@ExcelField("商业险佣金")
	private BigDecimal syxyj;
	@ExcelField("综合金融奖比例")
	private BigDecimal zhjrjbl;
	@ExcelField("综合金融将金额")
	private BigDecimal zhjrjje;
	@ExcelField("交强险佣金比例")
	private BigDecimal jjxyjbl;
	@ExcelField("交强险佣金")
	private BigDecimal jjxyj;
	@ExcelField("车船税佣金比例")
	private BigDecimal ccsyjbl;
	@ExcelField("车船税佣金")
	private BigDecimal ccsyj;
	@ExcelField("保险公司短期激励")
	private BigDecimal bxgsdqjl;
	@ExcelField("是否有礼品")
	private String giftSf;
	@ExcelField("礼品内容")
	private String giftComment;
	@ExcelField("实际支付佣金")
	private BigDecimal sjzfyj;
	@ExcelField("佣金支付时间")
	private String yjzfsj;
	@ExcelField("佣金支付人")
	private String yjzfr;
	@ExcelField("录单员")
	private String recorder;
	@ExcelField("录单员编号")
	private String recorderid;
	@ExcelField("录单员佣金")
	private BigDecimal recorderyj;
	@ExcelField("机动车损失险保额")
	private BigDecimal jdcssxbe;
	@ExcelField("机动车损失险保费")
	private BigDecimal jdcssxbf;
	@ExcelField("第三责任险保额")
	private BigDecimal dszrxbe;
	@ExcelField("第三责任险保费")
	private BigDecimal dszrxbf;
	@ExcelField("全车盗抢险")
	private BigDecimal qcdqx;
	@ExcelField("司机责任险保额")
	private BigDecimal sjzrxbe;
	@ExcelField("司机责任险保费")
	private BigDecimal sjzrxbf;
	@ExcelField("乘客责任险保额")
	private BigDecimal ckzrxbe;
	@ExcelField("乘客责任险保费")
	private BigDecimal ckzrxbf;
	@ExcelField("玻璃单独破碎险")
	private BigDecimal blddpsx;
	@ExcelField("自燃损失险")
	private BigDecimal zrssx;
	@ExcelField("车身划痕险")
	private BigDecimal cshhx;
	@ExcelField("发动车涉水损失险")
	private BigDecimal fdcssssx;
	@ExcelField("机动车损失无法找到第三方特约险")
	private BigDecimal jdcsstyx;
	@ExcelField("不计免赔")
	private BigDecimal bjmp;
	@ExcelField("操作员")
	private String operatorName;
	@ExcelField("业务员")
	private String salesManName;

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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTbStartTime() {
		return tbStartTime;
	}

	public void setTbStartTime(String tbStartTime) {
		this.tbStartTime = tbStartTime;
	}

	public String getTbEndTime() {
		return tbEndTime;
	}

	public void setTbEndTime(String tbEndTime) {
		this.tbEndTime = tbEndTime;
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

	public BigDecimal getBxgssqjqyjbl() {
		return bxgssqjqyjbl;
	}

	public void setBxgssqjqyjbl(BigDecimal bxgssqjqyjbl) {
		this.bxgssqjqyjbl = bxgssqjqyjbl;
	}

	public BigDecimal getBxgssqyjbl() {
		return bxgssqyjbl;
	}

	public void setBxgssqyjbl(BigDecimal bxgssqyjbl) {
		this.bxgssqyjbl = bxgssqyjbl;
	}

	public BigDecimal getBxgssqjqyj() {
		return bxgssqjqyj;
	}

	public void setBxgssqjqyj(BigDecimal bxgssqjqyj) {
		this.bxgssqjqyj = bxgssqjqyj;
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

	public String getBbrSex() {
		return bbrSex;
	}

	public void setBbrSex(String bbrSex) {
		this.bbrSex = bbrSex;
	}

	public Integer getBbrAge() {
		return bbrAge;
	}

	public void setBbrAge(Integer bbrAge) {
		this.bbrAge = bbrAge;
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

	public String getTbdw() {
		return tbdw;
	}

	public void setTbdw(String tbdw) {
		this.tbdw = tbdw;
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

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
