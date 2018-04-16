package com.gewei.model.vo;
import java.io.Serializable;
/**
 * @Description: 服务商搜索，接收前端参数实体
 * @author: Tiger
 * @date: 2018年1月28日 下午2:01:47
 */
public class ProductInfoVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String exmaineStatus;
	private String productType;
	private String keywordType;
	private String keywordInfo;
	public String getExmaineStatus() {
		return exmaineStatus;
	}
	public void setExmaineStatus(String exmaineStatus) {
		this.exmaineStatus = exmaineStatus;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getKeywordType() {
		return keywordType;
	}
	public void setKeywordType(String keywordType) {
		this.keywordType = keywordType;
	}
	public String getKeywordInfo() {
		return keywordInfo;
	}
	public void setKeywordInfo(String keywordInfo) {
		this.keywordInfo = keywordInfo;
	}
}
