package com.gewei.model;

import java.io.Serializable;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

@TableName("t_account")
public class TAccount extends Model<TAccount> {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String personId;
	private long totalIncome;
	private long settleApplying;
	private long balance;
	private String bankHostName;
	private String bankCardId;
	private String updateTime;

	@Override
	public String toString() {
		return "TAccount [id=" + id + ", personId=" + personId + ", totalIncome=" + totalIncome + ", settleApplying=" + settleApplying + ", balance=" + balance + ", bankHostName=" + bankHostName + ", bankCardId="
				+ bankCardId + ", updateTime=" + updateTime + "]";
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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public long getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(long totalIncome) {
		this.totalIncome = totalIncome;
	}

	public long getSettleApplying() {
		return settleApplying;
	}

	public void setSettleApplying(long settleApplying) {
		this.settleApplying = settleApplying;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
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
}
