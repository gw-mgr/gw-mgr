package com.gewei.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("order_sx")
public class OrderSx extends Model<OrderSx> {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
	private String orderId;
    /**
     * 保单号
     */
	private String orderNo;
    /**
     * 订单类型，见表t_merchant_product_category
     */
	private String orderType;
    /**
     * 投保开始生效日期
     */
	private String tbStartTime;
    /**
     * 保险截止日期
     */
	private String tbEndTime;
    /**
     * 订单状态（01未成交、02成交、03删除、04顾客申请重新报价），默认01
     */
	private String orderFlag;
    /**
     * 服务商编号，见表t_merchant_manage
     */
	private String merchantId;
    /**
     * 打单状态（01未打印、02已打印），默认01
     */
	private String printFlag;
    /**
     * 佣金支付状态（01未发放、02已发放），默认01
     */
	private String commissionFlag;
    /**
     * 保险公司是否结账（01否、02是），默认01
     */
	private String checkoutFlag;
    /**
     * 礼品状态（01未发放、02已发放），默认01
     */
	private String giftFlag;
    /**
     * 保险公司主险税前佣金比例
     */
	private BigDecimal bxgszxsqyjbl;
    /**
     * 保险公司主险税前佣金
     */
	private BigDecimal bxgszxsqyj;
    /**
     * 保险公司附险1税前佣金比例
     */
	private BigDecimal bxgsfx1sqyjbl;
    /**
     * 保险公司附险1税前佣金
     */
	private BigDecimal bxgsfx1sqyj;
    /**
     * 保险公司附险2税前佣金比例
     */
	private BigDecimal bxgsfx2sqyjbl;
    /**
     * 保险公司附险2税前佣金
     */
	private BigDecimal bxgsfx2sqyj;
    /**
     * 保险公司开票佣金比例
     */
	private BigDecimal bxgskpyjbl;
    /**
     * 保险公司开票佣金
     */
	private BigDecimal bxgskpyj;
    /**
     * 公司管理费金额
     */
	private BigDecimal gsglfje;
    /**
     * 公司管理费比例
     */
	private BigDecimal gsglfbl;
    /**
     * 公司提成金额
     */
	private BigDecimal gstcje;
    /**
     * 公司提成比例
     */
	private BigDecimal gstcbl;
    /**
     * 公司交税金额
     */
	private BigDecimal gsjsje;
    /**
     * 公司交税比例
     */
	private BigDecimal gsjsbl;
    /**
     * 投保人ID
     */
	private String policyholderId;
    /**
     * 被保人ID
     */
	private String insuredId;
    /**
     * 受益人ID,多个逗号分隔
     */
	private String beneficiaryId;
    /**
     * 交费频率（01年交、02季交、03月交）
     */
	private String jfpl;
    /**
     * 是否自动续交（01是、02否）
     */
	private String sfzdxj;
    /**
     * 首期交费方式（01银行转账、02现金）
     */
	private String sqjffs;
    /**
     * 续期交费方式（01银行转账、02现金）
     */
	private String xqjffs;
    /**
     * 开户行名称
     */
	private String khhmc;
    /**
     * 银行账号
     */
	private String yhzh;
    /**
     * 保费合计
     */
	private BigDecimal bfhj;
    /**
     * 红利领取方式（01累计生息、02现金领取、03红利转万能险账户、04抵交保险费、05其他）
     */
	private String hllqfs;
    /**
     * 领取方式
     */
	private String lqfs;
    /**
     * 领取年龄
     */
	private String hllqnl;
    /**
     * 红利领取时间（01年领、02。。）
     */
	private String hllqsj;
    /**
     * 红利领取类型（01平准年金、02。。）
     */
	private String hllqlx;
    /**
     * 是否自动支付保险费（01是、02否）
     */
	private String sfzdzf;
    /**
     * 投资连结保险填写
张三#$#0.03#$#0.03@张四#$#0.04#$#0.04@张五#$#0.05#$#0.05
     */
	private String tzljbxtx;
    /**
     * 投保人身高
     */
	private String tbrsg;
    /**
     * 投保人体重
     */
	private String tbrtz;
    /**
     * 被保人身高
     */
	private String bbrsg;
    /**
     * 被保人体重
     */
	private String bbrtz;
    /**
     * 每天吸烟(只)
     */
	private String mtxy;
    /**
     * 吸烟时间(年)
     */
	private String xysj;
    /**
     * 饮酒种类
     */
	private String yjzl;
    /**
     * 每天饮酒
     */
	private String mtyj;
    /**
     * 饮酒时间
     */
	private String yjsj;
    /**
     * 怀孕时间（周）
     */
	private String hysj;
    /**
     * 2岁儿童出生身高
     */
	private String cssg;
    /**
     * 2岁儿童出生体重
     */
	private String cstz;
    /**
     * 索赔公司名称
     */
	private String spgsmc;
    /**
     * 索赔日期
     */
	private String sprq;
    /**
     * 索赔金额
     */
	private String spje;
    /**
     * 索赔事由
     */
	private String spsy;
    /**
     * 驾照类型
     */
	private String jzlx;
    /**
     * 驾照签发日期
     */
	private String jzqfrq;
    /**
     * 驾龄(年)
     */
	private String jzjl;
    /**
     * 车辆用途
     */
	private String clyt;
    /**
     * 车辆类型
     */
	private String cllx;
    /**
     * 国家/地区名
     */
	private String jzgj;
    /**
     * 缘由
     */
	private String jzyy;
    /**
     * 滞留时间
     */
	private String jzzlsj;
    /**
     * 保险公司询问事项1（01是、02否）
     */
	private String txwsx1;
    /**
     * 保险公司询问事项1（01是、02否）
     */
	private String bxwsx1;
    /**
     * 保险公司询问事项2（01是、02否）
     */
	private String txwsx2;
    /**
     * 保险公司询问事项2（01是、02否）
     */
	private String bxwsx2;
    /**
     * 保险公司询问事项3（01是、02否）
     */
	private String txwsx3;
    /**
     * 保险公司询问事项3（01是、02否）
     */
	private String bxwsx3;
    /**
     * 保险公司询问事项4（01是、02否）
     */
	private String txwsx4;
    /**
     * 保险公司询问事项4（01是、02否）
     */
	private String bxwsx4;
    /**
     * 保险公司询问事项6（01是、02否）
     */
	private String txwsx6;
    /**
     * 保险公司询问事项6（01是、02否）
     */
	private String bxwsx6;
    /**
     * 保险公司询问事项7（01是、02否）
     */
	private String txwsx7;
    /**
     * 保险公司询问事项7（01是、02否）
     */
	private String bxwsx7;
    /**
     * 保险公司询问事项8（01是、02否）
     */
	private String txwsx8;
    /**
     * 保险公司询问事项9（01是、02否）
     */
	private String txwsx9;
    /**
     * 保险公司询问事项11（01是、02否）
     */
	private String txwsx11;
    /**
     * 保险公司询问事项12（01是、02否）
     */
	private String txwsx12;
    /**
     * 保险公司询问事项13（01是、02否）
     */
	private String txwsx13;
    /**
     * 保险公司询问事项14（01是、02否）
     */
	private String txwsx14;
    /**
     * 保险公司询问事项15（01是、02否）
     */
	private String txwsx15;
    /**
     * 保险公司询问事项16（01是、02否）
     */
	private String txwsx16;
    /**
     * 保险公司询问事项17（01是、02否）
     */
	private String txwsx17;
    /**
     * 保险公司询问事项18（01是、02否）
     */
	private String txwsx18;
    /**
     * 保险公司询问事项19（01是、02否）
     */
	private String txwsx19;
    /**
     * 保险公司询问事项20（01是、02否）
     */
	private String txwsx20;
    /**
     * 保险公司询问事项8（01是、02否）
     */
	private String bxwsx8;
    /**
     * 保险公司询问事项9（01是、02否）
     */
	private String bxwsx9;
    /**
     * 保险公司询问事项11（01是、02否）
     */
	private String bxwsx11;
    /**
     * 保险公司询问事项12（01是、02否）
     */
	private String bxwsx12;
    /**
     * 保险公司询问事项13（01是、02否）
     */
	private String bxwsx13;
    /**
     * 保险公司询问事项14（01是、02否）
     */
	private String bxwsx14;
    /**
     * 保险公司询问事项15（01是、02否）
     */
	private String bxwsx15;
    /**
     * 保险公司询问事项16（01是、02否）
     */
	private String bxwsx16;
    /**
     * 保险公司询问事项17（01是、02否）
     */
	private String bxwsx17;
    /**
     * 保险公司询问事项18（01是、02否）
     */
	private String bxwsx18;
    /**
     * 保险公司询问事项19（01是、02否）
     */
	private String bxwsx19;
    /**
     * 保险公司询问事项20（01是、02否）
     */
	private String bxwsx20;
    /**
     * 保险公司询问事项5a（01是、02否）
     */
	private String txwsx5a;
    /**
     * 保险公司询问事项5b（01是、02否）
     */
	private String txwsx5b;
    /**
     * 保险公司询问事项5c（01是、02否）
     */
	private String txwsx5c;
    /**
     * 保险公司询问事项5d（01是、02否）
     */
	private String txwsx5d;
    /**
     * 保险公司询问事项5e（01是、02否）
     */
	private String txwsx5e;
    /**
     * 保险公司询问事项5f（01是、02否）
     */
	private String txwsx5f;
    /**
     * 保险公司询问事项5g（01是、02否）
     */
	private String txwsx5g;
    /**
     * 保险公司询问事项5h（01是、02否）
     */
	private String txwsx5h;
    /**
     * 保险公司询问事项5i（01是、02否）
     */
	private String txwsx5i;
    /**
     * 保险公司询问事项5j（01是、02否）
     */
	private String txwsx5j;
    /**
     * 保险公司询问事项5k（01是、02否）
     */
	private String txwsx5k;
    /**
     * 保险公司询问事项5a（01是、02否）
     */
	private String bxwsx5a;
    /**
     * 保险公司询问事项5b（01是、02否）
     */
	private String bxwsx5b;
    /**
     * 保险公司询问事项5c（01是、02否）
     */
	private String bxwsx5c;
    /**
     * 保险公司询问事项5d（01是、02否）
     */
	private String bxwsx5d;
    /**
     * 保险公司询问事项5e（01是、02否）
     */
	private String bxwsx5e;
    /**
     * 保险公司询问事项5f（01是、02否）
     */
	private String bxwsx5f;
    /**
     * 保险公司询问事项5g（01是、02否）
     */
	private String bxwsx5g;
    /**
     * 保险公司询问事项5h（01是、02否）
     */
	private String bxwsx5h;
    /**
     * 保险公司询问事项5i（01是、02否）
     */
	private String bxwsx5i;
    /**
     * 保险公司询问事项5j（01是、02否）
     */
	private String bxwsx5j;
    /**
     * 保险公司询问事项5k（01是、02否）
     */
	private String bxwsx5k;
    /**
     * 保险公司询问事项10a（01是、02否）
     */
	private String txwsx10a;
    /**
     * 保险公司询问事项10b（01是、02否）
     */
	private String txwsx10b;
    /**
     * 保险公司询问事项10c（01是、02否）
     */
	private String txwsx10c;
    /**
     * 保险公司询问事项10a（01是、02否）
     */
	private String bxwsx10a;
    /**
     * 保险公司询问事项10b（01是、02否）
     */
	private String bxwsx10b;
    /**
     * 保险公司询问事项10c（01是、02否）
     */
	private String bxwsx10c;
    /**
     * 保险公司询问事项11a（01是、02否）
     */
	private String txwsx11a;
    /**
     * 保险公司询问事项11b（01是、02否）
     */
	private String txwsx11b;
    /**
     * 保险公司询问事项11a（01是、02否）
     */
	private String bxwsx11a;
    /**
     * 保险公司询问事项11b（01是、02否）
     */
	private String bxwsx11b;
    /**
     * 投保人年收入，单位：分
     */
	private BigDecimal tbrnsr;
    /**
     * 投保人收入来源（01工薪、02个体、03私营、04房屋出租、05证券投入、06银行利息、07其他）
     */
	private String tbrsrly;
    /**
     * 被保人年收入，单位：分
     */
	private BigDecimal bbrnsr;
    /**
     * 被保人收入来源（01工薪、02个体、03私营、04房屋出租、05证券投入、06银行利息、07其他）
     */
	private String bbrsrly;
    /**
     * 保险公司编号
     */
	private String insuranceCompanyId;
    /**
     * 出单渠道（操作人）
     */
	private String operator;
    /**
     * 投保方式
     */
	private String tbfs;
    /**
     * 净主险
     */
	private BigDecimal jzx;
    /**
     * 主险税
     */
	private BigDecimal zxs;
    /**
     * 净附险1
     */
	private BigDecimal jfx1;
    /**
     * 附险1税
     */
	private BigDecimal fxs1;
    /**
     * 净附险2
     */
	private BigDecimal jfx2;
    /**
     * 附险2税
     */
	private BigDecimal fxs2;
    /**
     * 业务员佣金
     */
	private BigDecimal salesManCommission;
    /**
     * 业务员
     */
	private String salesMan;
    /**
     * 佣金支付对象（01业务员、02录单员）
     */
	private String yjzfdx;
    /**
     * 主险佣金比例
     */
	private BigDecimal zxyjbl;
    /**
     * 主险佣金金额
     */
	private BigDecimal zxyjje;
    /**
     * 附险佣金比例1
     */
	private BigDecimal fxyjbl1;
    /**
     * 附险佣金金额1
     */
	private BigDecimal fxyjje1;
    /**
     * 附险佣金比例2
     */
	private BigDecimal fxyjbl2;
    /**
     * 附险佣金金额2
     */
	private BigDecimal fxyjje2;
    /**
     * 综合金融奖比例
     */
	private BigDecimal zhjrjbl;
    /**
     * 综合金融奖金额
     */
	private BigDecimal zhjrjje;
    /**
     * 保险公司短期激励
     */
	private BigDecimal bxgsdqjl;
    /**
     * 是否有礼品（01是、02否）
     */
	private String sfylp;
    /**
     * 礼品内容
     */
	private String lpnr;
    /**
     * 录单员
     */
	private String recorder;
    /**
     * 录单员ID
     */
	private String recorderid;
    /**
     * 录单员提成
     */
	private BigDecimal recordertc;
    /**
     * 创建时间
     */
	private String createTime;
    /**
     * 修改时间
     */
	private String updateTime;
    /**
     * 修改人
     */
	private String updateUser;
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
    /**
     * 打单人
     */
	private String ddr;
    /**
     * 打单日期
     */
	private String ddDate;
    /**
     * 本公司毛利
     */
	private BigDecimal bgsml;
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


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
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

	public String getJzzlsj() {
		return jzzlsj;
	}

	public void setJzzlsj(String jzzlsj) {
		this.jzzlsj = jzzlsj;
	}

	public String getTxwsx1() {
		return txwsx1;
	}

	public void setTxwsx1(String txwsx1) {
		this.txwsx1 = txwsx1;
	}

	public String getBxwsx1() {
		return bxwsx1;
	}

	public void setBxwsx1(String bxwsx1) {
		this.bxwsx1 = bxwsx1;
	}

	public String getTxwsx2() {
		return txwsx2;
	}

	public void setTxwsx2(String txwsx2) {
		this.txwsx2 = txwsx2;
	}

	public String getBxwsx2() {
		return bxwsx2;
	}

	public void setBxwsx2(String bxwsx2) {
		this.bxwsx2 = bxwsx2;
	}

	public String getTxwsx3() {
		return txwsx3;
	}

	public void setTxwsx3(String txwsx3) {
		this.txwsx3 = txwsx3;
	}

	public String getBxwsx3() {
		return bxwsx3;
	}

	public void setBxwsx3(String bxwsx3) {
		this.bxwsx3 = bxwsx3;
	}

	public String getTxwsx4() {
		return txwsx4;
	}

	public void setTxwsx4(String txwsx4) {
		this.txwsx4 = txwsx4;
	}

	public String getBxwsx4() {
		return bxwsx4;
	}

	public void setBxwsx4(String bxwsx4) {
		this.bxwsx4 = bxwsx4;
	}

	public String getTxwsx6() {
		return txwsx6;
	}

	public void setTxwsx6(String txwsx6) {
		this.txwsx6 = txwsx6;
	}

	public String getBxwsx6() {
		return bxwsx6;
	}

	public void setBxwsx6(String bxwsx6) {
		this.bxwsx6 = bxwsx6;
	}

	public String getTxwsx7() {
		return txwsx7;
	}

	public void setTxwsx7(String txwsx7) {
		this.txwsx7 = txwsx7;
	}

	public String getBxwsx7() {
		return bxwsx7;
	}

	public void setBxwsx7(String bxwsx7) {
		this.bxwsx7 = bxwsx7;
	}

	public String getTxwsx8() {
		return txwsx8;
	}

	public void setTxwsx8(String txwsx8) {
		this.txwsx8 = txwsx8;
	}

	public String getTxwsx9() {
		return txwsx9;
	}

	public void setTxwsx9(String txwsx9) {
		this.txwsx9 = txwsx9;
	}

	public String getTxwsx11() {
		return txwsx11;
	}

	public void setTxwsx11(String txwsx11) {
		this.txwsx11 = txwsx11;
	}

	public String getTxwsx12() {
		return txwsx12;
	}

	public void setTxwsx12(String txwsx12) {
		this.txwsx12 = txwsx12;
	}

	public String getTxwsx13() {
		return txwsx13;
	}

	public void setTxwsx13(String txwsx13) {
		this.txwsx13 = txwsx13;
	}

	public String getTxwsx14() {
		return txwsx14;
	}

	public void setTxwsx14(String txwsx14) {
		this.txwsx14 = txwsx14;
	}

	public String getTxwsx15() {
		return txwsx15;
	}

	public void setTxwsx15(String txwsx15) {
		this.txwsx15 = txwsx15;
	}

	public String getTxwsx16() {
		return txwsx16;
	}

	public void setTxwsx16(String txwsx16) {
		this.txwsx16 = txwsx16;
	}

	public String getTxwsx17() {
		return txwsx17;
	}

	public void setTxwsx17(String txwsx17) {
		this.txwsx17 = txwsx17;
	}

	public String getTxwsx18() {
		return txwsx18;
	}

	public void setTxwsx18(String txwsx18) {
		this.txwsx18 = txwsx18;
	}

	public String getTxwsx19() {
		return txwsx19;
	}

	public void setTxwsx19(String txwsx19) {
		this.txwsx19 = txwsx19;
	}

	public String getTxwsx20() {
		return txwsx20;
	}

	public void setTxwsx20(String txwsx20) {
		this.txwsx20 = txwsx20;
	}

	public String getBxwsx8() {
		return bxwsx8;
	}

	public void setBxwsx8(String bxwsx8) {
		this.bxwsx8 = bxwsx8;
	}

	public String getBxwsx9() {
		return bxwsx9;
	}

	public void setBxwsx9(String bxwsx9) {
		this.bxwsx9 = bxwsx9;
	}

	public String getBxwsx11() {
		return bxwsx11;
	}

	public void setBxwsx11(String bxwsx11) {
		this.bxwsx11 = bxwsx11;
	}

	public String getBxwsx12() {
		return bxwsx12;
	}

	public void setBxwsx12(String bxwsx12) {
		this.bxwsx12 = bxwsx12;
	}

	public String getBxwsx13() {
		return bxwsx13;
	}

	public void setBxwsx13(String bxwsx13) {
		this.bxwsx13 = bxwsx13;
	}

	public String getBxwsx14() {
		return bxwsx14;
	}

	public void setBxwsx14(String bxwsx14) {
		this.bxwsx14 = bxwsx14;
	}

	public String getBxwsx15() {
		return bxwsx15;
	}

	public void setBxwsx15(String bxwsx15) {
		this.bxwsx15 = bxwsx15;
	}

	public String getBxwsx16() {
		return bxwsx16;
	}

	public void setBxwsx16(String bxwsx16) {
		this.bxwsx16 = bxwsx16;
	}

	public String getBxwsx17() {
		return bxwsx17;
	}

	public void setBxwsx17(String bxwsx17) {
		this.bxwsx17 = bxwsx17;
	}

	public String getBxwsx18() {
		return bxwsx18;
	}

	public void setBxwsx18(String bxwsx18) {
		this.bxwsx18 = bxwsx18;
	}

	public String getBxwsx19() {
		return bxwsx19;
	}

	public void setBxwsx19(String bxwsx19) {
		this.bxwsx19 = bxwsx19;
	}

	public String getBxwsx20() {
		return bxwsx20;
	}

	public void setBxwsx20(String bxwsx20) {
		this.bxwsx20 = bxwsx20;
	}

	public String getTxwsx5a() {
		return txwsx5a;
	}

	public void setTxwsx5a(String txwsx5a) {
		this.txwsx5a = txwsx5a;
	}

	public String getTxwsx5b() {
		return txwsx5b;
	}

	public void setTxwsx5b(String txwsx5b) {
		this.txwsx5b = txwsx5b;
	}

	public String getTxwsx5c() {
		return txwsx5c;
	}

	public void setTxwsx5c(String txwsx5c) {
		this.txwsx5c = txwsx5c;
	}

	public String getTxwsx5d() {
		return txwsx5d;
	}

	public void setTxwsx5d(String txwsx5d) {
		this.txwsx5d = txwsx5d;
	}

	public String getTxwsx5e() {
		return txwsx5e;
	}

	public void setTxwsx5e(String txwsx5e) {
		this.txwsx5e = txwsx5e;
	}

	public String getTxwsx5f() {
		return txwsx5f;
	}

	public void setTxwsx5f(String txwsx5f) {
		this.txwsx5f = txwsx5f;
	}

	public String getTxwsx5g() {
		return txwsx5g;
	}

	public void setTxwsx5g(String txwsx5g) {
		this.txwsx5g = txwsx5g;
	}

	public String getTxwsx5h() {
		return txwsx5h;
	}

	public void setTxwsx5h(String txwsx5h) {
		this.txwsx5h = txwsx5h;
	}

	public String getTxwsx5i() {
		return txwsx5i;
	}

	public void setTxwsx5i(String txwsx5i) {
		this.txwsx5i = txwsx5i;
	}

	public String getTxwsx5j() {
		return txwsx5j;
	}

	public void setTxwsx5j(String txwsx5j) {
		this.txwsx5j = txwsx5j;
	}

	public String getTxwsx5k() {
		return txwsx5k;
	}

	public void setTxwsx5k(String txwsx5k) {
		this.txwsx5k = txwsx5k;
	}

	public String getBxwsx5a() {
		return bxwsx5a;
	}

	public void setBxwsx5a(String bxwsx5a) {
		this.bxwsx5a = bxwsx5a;
	}

	public String getBxwsx5b() {
		return bxwsx5b;
	}

	public void setBxwsx5b(String bxwsx5b) {
		this.bxwsx5b = bxwsx5b;
	}

	public String getBxwsx5c() {
		return bxwsx5c;
	}

	public void setBxwsx5c(String bxwsx5c) {
		this.bxwsx5c = bxwsx5c;
	}

	public String getBxwsx5d() {
		return bxwsx5d;
	}

	public void setBxwsx5d(String bxwsx5d) {
		this.bxwsx5d = bxwsx5d;
	}

	public String getBxwsx5e() {
		return bxwsx5e;
	}

	public void setBxwsx5e(String bxwsx5e) {
		this.bxwsx5e = bxwsx5e;
	}

	public String getBxwsx5f() {
		return bxwsx5f;
	}

	public void setBxwsx5f(String bxwsx5f) {
		this.bxwsx5f = bxwsx5f;
	}

	public String getBxwsx5g() {
		return bxwsx5g;
	}

	public void setBxwsx5g(String bxwsx5g) {
		this.bxwsx5g = bxwsx5g;
	}

	public String getBxwsx5h() {
		return bxwsx5h;
	}

	public void setBxwsx5h(String bxwsx5h) {
		this.bxwsx5h = bxwsx5h;
	}

	public String getBxwsx5i() {
		return bxwsx5i;
	}

	public void setBxwsx5i(String bxwsx5i) {
		this.bxwsx5i = bxwsx5i;
	}

	public String getBxwsx5j() {
		return bxwsx5j;
	}

	public void setBxwsx5j(String bxwsx5j) {
		this.bxwsx5j = bxwsx5j;
	}

	public String getBxwsx5k() {
		return bxwsx5k;
	}

	public void setBxwsx5k(String bxwsx5k) {
		this.bxwsx5k = bxwsx5k;
	}

	public String getTxwsx10a() {
		return txwsx10a;
	}

	public void setTxwsx10a(String txwsx10a) {
		this.txwsx10a = txwsx10a;
	}

	public String getTxwsx10b() {
		return txwsx10b;
	}

	public void setTxwsx10b(String txwsx10b) {
		this.txwsx10b = txwsx10b;
	}

	public String getTxwsx10c() {
		return txwsx10c;
	}

	public void setTxwsx10c(String txwsx10c) {
		this.txwsx10c = txwsx10c;
	}

	public String getBxwsx10a() {
		return bxwsx10a;
	}

	public void setBxwsx10a(String bxwsx10a) {
		this.bxwsx10a = bxwsx10a;
	}

	public String getBxwsx10b() {
		return bxwsx10b;
	}

	public void setBxwsx10b(String bxwsx10b) {
		this.bxwsx10b = bxwsx10b;
	}

	public String getBxwsx10c() {
		return bxwsx10c;
	}

	public void setBxwsx10c(String bxwsx10c) {
		this.bxwsx10c = bxwsx10c;
	}

	public String getTxwsx11a() {
		return txwsx11a;
	}

	public void setTxwsx11a(String txwsx11a) {
		this.txwsx11a = txwsx11a;
	}

	public String getTxwsx11b() {
		return txwsx11b;
	}

	public void setTxwsx11b(String txwsx11b) {
		this.txwsx11b = txwsx11b;
	}

	public String getBxwsx11a() {
		return bxwsx11a;
	}

	public void setBxwsx11a(String bxwsx11a) {
		this.bxwsx11a = bxwsx11a;
	}

	public String getBxwsx11b() {
		return bxwsx11b;
	}

	public void setBxwsx11b(String bxwsx11b) {
		this.bxwsx11b = bxwsx11b;
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


	@Override
	protected Serializable pkVal() {
		return this.orderId;
	}

	@Override
	public String toString() {
		return "OrderSx [orderId=" + orderId + ", orderNo=" + orderNo + ", orderType=" + orderType + ", tbStartTime="
				+ tbStartTime + ", tbEndTime=" + tbEndTime + ", orderFlag=" + orderFlag + ", merchantId=" + merchantId
				+ ", printFlag=" + printFlag + ", commissionFlag=" + commissionFlag + ", checkoutFlag=" + checkoutFlag
				+ ", giftFlag=" + giftFlag + ", bxgszxsqyjbl=" + bxgszxsqyjbl + ", bxgszxsqyj=" + bxgszxsqyj
				+ ", bxgsfx1sqyjbl=" + bxgsfx1sqyjbl + ", bxgsfx1sqyj=" + bxgsfx1sqyj + ", bxgsfx2sqyjbl="
				+ bxgsfx2sqyjbl + ", bxgsfx2sqyj=" + bxgsfx2sqyj + ", bxgskpyjbl=" + bxgskpyjbl + ", bxgskpyj="
				+ bxgskpyj + ", gsglfje=" + gsglfje + ", gsglfbl=" + gsglfbl + ", gstcje=" + gstcje + ", gstcbl="
				+ gstcbl + ", gsjsje=" + gsjsje + ", gsjsbl=" + gsjsbl + ", policyholderId=" + policyholderId
				+ ", insuredId=" + insuredId + ", beneficiaryId=" + beneficiaryId + ", jfpl=" + jfpl + ", sfzdxj="
				+ sfzdxj + ", sqjffs=" + sqjffs + ", xqjffs=" + xqjffs + ", khhmc=" + khhmc + ", yhzh=" + yhzh
				+ ", bfhj=" + bfhj + ", hllqfs=" + hllqfs + ", lqfs=" + lqfs + ", hllqnl=" + hllqnl + ", hllqsj="
				+ hllqsj + ", hllqlx=" + hllqlx + ", sfzdzf=" + sfzdzf + ", tzljbxtx=" + tzljbxtx + ", tbrsg=" + tbrsg
				+ ", tbrtz=" + tbrtz + ", bbrsg=" + bbrsg + ", bbrtz=" + bbrtz + ", mtxy=" + mtxy + ", xysj=" + xysj
				+ ", yjzl=" + yjzl + ", mtyj=" + mtyj + ", yjsj=" + yjsj + ", hysj=" + hysj + ", cssg=" + cssg
				+ ", cstz=" + cstz + ", spgsmc=" + spgsmc + ", sprq=" + sprq + ", spje=" + spje + ", spsy=" + spsy
				+ ", jzlx=" + jzlx + ", jzqfrq=" + jzqfrq + ", jzjl=" + jzjl + ", clyt=" + clyt + ", cllx=" + cllx
				+ ", jzgj=" + jzgj + ", jzyy=" + jzyy + ", jzzlsj=" + jzzlsj + ", txwsx1=" + txwsx1 + ", bxwsx1="
				+ bxwsx1 + ", txwsx2=" + txwsx2 + ", bxwsx2=" + bxwsx2 + ", txwsx3=" + txwsx3 + ", bxwsx3=" + bxwsx3
				+ ", txwsx4=" + txwsx4 + ", bxwsx4=" + bxwsx4 + ", txwsx6=" + txwsx6 + ", bxwsx6=" + bxwsx6
				+ ", txwsx7=" + txwsx7 + ", bxwsx7=" + bxwsx7 + ", txwsx8=" + txwsx8 + ", txwsx9=" + txwsx9
				+ ", txwsx11=" + txwsx11 + ", txwsx12=" + txwsx12 + ", txwsx13=" + txwsx13 + ", txwsx14=" + txwsx14
				+ ", txwsx15=" + txwsx15 + ", txwsx16=" + txwsx16 + ", txwsx17=" + txwsx17 + ", txwsx18=" + txwsx18
				+ ", txwsx19=" + txwsx19 + ", txwsx20=" + txwsx20 + ", bxwsx8=" + bxwsx8 + ", bxwsx9=" + bxwsx9
				+ ", bxwsx11=" + bxwsx11 + ", bxwsx12=" + bxwsx12 + ", bxwsx13=" + bxwsx13 + ", bxwsx14=" + bxwsx14
				+ ", bxwsx15=" + bxwsx15 + ", bxwsx16=" + bxwsx16 + ", bxwsx17=" + bxwsx17 + ", bxwsx18=" + bxwsx18
				+ ", bxwsx19=" + bxwsx19 + ", bxwsx20=" + bxwsx20 + ", txwsx5a=" + txwsx5a + ", txwsx5b=" + txwsx5b
				+ ", txwsx5c=" + txwsx5c + ", txwsx5d=" + txwsx5d + ", txwsx5e=" + txwsx5e + ", txwsx5f=" + txwsx5f
				+ ", txwsx5g=" + txwsx5g + ", txwsx5h=" + txwsx5h + ", txwsx5i=" + txwsx5i + ", txwsx5j=" + txwsx5j
				+ ", txwsx5k=" + txwsx5k + ", bxwsx5a=" + bxwsx5a + ", bxwsx5b=" + bxwsx5b + ", bxwsx5c=" + bxwsx5c
				+ ", bxwsx5d=" + bxwsx5d + ", bxwsx5e=" + bxwsx5e + ", bxwsx5f=" + bxwsx5f + ", bxwsx5g=" + bxwsx5g
				+ ", bxwsx5h=" + bxwsx5h + ", bxwsx5i=" + bxwsx5i + ", bxwsx5j=" + bxwsx5j + ", bxwsx5k=" + bxwsx5k
				+ ", txwsx10a=" + txwsx10a + ", txwsx10b=" + txwsx10b + ", txwsx10c=" + txwsx10c + ", bxwsx10a="
				+ bxwsx10a + ", bxwsx10b=" + bxwsx10b + ", bxwsx10c=" + bxwsx10c + ", txwsx11a=" + txwsx11a
				+ ", txwsx11b=" + txwsx11b + ", bxwsx11a=" + bxwsx11a + ", bxwsx11b=" + bxwsx11b + ", tbrnsr=" + tbrnsr
				+ ", tbrsrly=" + tbrsrly + ", bbrnsr=" + bbrnsr + ", bbrsrly=" + bbrsrly + ", insuranceCompanyId="
				+ insuranceCompanyId + ", operator=" + operator + ", tbfs=" + tbfs + ", jzx=" + jzx + ", zxs=" + zxs
				+ ", jfx1=" + jfx1 + ", fxs1=" + fxs1 + ", jfx2=" + jfx2 + ", fxs2=" + fxs2 + ", salesManCommission="
				+ salesManCommission + ", salesMan=" + salesMan + ", yjzfdx=" + yjzfdx + ", zxyjbl=" + zxyjbl
				+ ", zxyjje=" + zxyjje + ", fxyjbl1=" + fxyjbl1 + ", fxyjje1=" + fxyjje1 + ", fxyjbl2=" + fxyjbl2
				+ ", fxyjje2=" + fxyjje2 + ", zhjrjbl=" + zhjrjbl + ", zhjrjje=" + zhjrjje + ", bxgsdqjl=" + bxgsdqjl
				+ ", sfylp=" + sfylp + ", lpnr=" + lpnr + ", recorder=" + recorder + ", recorderid=" + recorderid
				+ ", recordertc=" + recordertc + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", updateUser=" + updateUser + ", remark1=" + remark1 + ", remark2=" + remark2 + ", remark3="
				+ remark3 + ", ddr=" + ddr + ", ddDate=" + ddDate + ", bgsml=" + bgsml + ", sjzfyj=" + sjzfyj
				+ ", yjzfsj=" + yjzfsj + ", yjzfr=" + yjzfr + "]";
	}

}
