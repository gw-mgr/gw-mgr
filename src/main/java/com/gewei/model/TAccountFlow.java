package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_account_flow")
public class TAccountFlow extends Model<TAccountFlow> {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String flowId;
	private String orderType;
	private Long beforeValue;
	private String tradeType;
	private Long tradeValue;
	private Long afterValue;
	private String bankHostName;
	private String bankCardId;
	private String status;
	private String personId;
	private String updateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public Long getBeforeValue() {
		return beforeValue;
	}

	public void setBeforeValue(Long beforeValue) {
		this.beforeValue = beforeValue;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public Long getTradeValue() {
		return tradeValue;
	}

	public void setTradeValue(Long tradeValue) {
		this.tradeValue = tradeValue;
	}

	public Long getAfterValue() {
		return afterValue;
	}

	public void setAfterValue(Long afterValue) {
		this.afterValue = afterValue;
	}

	public String getBankHostName() {
		return bankHostName;
	}

	public void setBankHostName(String bankHostName) {
		this.bankHostName = bankHostName;
	}

	public String getBankCardId() {
		return bankCardId;
	}

	public void setBankCardId(String bankCardId) {
		this.bankCardId = bankCardId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	@Override
	public String toString() {
		return "TAccountFlow [id=" + id + ", flowId=" + flowId + ", orderType=" + orderType + ", beforeValue=" + beforeValue + ", tradeType=" + tradeType + ", tradeValue=" + tradeValue + ", afterValue=" + afterValue
				+ ", bankHostName=" + bankHostName + ", bankCardId=" + bankCardId + ", status=" + status + ", personId=" + personId + ", updateTime=" + updateTime + "]";
	}
}
