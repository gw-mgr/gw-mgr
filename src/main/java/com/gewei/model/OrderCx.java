package com.gewei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("order_cx")
public class OrderCx extends Model<OrderCx> {

    private static final long serialVersionUID = 1L;

	private String orderId;
	private String createTime;
	private String orderType;
	private String merchantId;
	private String operator;
	private String orderNo;
	private String tbStartTime;
	private String tbEndTime;
	private String updateTime;
	private String updateUser;
	private String orderFlag;
	private String giftFlag;
	private String commissionFlag;
	private String printFlag;
	private String checkoutFlag;
	private String ddr;
	private String ddDate;
	private BigDecimal bxgssqjqyjbl;
	private BigDecimal bxgssqyjbl;
	private BigDecimal bxgssqjqyj;
	private BigDecimal bxgssqyj;
	private BigDecimal bxgskpyjbl;
	private BigDecimal bxgskpyj;
	private BigDecimal gsglfbl;
	private BigDecimal gsglfje;
	private BigDecimal gsjsbl;
	private BigDecimal gsjsje;
	private BigDecimal gstcbl;
	private BigDecimal gstcje;
	private BigDecimal bgsml;
	private String carNum;
	private String carFrameNum;
	private String engineNum;
	private String changType;
	private String registerTime;
	private String examineTime;
	private String businessInsStartTime;
	private String carType;
	private String trafficInsStartTime;
	private String carRemark;
	private String carOwner;
	private String ownerCardType;
	private String ownerCardId;
	private String policyholderId;
	private String policyholder;
	private String tbrCardType;
	private String tbrCardId;
	private String beneficiary;
	private String bbrSex;
	private Integer bbrAge;
	private String bbrCardType;
	private String bbrCardId;
	private String telphone;
	private String mtelphone;
	private String mail;
	private String qq;
	private String district;
	private String city;
	private String province;
	private String linkaddr;
	private String ownerRemark;
	private String insuranceCompanyId;
	private String insuranceCompany;
	private String insuranceCompanyTel;
	private String tbdw;
	private String tbfs;
	private BigDecimal jsyx;
	private BigDecimal syxs;
	private BigDecimal zsyx;
	private BigDecimal jjqx;
	private BigDecimal jqxs;
	private BigDecimal zjq;
	private BigDecimal ccs;
	private BigDecimal zbf;
	private String salesMan;
	private String yjzfdx;
	private String tjjyjCancle;
	private BigDecimal syxyjbl;
	private BigDecimal syxyj;
	private BigDecimal zhjrjbl;
	private BigDecimal zhjrjje;
	private BigDecimal jjxyjbl;
	private BigDecimal jjxyj;
	private BigDecimal ccsyjbl;
	private BigDecimal ccsyj;
	private BigDecimal bxgsdqjl;
	private String giftSf;
	private String giftComment;
	private BigDecimal sjzfyj;
	private String yjzfsj;
	private String yjzfr;
	private String recorder;
	private String recorderid;
	private BigDecimal recorderyj;
	private BigDecimal jdcssxbe;
	private BigDecimal jdcssxbf;
	private BigDecimal dszrxbe;
	private BigDecimal dszrxbf;
	private BigDecimal qcdqx;
	private BigDecimal sjzrxbe;
	private BigDecimal sjzrxbf;
	private BigDecimal ckzrxbe;
	private BigDecimal ckzrxbf;
	private BigDecimal blddpsx;
	private BigDecimal zrssx;
	private BigDecimal cshhx;
	private BigDecimal fdcssssx;
	private BigDecimal jdcsstyx;
	private BigDecimal bjmp;
	private String remark;


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

	public String getTjjyjCancle() {
		return tjjyjCancle;
	}

	public void setTjjyjCancle(String tjjyjCancle) {
		this.tjjyjCancle = tjjyjCancle;
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

	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

}
