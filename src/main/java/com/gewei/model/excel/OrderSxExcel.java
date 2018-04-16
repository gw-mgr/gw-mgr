package com.gewei.model.excel;

import java.math.BigDecimal;
import com.gewei.commons.report.excel.ExcelField;

public class OrderSxExcel {
	@ExcelField("保单号")
	private String orderNo;
	@ExcelField("订单类型")
	private String orderType;
	@ExcelField("投保开始生效日期")
	private String tbStartTime;
	@ExcelField("投保结束日期")
	private String tbEndTime;
	@ExcelField("订单状态")
	private String orderFlag;
	@ExcelField("打单状态")
	private String printFlag;
	@ExcelField("佣金支付状态")
	private String commissionFlag;
	@ExcelField("保险公司是否结账")
	private String checkoutFlag;
	@ExcelField("礼品状态")
	private String giftFlag;
	@ExcelField("保险公司主险税前佣金比例")
	private BigDecimal bxgszxsqyjbl;
	@ExcelField("保险公司主险税前佣金")
	private BigDecimal bxgszxsqyj;
	@ExcelField("保险公司附险1税前佣金比例")
	private BigDecimal bxgsfx1sqyjbl;
	@ExcelField("保险公司附险1税前佣金")
	private BigDecimal bxgsfx1sqyj;
	@ExcelField("保险公司附险2税前佣金比例")
	private BigDecimal bxgsfx2sqyjbl;
	@ExcelField("保险公司附险2税前佣金")
	private BigDecimal bxgsfx2sqyj;
	@ExcelField("保险公司开票佣金比例")
	private BigDecimal bxgskpyjbl;
	@ExcelField("保险公司开票佣金")
	private BigDecimal bxgskpyj;
	@ExcelField("公司管理费金额")
	private BigDecimal gsglfje;
	@ExcelField("公司管理费比例")
	private BigDecimal gsglfbl;
	@ExcelField("公司提成金额")
	private BigDecimal gstcje;
	@ExcelField("公司提成比例")
	private BigDecimal gstcbl;
	@ExcelField("公司交税金额")
	private BigDecimal gsjsje;
	@ExcelField("公司交税比例")
	private BigDecimal gsjsbl;
	@ExcelField("投保人ID")
	private String policyholderId;
	@ExcelField("被保人ID")
	private String insuredId;
	@ExcelField("受益人ID")
	private String beneficiaryId;
	@ExcelField("交费频率")
	private String jfpl;
	@ExcelField("是否自动续交")
	private String sfzdxj;
	@ExcelField("首期交费方式")
	private String sqjffs;
	@ExcelField("续期交费方式")
	private String xqjffs;
	@ExcelField("开户行名称")
	private String khhmc;
	@ExcelField("银行账号")
	private String yhzh;
	@ExcelField("保费合计")
	private BigDecimal bfhj;
	@ExcelField("红利领取方式")
	private String hllqfs;
	@ExcelField("领取方式")
	private String lqfs;
	@ExcelField("领取年龄")
	private String hllqnl;
	@ExcelField("红利领取时间")
	private String hllqsj;
	@ExcelField("红利领取类型")
	private String hllqlx;
	@ExcelField("是否自动支付保险费")
	private String sfzdzf;
	@ExcelField("投资连结保险填写")
	private String tzljbxtx;
	@ExcelField("投保人身高")
	private String tbrsg;
	@ExcelField("投保人体重")
	private String tbrtz;
	@ExcelField("被保人身高")
	private String bbrsg;
	@ExcelField("被保人体重")
	private String bbrtz;
	@ExcelField("每天吸烟")
	private String mtxy;
	@ExcelField("吸烟时间")
	private String xysj;
	@ExcelField("饮酒种类")
	private String yjzl;
	@ExcelField("每天饮酒")
	private String mtyj;
	@ExcelField("饮酒时间")
	private String yjsj;
	@ExcelField("怀孕时间（周）")
	private String hysj;
	@ExcelField("2岁儿童出生身高")
	private String cssg;
	@ExcelField("2岁儿童出生体重")
	private String cstz;
	@ExcelField("索赔公司名称")
	private String spgsmc;
	@ExcelField("索赔日期")
	private String sprq;
	@ExcelField("索赔金额")
	private String spje;
	@ExcelField("索赔事由")
	private String spsy;
	@ExcelField("驾照类型")
	private String jzlx;
	@ExcelField("驾照签发日期")
	private String jzqfrq;
	@ExcelField("驾龄(年)")
	private String jzjl;
	@ExcelField("车辆用途")
	private String clyt;
	@ExcelField("车辆类型")
	private String cllx;
	@ExcelField("国家/地区名")
	private String jzgj;
	@ExcelField("缘由")
	private String jzyy;
	@ExcelField("投保人年收入")
	private BigDecimal tbrnsr;
	@ExcelField("投保人收入来源")
	private String tbrsrly;
	@ExcelField("被保人年收入")
	private BigDecimal bbrnsr;
	@ExcelField("被保人收入来源")
	private String bbrsrly;
	@ExcelField("保险公司编号")
	private String insuranceCompanyId;
	@ExcelField("出单渠道")
	private String operator;
	@ExcelField("投保方式")
	private String tbfs;
	@ExcelField("净主险")
	private BigDecimal jzx;
	@ExcelField("主险税")
	private BigDecimal zxs;
	@ExcelField("净附险1")
	private BigDecimal jfx1;
	@ExcelField("附险1税")
	private BigDecimal fxs1;
	@ExcelField("净附险2")
	private BigDecimal jfx2;
	@ExcelField("附险2税")
	private BigDecimal fxs2;
	@ExcelField("业务员佣金")
	private BigDecimal salesManCommission;
	@ExcelField("业务员")
	private String salesMan;
	@ExcelField("佣金支付对象")
	private String yjzfdx;
	@ExcelField("主险佣金比例")
	private BigDecimal zxyjbl;
	@ExcelField("主险佣金金额")
	private BigDecimal zxyjje;
	@ExcelField("附险佣金比例1")
	private BigDecimal fxyjbl1;
	@ExcelField("附险佣金金额1")
	private BigDecimal fxyjje1;
	@ExcelField("附险佣金比例2")
	private BigDecimal fxyjbl2;
	@ExcelField("附险佣金金额2")
	private BigDecimal fxyjje2;
	@ExcelField("综合金融奖比例")
	private BigDecimal zhjrjbl;
	@ExcelField("综合金融奖金额")
	private BigDecimal zhjrjje;
	@ExcelField("保险公司短期激励")
	private BigDecimal bxgsdqjl;
	@ExcelField("是否有礼品")
	private String sfylp;
	@ExcelField("礼品内容")
	private String lpnr;
	@ExcelField("录单员")
	private String recorder;
	@ExcelField("录单员ID")
	private String recorderid;
	@ExcelField("录单员提成")
	private BigDecimal recordertc;
	@ExcelField("创建时间")
	private String createTime;
	@ExcelField("修改时间")
	private String updateTime;
	@ExcelField("修改人")
	private String updateUser;
	@ExcelField("打单人")
	private String ddr;
	@ExcelField("打单日期")
	private String ddDate;
	@ExcelField("本公司毛利")
	private BigDecimal bgsml;
	@ExcelField("实际支付佣金")
	private BigDecimal sjzfyj;
	@ExcelField("佣金支付时间")
	private String yjzfsj;
	@ExcelField("佣金支付人")
	private String yjzfr;


	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}


	public String getPrintFlag() {
		return printFlag;
	}

	public void setPrintFlag(String printFlag) {
		this.printFlag = printFlag;
	}

	public String getCommissionFlag() {
		return commissionFlag;
	}

	public void setCommissionFlag(String commissionFlag) {
		this.commissionFlag = commissionFlag;
	}

	public String getCheckoutFlag() {
		return checkoutFlag;
	}

	public void setCheckoutFlag(String checkoutFlag) {
		this.checkoutFlag = checkoutFlag;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
	}

	public BigDecimal getBxgszxsqyjbl() {
		return bxgszxsqyjbl;
	}

	public void setBxgszxsqyjbl(BigDecimal bxgszxsqyjbl) {
		this.bxgszxsqyjbl = bxgszxsqyjbl;
	}

	public BigDecimal getBxgszxsqyj() {
		return bxgszxsqyj;
	}

	public void setBxgszxsqyj(BigDecimal bxgszxsqyj) {
		this.bxgszxsqyj = bxgszxsqyj;
	}

	public BigDecimal getBxgsfx1sqyjbl() {
		return bxgsfx1sqyjbl;
	}

	public void setBxgsfx1sqyjbl(BigDecimal bxgsfx1sqyjbl) {
		this.bxgsfx1sqyjbl = bxgsfx1sqyjbl;
	}

	public BigDecimal getBxgsfx1sqyj() {
		return bxgsfx1sqyj;
	}

	public void setBxgsfx1sqyj(BigDecimal bxgsfx1sqyj) {
		this.bxgsfx1sqyj = bxgsfx1sqyj;
	}

	public BigDecimal getBxgsfx2sqyjbl() {
		return bxgsfx2sqyjbl;
	}

	public void setBxgsfx2sqyjbl(BigDecimal bxgsfx2sqyjbl) {
		this.bxgsfx2sqyjbl = bxgsfx2sqyjbl;
	}

	public BigDecimal getBxgsfx2sqyj() {
		return bxgsfx2sqyj;
	}

	public void setBxgsfx2sqyj(BigDecimal bxgsfx2sqyj) {
		this.bxgsfx2sqyj = bxgsfx2sqyj;
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

	public BigDecimal getGsglfje() {
		return gsglfje;
	}

	public void setGsglfje(BigDecimal gsglfje) {
		this.gsglfje = gsglfje;
	}

	public BigDecimal getGsglfbl() {
		return gsglfbl;
	}

	public void setGsglfbl(BigDecimal gsglfbl) {
		this.gsglfbl = gsglfbl;
	}

	public BigDecimal getGstcje() {
		return gstcje;
	}

	public void setGstcje(BigDecimal gstcje) {
		this.gstcje = gstcje;
	}

	public BigDecimal getGstcbl() {
		return gstcbl;
	}

	public void setGstcbl(BigDecimal gstcbl) {
		this.gstcbl = gstcbl;
	}

	public BigDecimal getGsjsje() {
		return gsjsje;
	}

	public void setGsjsje(BigDecimal gsjsje) {
		this.gsjsje = gsjsje;
	}

	public BigDecimal getGsjsbl() {
		return gsjsbl;
	}

	public void setGsjsbl(BigDecimal gsjsbl) {
		this.gsjsbl = gsjsbl;
	}

	public String getPolicyholderId() {
		return policyholderId;
	}

	public void setPolicyholderId(String policyholderId) {
		this.policyholderId = policyholderId;
	}

	public String getInsuredId() {
		return insuredId;
	}

	public void setInsuredId(String insuredId) {
		this.insuredId = insuredId;
	}

	public String getBeneficiaryId() {
		return beneficiaryId;
	}

	public void setBeneficiaryId(String beneficiaryId) {
		this.beneficiaryId = beneficiaryId;
	}

	public String getJfpl() {
		return jfpl;
	}

	public void setJfpl(String jfpl) {
		this.jfpl = jfpl;
	}

	public String getSfzdxj() {
		return sfzdxj;
	}

	public void setSfzdxj(String sfzdxj) {
		this.sfzdxj = sfzdxj;
	}

	public String getSqjffs() {
		return sqjffs;
	}

	public void setSqjffs(String sqjffs) {
		this.sqjffs = sqjffs;
	}

	public String getXqjffs() {
		return xqjffs;
	}

	public void setXqjffs(String xqjffs) {
		this.xqjffs = xqjffs;
	}

	public String getKhhmc() {
		return khhmc;
	}

	public void setKhhmc(String khhmc) {
		this.khhmc = khhmc;
	}

	public String getYhzh() {
		return yhzh;
	}

	public void setYhzh(String yhzh) {
		this.yhzh = yhzh;
	}

	public BigDecimal getBfhj() {
		return bfhj;
	}

	public void setBfhj(BigDecimal bfhj) {
		this.bfhj = bfhj;
	}

	public String getHllqfs() {
		return hllqfs;
	}

	public void setHllqfs(String hllqfs) {
		this.hllqfs = hllqfs;
	}

	public String getLqfs() {
		return lqfs;
	}

	public void setLqfs(String lqfs) {
		this.lqfs = lqfs;
	}

	public String getHllqnl() {
		return hllqnl;
	}

	public void setHllqnl(String hllqnl) {
		this.hllqnl = hllqnl;
	}

	public String getHllqsj() {
		return hllqsj;
	}

	public void setHllqsj(String hllqsj) {
		this.hllqsj = hllqsj;
	}

	public String getHllqlx() {
		return hllqlx;
	}

	public void setHllqlx(String hllqlx) {
		this.hllqlx = hllqlx;
	}

	public String getSfzdzf() {
		return sfzdzf;
	}

	public void setSfzdzf(String sfzdzf) {
		this.sfzdzf = sfzdzf;
	}

	public String getTzljbxtx() {
		return tzljbxtx;
	}

	public void setTzljbxtx(String tzljbxtx) {
		this.tzljbxtx = tzljbxtx;
	}

	public String getTbrsg() {
		return tbrsg;
	}

	public void setTbrsg(String tbrsg) {
		this.tbrsg = tbrsg;
	}

	public String getTbrtz() {
		return tbrtz;
	}

	public void setTbrtz(String tbrtz) {
		this.tbrtz = tbrtz;
	}

	public String getBbrsg() {
		return bbrsg;
	}

	public void setBbrsg(String bbrsg) {
		this.bbrsg = bbrsg;
	}

	public String getBbrtz() {
		return bbrtz;
	}

	public void setBbrtz(String bbrtz) {
		this.bbrtz = bbrtz;
	}

	public String getMtxy() {
		return mtxy;
	}

	public void setMtxy(String mtxy) {
		this.mtxy = mtxy;
	}

	public String getXysj() {
		return xysj;
	}

	public void setXysj(String xysj) {
		this.xysj = xysj;
	}

	public String getYjzl() {
		return yjzl;
	}

	public void setYjzl(String yjzl) {
		this.yjzl = yjzl;
	}

	public String getMtyj() {
		return mtyj;
	}

	public void setMtyj(String mtyj) {
		this.mtyj = mtyj;
	}

	public String getYjsj() {
		return yjsj;
	}

	public void setYjsj(String yjsj) {
		this.yjsj = yjsj;
	}

	public String getHysj() {
		return hysj;
	}

	public void setHysj(String hysj) {
		this.hysj = hysj;
	}

	public String getCssg() {
		return cssg;
	}

	public void setCssg(String cssg) {
		this.cssg = cssg;
	}

	public String getCstz() {
		return cstz;
	}

	public void setCstz(String cstz) {
		this.cstz = cstz;
	}

	public String getSpgsmc() {
		return spgsmc;
	}

	public void setSpgsmc(String spgsmc) {
		this.spgsmc = spgsmc;
	}

	public String getSprq() {
		return sprq;
	}

	public void setSprq(String sprq) {
		this.sprq = sprq;
	}

	public String getSpje() {
		return spje;
	}

	public void setSpje(String spje) {
		this.spje = spje;
	}

	public String getSpsy() {
		return spsy;
	}

	public void setSpsy(String spsy) {
		this.spsy = spsy;
	}

	public String getJzlx() {
		return jzlx;
	}

	public void setJzlx(String jzlx) {
		this.jzlx = jzlx;
	}

	public String getJzqfrq() {
		return jzqfrq;
	}

	public void setJzqfrq(String jzqfrq) {
		this.jzqfrq = jzqfrq;
	}

	public String getJzjl() {
		return jzjl;
	}

	public void setJzjl(String jzjl) {
		this.jzjl = jzjl;
	}

	public String getClyt() {
		return clyt;
	}

	public void setClyt(String clyt) {
		this.clyt = clyt;
	}

	public String getCllx() {
		return cllx;
	}

	public void setCllx(String cllx) {
		this.cllx = cllx;
	}

	public String getJzgj() {
		return jzgj;
	}

	public void setJzgj(String jzgj) {
		this.jzgj = jzgj;
	}

	public String getJzyy() {
		return jzyy;
	}

	public void setJzyy(String jzyy) {
		this.jzyy = jzyy;
	}

	public BigDecimal getTbrnsr() {
		return tbrnsr;
	}

	public void setTbrnsr(BigDecimal tbrnsr) {
		this.tbrnsr = tbrnsr;
	}

	public String getTbrsrly() {
		return tbrsrly;
	}

	public void setTbrsrly(String tbrsrly) {
		this.tbrsrly = tbrsrly;
	}

	public BigDecimal getBbrnsr() {
		return bbrnsr;
	}

	public void setBbrnsr(BigDecimal bbrnsr) {
		this.bbrnsr = bbrnsr;
	}

	public String getBbrsrly() {
		return bbrsrly;
	}

	public void setBbrsrly(String bbrsrly) {
		this.bbrsrly = bbrsrly;
	}

	public String getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(String insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
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

	public BigDecimal getJzx() {
		return jzx;
	}

	public void setJzx(BigDecimal jzx) {
		this.jzx = jzx;
	}

	public BigDecimal getZxs() {
		return zxs;
	}

	public void setZxs(BigDecimal zxs) {
		this.zxs = zxs;
	}

	public BigDecimal getJfx1() {
		return jfx1;
	}

	public void setJfx1(BigDecimal jfx1) {
		this.jfx1 = jfx1;
	}

	public BigDecimal getFxs1() {
		return fxs1;
	}

	public void setFxs1(BigDecimal fxs1) {
		this.fxs1 = fxs1;
	}

	public BigDecimal getJfx2() {
		return jfx2;
	}

	public void setJfx2(BigDecimal jfx2) {
		this.jfx2 = jfx2;
	}

	public BigDecimal getFxs2() {
		return fxs2;
	}

	public void setFxs2(BigDecimal fxs2) {
		this.fxs2 = fxs2;
	}

	public BigDecimal getSalesManCommission() {
		return salesManCommission;
	}

	public void setSalesManCommission(BigDecimal salesManCommission) {
		this.salesManCommission = salesManCommission;
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

	public BigDecimal getZxyjbl() {
		return zxyjbl;
	}

	public void setZxyjbl(BigDecimal zxyjbl) {
		this.zxyjbl = zxyjbl;
	}

	public BigDecimal getZxyjje() {
		return zxyjje;
	}

	public void setZxyjje(BigDecimal zxyjje) {
		this.zxyjje = zxyjje;
	}

	public BigDecimal getFxyjbl1() {
		return fxyjbl1;
	}

	public void setFxyjbl1(BigDecimal fxyjbl1) {
		this.fxyjbl1 = fxyjbl1;
	}

	public BigDecimal getFxyjje1() {
		return fxyjje1;
	}

	public void setFxyjje1(BigDecimal fxyjje1) {
		this.fxyjje1 = fxyjje1;
	}

	public BigDecimal getFxyjbl2() {
		return fxyjbl2;
	}

	public void setFxyjbl2(BigDecimal fxyjbl2) {
		this.fxyjbl2 = fxyjbl2;
	}

	public BigDecimal getFxyjje2() {
		return fxyjje2;
	}

	public void setFxyjje2(BigDecimal fxyjje2) {
		this.fxyjje2 = fxyjje2;
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

	public BigDecimal getBxgsdqjl() {
		return bxgsdqjl;
	}

	public void setBxgsdqjl(BigDecimal bxgsdqjl) {
		this.bxgsdqjl = bxgsdqjl;
	}

	public String getSfylp() {
		return sfylp;
	}

	public void setSfylp(String sfylp) {
		this.sfylp = sfylp;
	}

	public String getLpnr() {
		return lpnr;
	}

	public void setLpnr(String lpnr) {
		this.lpnr = lpnr;
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

	public BigDecimal getRecordertc() {
		return recordertc;
	}

	public void setRecordertc(BigDecimal recordertc) {
		this.recordertc = recordertc;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public BigDecimal getBgsml() {
		return bgsml;
	}

	public void setBgsml(BigDecimal bgsml) {
		this.bgsml = bgsml;
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
}
