package com.gewei.model;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("order_sx_bxsx")
public class OrderSxBxsx extends Model<OrderSxBxsx> {

    private static final long serialVersionUID = 1L;

	private Integer id;
	private String orderId;
	private String order;
	private String name;
	private String code;
	private String bxDate;
	private String jfDate;
	private BigDecimal bxMoney;
	private BigDecimal bxFee;
	private BigDecimal zjbf;
	private BigDecimal bfxj;


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
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

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

}
