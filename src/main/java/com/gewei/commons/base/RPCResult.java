package com.gewei.commons.base;

import java.io.Serializable;

public class RPCResult implements Serializable {
	private static final long serialVersionUID = 1L;
	private Object body;
	private boolean flag;
	private String tipMsg;

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getTipMsg() {
		return tipMsg;
	}

	public void setTipMsg(String tipMsg) {
		this.tipMsg = tipMsg;
	}
}
