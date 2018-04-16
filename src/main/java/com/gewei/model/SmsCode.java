package com.gewei.model;

import java.io.Serializable;

public class SmsCode implements Serializable {
	private static final long serialVersionUID = 1L;
	private String addr;
	private String smsCode;
	private String createTime;
	private String deadTime;
	private String flag;

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getSmsCode() {
		return smsCode;
	}

	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDeadTime() {
		return deadTime;
	}

	public void setDeadTime(String deadTime) {
		this.deadTime = deadTime;
	}
}
