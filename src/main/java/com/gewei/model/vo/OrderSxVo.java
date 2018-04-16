package com.gewei.model.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import com.baomidou.mybatisplus.activerecord.Model;
import com.gewei.commons.utils.JsonUtils;
import com.gewei.model.Beneficial;
import com.gewei.model.OrderSxBxsx;

/**
 * <p>
 * 人寿保险订单表
 * </p>
 *
 * @author caoyifu@iyooc.cn
 * @since 2018-03-28
 */
public class OrderSxVo{
	/**
     * 订单号
     */
	private String orderId;
	private List<OrderSxBxsx> tbsx_bxsx;

	public List<OrderSxBxsx> getTbsx_bxsx() {
		return tbsx_bxsx;
	}

	public void setTbsx_bxsx(List<OrderSxBxsx> tbsx_bxsx) {
		this.tbsx_bxsx = tbsx_bxsx;
	}
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	
	private String shengXiaoRiQi;
	
	private List<TzljForOrderSx> tzlj;
	
	private List<Beneficial> syr;
	/**以下为投保人信息*/
	private String userName;
	private String sex;
	private String marryFlag;
	private String birthDate;
	private String certType;
	private String certNo;
	private String nationality;
	private String household;
	private String validityDate;
	private String insuredRelationship;
	private String residentType;
	private String pprovince;
	private String pcity;
	private String pdistrict;
	private String postalAddress;
	private String rprovince;
	private String rcity;
	private String rdistrict;
	private String residentialAddress;
	private String telphone;
	private String mtelphone;
	private String mail;
	private String workUnit;
	private String jobContent;
	private String industry;
	private String occupation;
	private String GZSXJSML_CSML;
	// 投保人
	private String tbr_id;
	private String tbr_userName;
	private String tbr_sex;
	private String tbr_marryFlag;
	private String tbr_birthDate;
	private String tbr_certType;
	private String tbr_certNo;
	private String tbr_nationality;
	private String tbr_houseHold;
	private String tbr_validityDate;
	private String tbr_insuredRelationship;
	private String tbr_residentType;
	private String tbr_pprovince;
	private String tbr_pcity;
	private String tbr_pdistrict;
	private String tbr_postalAddress;
	private String tbr_rprovince;
	private String tbr_rcity;
	private String tbr_rdistrict;
	private String tbr_residentialAddress;
	private String tbr_telphone;
	private String tbr_mtelphone;
	private String tbr_mail;
	private String tbr_workUnit;
	private String tbr_jobContent;
	private String tbr_industry;
	private String tbr_occupation;
	private String tbr_GZSXJSML_CSML;
	// 被保人
	private String bbr_id;
	private String bbr_userName;
	private String bbr_sex;
	private String bbr_marryFlag;
	private String bbr_birthDate;
	private String bbr_certType;
	private String bbr_certNo;
	private String bbr_nationality;
	private String bbr_houseHold;
	private String bbr_validityDate;
	private String bbr_insuredRelationship;
	private String bbr_residentType;
	private String bbr_pprovince;
	private String bbr_pcity;
	private String bbr_pdistrict;
	private String bbr_postalAddress;
	private String bbr_rprovince;
	private String bbr_rcity;
	private String bbr_rdistrict;
	private String bbr_residentialAddress;
	private String bbr_telphone;
	private String bbr_mtelphone;
	private String bbr_mail;
	private String bbr_workUnit;
	private String bbr_jobContent;
	private String bbr_industry;
	private String bbr_occupation;
	private String bbr_GZSXJSML_CSML;
	/**
	 被保人	
	 */
	private String userNameB;
	private String sexB;
	private String marryFlagB;
	private String birthDateB;
	private String certTypeB;
	private String certNoB;
	private String nationalityB;
	private String householdB;
	private String validityDateB;
	private String residentTypeB;
	private String provinceB;
	private String cityB;
	private String districtB;
	private String residentialAddressB;
	private String telphoneB;
	private String mtelphoneB;
	private String mailB;
	private String workUnitB;
	private String jobContentB;
	private String industryB;
	private String occupationB;
	private String beneficialRate;
	private String beneficialName;
	private String sexS;
	private String birthDateS;
	private String relationship;
	private String certTypeS;
	private String certNoS;
	private String validityDateS;
	private String residentialAddressS;
	private String beneficiaryOrder;
	private String description;
	private String insuranceCompany;
	private String insuranceCompanyTel;
	private String policyholder;
	private String salesManName;
	private String operatorName;

	private Integer order;
	private String name;
	private String code;
	private String bxDate;
	private String jfDate;
	private BigDecimal bxMoney;
	private BigDecimal bxFee;
	private BigDecimal zjbf;
	private BigDecimal bfxj;
	
	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBxDate() {
		return bxDate;
	}

	public void setBxDate(String bxDate) {
		this.bxDate = bxDate;
	}

	public String getJfDate() {
		return jfDate;
	}

	public void setJfDate(String jfDate) {
		this.jfDate = jfDate;
	}

	public BigDecimal getBxMoney() {
		return bxMoney;
	}

	public void setBxMoney(BigDecimal bxMoney) {
		this.bxMoney = bxMoney;
	}

	public BigDecimal getBxFee() {
		return bxFee;
	}

	public void setBxFee(BigDecimal bxFee) {
		this.bxFee = bxFee;
	}

	public BigDecimal getZjbf() {
		return zjbf;
	}

	public void setZjbf(BigDecimal zjbf) {
		this.zjbf = zjbf;
	}

	public BigDecimal getBfxj() {
		return bfxj;
	}

	public void setBfxj(BigDecimal bfxj) {
		this.bfxj = bfxj;
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

	public String getTbr_id() {
		return tbr_id;
	}

	public void setTbr_id(String tbr_id) {
		this.tbr_id = tbr_id;
	}

	public String getBbr_id() {
		return bbr_id;
	}

	public void setBbr_id(String bbr_id) {
		this.bbr_id = bbr_id;
	}

	public String getTbr_userName() {
		return tbr_userName;
	}

	public void setTbr_userName(String tbr_userName) {
		this.tbr_userName = tbr_userName;
	}

	public String getTbr_sex() {
		return tbr_sex;
	}

	public void setTbr_sex(String tbr_sex) {
		this.tbr_sex = tbr_sex;
	}

	public String getTbr_marryFlag() {
		return tbr_marryFlag;
	}

	public void setTbr_marryFlag(String tbr_marryFlag) {
		this.tbr_marryFlag = tbr_marryFlag;
	}

	public String getTbr_birthDate() {
		return tbr_birthDate;
	}

	public void setTbr_birthDate(String tbr_birthDate) {
		this.tbr_birthDate = tbr_birthDate;
	}

	public String getTbr_certType() {
		return tbr_certType;
	}

	public void setTbr_certType(String tbr_certType) {
		this.tbr_certType = tbr_certType;
	}

	public String getTbr_certNo() {
		return tbr_certNo;
	}

	public void setTbr_certNo(String tbr_certNo) {
		this.tbr_certNo = tbr_certNo;
	}

	public String getTbr_nationality() {
		return tbr_nationality;
	}

	public void setTbr_nationality(String tbr_nationality) {
		this.tbr_nationality = tbr_nationality;
	}

	public String getTbr_houseHold() {
		return tbr_houseHold;
	}

	public void setTbr_houseHold(String tbr_houseHold) {
		this.tbr_houseHold = tbr_houseHold;
	}

	public String getTbr_validityDate() {
		return tbr_validityDate;
	}

	public void setTbr_validityDate(String tbr_validityDate) {
		this.tbr_validityDate = tbr_validityDate;
	}

	public String getTbr_insuredRelationship() {
		return tbr_insuredRelationship;
	}

	public void setTbr_insuredRelationship(String tbr_insuredRelationship) {
		this.tbr_insuredRelationship = tbr_insuredRelationship;
	}

	public String getTbr_residentType() {
		return tbr_residentType;
	}

	public void setTbr_residentType(String tbr_residentType) {
		this.tbr_residentType = tbr_residentType;
	}

	public String getTbr_pprovince() {
		return tbr_pprovince;
	}

	public void setTbr_pprovince(String tbr_pprovince) {
		this.tbr_pprovince = tbr_pprovince;
	}

	public String getTbr_pcity() {
		return tbr_pcity;
	}

	public void setTbr_pcity(String tbr_pcity) {
		this.tbr_pcity = tbr_pcity;
	}

	public String getTbr_pdistrict() {
		return tbr_pdistrict;
	}

	public void setTbr_pdistrict(String tbr_pdistrict) {
		this.tbr_pdistrict = tbr_pdistrict;
	}

	public String getTbr_postalAddress() {
		return tbr_postalAddress;
	}

	public void setTbr_postalAddress(String tbr_postalAddress) {
		this.tbr_postalAddress = tbr_postalAddress;
	}

	public String getTbr_rprovince() {
		return tbr_rprovince;
	}

	public void setTbr_rprovince(String tbr_rprovince) {
		this.tbr_rprovince = tbr_rprovince;
	}

	public String getTbr_rcity() {
		return tbr_rcity;
	}

	public void setTbr_rcity(String tbr_rcity) {
		this.tbr_rcity = tbr_rcity;
	}

	public String getTbr_rdistrict() {
		return tbr_rdistrict;
	}

	public void setTbr_rdistrict(String tbr_rdistrict) {
		this.tbr_rdistrict = tbr_rdistrict;
	}

	public String getTbr_residentialAddress() {
		return tbr_residentialAddress;
	}

	public void setTbr_residentialAddress(String tbr_residentialAddress) {
		this.tbr_residentialAddress = tbr_residentialAddress;
	}

	public String getTbr_telphone() {
		return tbr_telphone;
	}

	public void setTbr_telphone(String tbr_telphone) {
		this.tbr_telphone = tbr_telphone;
	}

	public String getTbr_mtelphone() {
		return tbr_mtelphone;
	}

	public void setTbr_mtelphone(String tbr_mtelphone) {
		this.tbr_mtelphone = tbr_mtelphone;
	}

	public String getTbr_mail() {
		return tbr_mail;
	}

	public void setTbr_mail(String tbr_mail) {
		this.tbr_mail = tbr_mail;
	}

	public String getTbr_workUnit() {
		return tbr_workUnit;
	}

	public void setTbr_workUnit(String tbr_workUnit) {
		this.tbr_workUnit = tbr_workUnit;
	}

	public String getTbr_jobContent() {
		return tbr_jobContent;
	}

	public void setTbr_jobContent(String tbr_jobContent) {
		this.tbr_jobContent = tbr_jobContent;
	}

	public String getTbr_industry() {
		return tbr_industry;
	}

	public void setTbr_industry(String tbr_industry) {
		this.tbr_industry = tbr_industry;
	}

	public String getTbr_occupation() {
		return tbr_occupation;
	}

	public void setTbr_occupation(String tbr_occupation) {
		this.tbr_occupation = tbr_occupation;
	}

	public String getTbr_GZSXJSML_CSML() {
		return tbr_GZSXJSML_CSML;
	}

	public void setTbr_GZSXJSML_CSML(String tbr_GZSXJSML_CSML) {
		this.tbr_GZSXJSML_CSML = tbr_GZSXJSML_CSML;
	}

	public String getBbr_userName() {
		return bbr_userName;
	}

	public void setBbr_userName(String bbr_userName) {
		this.bbr_userName = bbr_userName;
	}

	public String getBbr_sex() {
		return bbr_sex;
	}

	public void setBbr_sex(String bbr_sex) {
		this.bbr_sex = bbr_sex;
	}

	public String getBbr_marryFlag() {
		return bbr_marryFlag;
	}

	public void setBbr_marryFlag(String bbr_marryFlag) {
		this.bbr_marryFlag = bbr_marryFlag;
	}

	public String getBbr_birthDate() {
		return bbr_birthDate;
	}

	public void setBbr_birthDate(String bbr_birthDate) {
		this.bbr_birthDate = bbr_birthDate;
	}

	public String getBbr_certType() {
		return bbr_certType;
	}

	public void setBbr_certType(String bbr_certType) {
		this.bbr_certType = bbr_certType;
	}

	public String getBbr_certNo() {
		return bbr_certNo;
	}

	public void setBbr_certNo(String bbr_certNo) {
		this.bbr_certNo = bbr_certNo;
	}

	public String getBbr_nationality() {
		return bbr_nationality;
	}

	public void setBbr_nationality(String bbr_nationality) {
		this.bbr_nationality = bbr_nationality;
	}

	public String getBbr_houseHold() {
		return bbr_houseHold;
	}

	public void setBbr_houseHold(String bbr_houseHold) {
		this.bbr_houseHold = bbr_houseHold;
	}

	public String getBbr_validityDate() {
		return bbr_validityDate;
	}

	public void setBbr_validityDate(String bbr_validityDate) {
		this.bbr_validityDate = bbr_validityDate;
	}

	public String getBbr_insuredRelationship() {
		return bbr_insuredRelationship;
	}

	public void setBbr_insuredRelationship(String bbr_insuredRelationship) {
		this.bbr_insuredRelationship = bbr_insuredRelationship;
	}

	public String getBbr_residentType() {
		return bbr_residentType;
	}

	public void setBbr_residentType(String bbr_residentType) {
		this.bbr_residentType = bbr_residentType;
	}

	public String getBbr_pprovince() {
		return bbr_pprovince;
	}

	public void setBbr_pprovince(String bbr_pprovince) {
		this.bbr_pprovince = bbr_pprovince;
	}

	public String getBbr_pcity() {
		return bbr_pcity;
	}

	public void setBbr_pcity(String bbr_pcity) {
		this.bbr_pcity = bbr_pcity;
	}

	public String getBbr_pdistrict() {
		return bbr_pdistrict;
	}

	public void setBbr_pdistrict(String bbr_pdistrict) {
		this.bbr_pdistrict = bbr_pdistrict;
	}

	public String getBbr_postalAddress() {
		return bbr_postalAddress;
	}

	public void setBbr_postalAddress(String bbr_postalAddress) {
		this.bbr_postalAddress = bbr_postalAddress;
	}

	public String getBbr_rprovince() {
		return bbr_rprovince;
	}

	public void setBbr_rprovince(String bbr_rprovince) {
		this.bbr_rprovince = bbr_rprovince;
	}

	public String getBbr_rcity() {
		return bbr_rcity;
	}

	public void setBbr_rcity(String bbr_rcity) {
		this.bbr_rcity = bbr_rcity;
	}

	public String getBbr_rdistrict() {
		return bbr_rdistrict;
	}

	public void setBbr_rdistrict(String bbr_rdistrict) {
		this.bbr_rdistrict = bbr_rdistrict;
	}

	public String getBbr_residentialAddress() {
		return bbr_residentialAddress;
	}

	public void setBbr_residentialAddress(String bbr_residentialAddress) {
		this.bbr_residentialAddress = bbr_residentialAddress;
	}

	public String getBbr_telphone() {
		return bbr_telphone;
	}

	public void setBbr_telphone(String bbr_telphone) {
		this.bbr_telphone = bbr_telphone;
	}

	public String getBbr_mtelphone() {
		return bbr_mtelphone;
	}

	public void setBbr_mtelphone(String bbr_mtelphone) {
		this.bbr_mtelphone = bbr_mtelphone;
	}

	public String getBbr_mail() {
		return bbr_mail;
	}

	public void setBbr_mail(String bbr_mail) {
		this.bbr_mail = bbr_mail;
	}

	public String getBbr_workUnit() {
		return bbr_workUnit;
	}

	public void setBbr_workUnit(String bbr_workUnit) {
		this.bbr_workUnit = bbr_workUnit;
	}

	public String getBbr_jobContent() {
		return bbr_jobContent;
	}

	public void setBbr_jobContent(String bbr_jobContent) {
		this.bbr_jobContent = bbr_jobContent;
	}

	public String getBbr_industry() {
		return bbr_industry;
	}

	public void setBbr_industry(String bbr_industry) {
		this.bbr_industry = bbr_industry;
	}

	public String getBbr_occupation() {
		return bbr_occupation;
	}

	public void setBbr_occupation(String bbr_occupation) {
		this.bbr_occupation = bbr_occupation;
	}

	public String getBbr_GZSXJSML_CSML() {
		return bbr_GZSXJSML_CSML;
	}

	public void setBbr_GZSXJSML_CSML(String bbr_GZSXJSML_CSML) {
		this.bbr_GZSXJSML_CSML = bbr_GZSXJSML_CSML;
	}

	public String getShengXiaoRiQi() {
		return shengXiaoRiQi;
	}

	public void setShengXiaoRiQi(String shengXiaoRiQi) {
		this.shengXiaoRiQi = shengXiaoRiQi;
	}

	public String getGZSXJSML_CSML() {
		return GZSXJSML_CSML;
	}

	public void setGZSXJSML_CSML(String gZSXJSML_CSML) {
		GZSXJSML_CSML = gZSXJSML_CSML;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
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

	public BigDecimal getBxgskpyj() {
		return bxgskpyj;
	}

	public void setBxgskpyj(BigDecimal bxgskpyj) {
		this.bxgskpyj = bxgskpyj;
	}

	public BigDecimal getBxgskpyjbl() {
		return bxgskpyjbl;
	}

	public void setBxgskpyjbl(BigDecimal bxgskpyjbl) {
		this.bxgskpyjbl = bxgskpyjbl;
	}

	public String getGiftFlag() {
		return giftFlag;
	}

	public void setGiftFlag(String giftFlag) {
		this.giftFlag = giftFlag;
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

	public BigDecimal getZxyjje() {
		return zxyjje;
	}

	public void setZxyjje(BigDecimal zxyjje) {
		this.zxyjje = zxyjje;
	}

	public BigDecimal getFxyjje1() {
		return fxyjje1;
	}

	public void setFxyjje1(BigDecimal fxyjje1) {
		this.fxyjje1 = fxyjje1;
	}

	public BigDecimal getFxyjje2() {
		return fxyjje2;
	}

	public void setFxyjje2(BigDecimal fxyjje2) {
		this.fxyjje2 = fxyjje2;
	}

	public BigDecimal getZhjrjje() {
		return zhjrjje;
	}

	public void setZhjrjje(BigDecimal zhjrjje) {
		this.zhjrjje = zhjrjje;
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

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getPolicyholderId() {
		return policyholderId;
	}

	public void setPolicyholderId(String policyholderId) {
		this.policyholderId = policyholderId;
	}

	public BigDecimal getZxyjbl() {
		return zxyjbl;
	}

	public void setZxyjbl(BigDecimal zxyjbl) {
		this.zxyjbl = zxyjbl;
	}

	public BigDecimal getFxyjbl1() {
		return fxyjbl1;
	}

	public void setFxyjbl1(BigDecimal fxyjbl1) {
		this.fxyjbl1 = fxyjbl1;
	}

	public BigDecimal getFxyjbl2() {
		return fxyjbl2;
	}

	public void setFxyjbl2(BigDecimal fxyjbl2) {
		this.fxyjbl2 = fxyjbl2;
	}

	public BigDecimal getZhjrjbl() {
		return zhjrjbl;
	}

	public void setZhjrjbl(BigDecimal zhjrjbl) {
		this.zhjrjbl = zhjrjbl;
	}

	public BigDecimal getBxgsdqjl() {
		return bxgsdqjl;
	}

	public void setBxgsdqjl(BigDecimal bxgsdqjl) {
		this.bxgsdqjl = bxgsdqjl;
	}

	public BigDecimal getRecordertc() {
		return recordertc;
	}

	public void setRecordertc(BigDecimal recordertc) {
		this.recordertc = recordertc;
	}

	public BigDecimal getBgsml() {
		return bgsml;
	}

	public void setBgsml(BigDecimal bgsml) {
		this.bgsml = bgsml;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getHousehold() {
		return household;
	}

	public void setHousehold(String household) {
		this.household = household;
	}

	public String getValidityDate() {
		return validityDate;
	}

	public void setValidityDate(String validityDate) {
		this.validityDate = validityDate;
	}

	public String getInsuredRelationship() {
		return insuredRelationship;
	}

	public void setInsuredRelationship(String insuredRelationship) {
		this.insuredRelationship = insuredRelationship;
	}

	public String getResidentType() {
		return residentType;
	}

	public void setResidentType(String residentType) {
		this.residentType = residentType;
	}

	public String getPprovince() {
		return pprovince;
	}

	public void setPprovince(String pprovince) {
		this.pprovince = pprovince;
	}

	public String getPcity() {
		return pcity;
	}

	public void setPcity(String pcity) {
		this.pcity = pcity;
	}

	public String getPdistrict() {
		return pdistrict;
	}

	public void setPdistrict(String pdistrict) {
		this.pdistrict = pdistrict;
	}

	public String getPostalAddress() {
		return postalAddress;
	}

	public void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	public String getRprovince() {
		return rprovince;
	}

	public void setRprovince(String rprovince) {
		this.rprovince = rprovince;
	}

	public String getRcity() {
		return rcity;
	}

	public void setRcity(String rcity) {
		this.rcity = rcity;
	}

	public String getRdistrict() {
		return rdistrict;
	}

	public void setRdistrict(String rdistrict) {
		this.rdistrict = rdistrict;
	}

	public String getResidentialAddress() {
		return residentialAddress;
	}

	public void setResidentialAddress(String residentialAddress) {
		this.residentialAddress = residentialAddress;
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

	public String getWorkUnit() {
		return workUnit;
	}

	public void setWorkUnit(String workUnit) {
		this.workUnit = workUnit;
	}

	public String getJobContent() {
		return jobContent;
	}

	public void setJobContent(String jobContent) {
		this.jobContent = jobContent;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getUserNameB() {
		return userNameB;
	}

	public void setUserNameB(String userNameB) {
		this.userNameB = userNameB;
	}

	public String getSexB() {
		return sexB;
	}

	public void setSexB(String sexB) {
		this.sexB = sexB;
	}

	public String getMarryFlag() {
		return marryFlag;
	}

	public void setMarryFlag(String marryFlag) {
		this.marryFlag = marryFlag;
	}

	public String getMarryFlagB() {
		return marryFlagB;
	}

	public void setMarryFlagB(String marryFlagB) {
		this.marryFlagB = marryFlagB;
	}

	public String getBirthDateB() {
		return birthDateB;
	}

	public void setBirthDateB(String birthDateB) {
		this.birthDateB = birthDateB;
	}

	public String getCertTypeB() {
		return certTypeB;
	}

	public void setCertTypeB(String certTypeB) {
		this.certTypeB = certTypeB;
	}

	public String getCertNoB() {
		return certNoB;
	}

	public void setCertNoB(String certNoB) {
		this.certNoB = certNoB;
	}

	public String getNationalityB() {
		return nationalityB;
	}

	public void setNationalityB(String nationalityB) {
		this.nationalityB = nationalityB;
	}

	public String getHouseholdB() {
		return householdB;
	}

	public void setHouseholdB(String householdB) {
		this.householdB = householdB;
	}

	public String getValidityDateB() {
		return validityDateB;
	}

	public void setValidityDateB(String validityDateB) {
		this.validityDateB = validityDateB;
	}

	public String getResidentTypeB() {
		return residentTypeB;
	}

	public void setResidentTypeB(String residentTypeB) {
		this.residentTypeB = residentTypeB;
	}

	public String getCityB() {
		return cityB;
	}

	public void setCityB(String cityB) {
		this.cityB = cityB;
	}

	public String getProvinceB() {
		return provinceB;
	}

	public void setProvinceB(String provinceB) {
		this.provinceB = provinceB;
	}

	public String getDistrictB() {
		return districtB;
	}

	public void setDistrictB(String districtB) {
		this.districtB = districtB;
	}

	public String getResidentialAddressB() {
		return residentialAddressB;
	}

	public void setResidentialAddressB(String residentialAddressB) {
		this.residentialAddressB = residentialAddressB;
	}

	public String getTelphoneB() {
		return telphoneB;
	}

	public void setTelphoneB(String telphoneB) {
		this.telphoneB = telphoneB;
	}

	public String getMtelphoneB() {
		return mtelphoneB;
	}

	public void setMtelphoneB(String mtelphoneB) {
		this.mtelphoneB = mtelphoneB;
	}

	public String getMailB() {
		return mailB;
	}

	public void setMailB(String mailB) {
		this.mailB = mailB;
	}

	public String getWorkUnitB() {
		return workUnitB;
	}

	public void setWorkUnitB(String workUnitB) {
		this.workUnitB = workUnitB;
	}

	public String getJobContentB() {
		return jobContentB;
	}

	public void setJobContentB(String jobContentB) {
		this.jobContentB = jobContentB;
	}

	public String getIndustryB() {
		return industryB;
	}

	public void setIndustryB(String industryB) {
		this.industryB = industryB;
	}

	public String getOccupationB() {
		return occupationB;
	}

	public void setOccupationB(String occupationB) {
		this.occupationB = occupationB;
	}

	public String getBeneficialRate() {
		return beneficialRate;
	}

	public void setBeneficialRate(String beneficialRate) {
		this.beneficialRate = beneficialRate;
	}

	public String getBeneficialName() {
		return beneficialName;
	}

	public void setBeneficialName(String beneficialName) {
		this.beneficialName = beneficialName;
	}

	public String getSexS() {
		return sexS;
	}

	public void setSexS(String sexS) {
		this.sexS = sexS;
	}

	public String getBirthDateS() {
		return birthDateS;
	}

	public void setBirthDateS(String birthDateS) {
		this.birthDateS = birthDateS;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getCertTypeS() {
		return certTypeS;
	}

	public void setCertTypeS(String certTypeS) {
		this.certTypeS = certTypeS;
	}

	public String getCertNoS() {
		return certNoS;
	}

	public void setCertNoS(String certNoS) {
		this.certNoS = certNoS;
	}

	public String getValidityDateS() {
		return validityDateS;
	}

	public void setValidityDateS(String validityDateS) {
		this.validityDateS = validityDateS;
	}

	public String getResidentialAddressS() {
		return residentialAddressS;
	}

	public void setResidentialAddressS(String residentialAddressS) {
		this.residentialAddressS = residentialAddressS;
	}

	public String getBeneficiaryOrder() {
		return beneficiaryOrder;
	}

	public void setBeneficiaryOrder(String beneficiaryOrder) {
		this.beneficiaryOrder = beneficiaryOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getPolicyholder() {
		return policyholder;
	}

	public void setPolicyholder(String policyholder) {
		this.policyholder = policyholder;
	}

	public String getSalesManName() {
		return salesManName;
	}

	public void setSalesManName(String salesManName) {
		this.salesManName = salesManName;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public String getLqfs() {
		return lqfs;
	}

	public void setLqfs(String lqfs) {
		this.lqfs = lqfs;
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

	public List<Beneficial> getSyr() {
		return syr;
	}

	public void setSyr(List<Beneficial> syr) {
		this.syr = syr;
	}

	public List<TzljForOrderSx> getTzlj() {
		return tzlj;
	}

	public void setTzlj(List<TzljForOrderSx> tzlj) {
		this.tzlj = tzlj;
	}

	@Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}
