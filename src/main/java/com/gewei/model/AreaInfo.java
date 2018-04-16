package com.gewei.model;

import java.io.Serializable;
import java.util.List;

public class AreaInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String value;
	private String text;
	private List<AreaInfo> children;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<AreaInfo> getChildren() {
		return children;
	}

	public void setChildren(List<AreaInfo> children) {
		this.children = children;
	}
}
